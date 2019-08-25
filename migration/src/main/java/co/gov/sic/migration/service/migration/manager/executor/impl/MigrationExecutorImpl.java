package co.gov.sic.migration.service.migration.manager.executor.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoEndpointManager;
import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.GenerarConsecutivoUtil;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.migration.loader.MigrationLoader;
import co.gov.sic.migration.service.migration.manager.executor.MigrationExecutor;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;

import java.io.File;
import java.util.List;

@Service
public class MigrationExecutorImpl implements MigrationExecutor {

    private static final Logger LOGGER = LogManager.getLogger(MigrationExecutorImpl.class);

    private MigrationLoader migrationLoader;

    private String uuidExecution;

    private GeneralStatus statusApplication;

    private AlfrescoEndpointManager alfrescoEndpointManager;

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    @Autowired
    public MigrationExecutorImpl(MigrationLoader migrationLoader, AlfrescoEndpointManager alfrescoEndpointManager, AplicacionEstadoBoundary aplicacionEstadoBoundary) {
        this.migrationLoader = migrationLoader;
        this.alfrescoEndpointManager = alfrescoEndpointManager;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
    }

    @Override
    public void startExecution(final IniciarDTOResquest request) throws SystemException {

        LOGGER.info("EXECUTE: MigrationExecutorImpl - startExecution - Parameters - request: {}", request.toString());
        try {
            uuidExecution = GenerarConsecutivoUtil.generarCosecutivo();

            MigrationStatusDTOResponse m = aplicacionEstadoBoundary.findLastAplicacionEstado();

            if (m.getIdEjecucion() == null) {
                aplicacionEstadoBoundary.registerGeneralStateAplicaction(Etapa.INICIADA.getName(), uuidExecution, "Iniciando Ejecución", 1, "", "En Ejecución");
            }

            if (m.getEstadoMigracion().getCodEstado() != EstadoParametro.DETENIENDO.getId()) {
                aplicacionEstadoBoundary.registerGeneralStateAplicaction(Etapa.INICIADA.getName(), uuidExecution, "Iniciando Ejecución", 1, "", "En Ejecución");
            }

            LOGGER.info("EXECUTE: MigrationExecutorImpl - startExecution - STARTING PROCESS WITH UUID: {}", uuidExecution);
            List<BatchFiltered<Metadatos, Workspace, Document>> migration = migrationLoader.createMigrationBatch(request.getCantidad(), uuidExecution);
            if(!migration.isEmpty()){
                statusApplication = migrationLoader.prepareBatchMigrationOnStage(migration, uuidExecution);
                Thread.sleep(2000);
                alfrescoEndpointManager.bulkImportStart(Boolean.toString(request.isReplaceExisting()), statusApplication.getPathMigration().concat(File.separator));
            }



        } catch (ResourceAccessException a) {
            //throw ExceptionBuilder.newBuilder().withCode(CodigoError.ADAPTER_EXCEPTION).withMessage(MensajeError.WEB_SERVICE_EXCEPTION.getName()).buildSystemException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e){
         e.printStackTrace();
        } finally {
            MigrationStatusDTOResponse thirdGeneralStatusApplication = aplicacionEstadoBoundary.findLastAplicacionEstado();
            if (thirdGeneralStatusApplication.getIdEjecucion() != null) {
                if (thirdGeneralStatusApplication.getEstadoMigracion().getCodEstado() == EstadoParametro.ESCANEANDO.getId()) {
                    LOGGER.info("EXECUTE: MigrationExecutorImpl - startExecution - INVOKING INITIATE IMPORT ON BULK: Resource Folder Path:  - request: {}", thirdGeneralStatusApplication.getCarpetaMigracion());
                    aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.FINALIZADA.getName(), thirdGeneralStatusApplication.getUuid(), EstadoParametro.FINALIZO.getName(),
                            EstadoParametro.FINALIZO.getId(), AlfrescoBundleUtil.getProperty("host.folder").concat(File.separator).concat(thirdGeneralStatusApplication.getUuid()),
                            "Finalizo Migración Correctamente.");
                } else if (thirdGeneralStatusApplication.getEstadoMigracion().getCodEstado() == EstadoParametro.DETENIENDO.getId()) {
                    LOGGER.info("EXECUTE: MigrationExecutorImpl - startExecution - STOP PROCESS MIGRATION: Resource Folder Path:  - request: {}", thirdGeneralStatusApplication.getCarpetaMigracion().concat(thirdGeneralStatusApplication.getUuid()));
                    aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.FINALIZADA.getName(), thirdGeneralStatusApplication.getUuid(), EstadoParametro.DETENIDO.getName(),
                            EstadoParametro.DETENIDO.getId(), AlfrescoBundleUtil.getProperty("host.folder").concat(File.separator).concat(thirdGeneralStatusApplication.getUuid()),
                            "Aplicación Detenida.");
                }
            } else {
                aplicacionEstadoBoundary.registerGeneralStateAplicaction(Etapa.INICIADA.getName(), uuidExecution, "Iniciando Ejecución", 1, "", "En Ejecución");

            }
        }

    }

    @Override
    public void stopExecution() throws BusinessException {
        LOGGER.warn("EXECUTE: MigrationExecutorImpl - stopExecution - STOPPING EXECUTION!!-----------------------------> XO");
        MigrationStatusDTOResponse migrationStatus = aplicacionEstadoBoundary.findLastAplicacionEstado();

        aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.INICIADA.getName(), migrationStatus.getUuid(), EstadoParametro.DETENIENDO.getName(),
                EstadoParametro.DETENIENDO.getId(), AlfrescoBundleUtil.getProperty("host.folder").concat(migrationStatus.getUuid()),
                "Deteniendo la migración - Los lotes en cola han sido cancelados. Esperando la finalización del lote en proceso.");
    }

    @Override
    public void updateExecutionState(String uuid, Metadatos fileImport, EstadoParametro estadoParametro, String stateDescription) {
        //TODO: implement activity of updates executions of states migration

    }


}

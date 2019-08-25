package co.gov.sic.migration.service.migration.loader.impl;

import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.domains.request.EjecucionDTORequest;
import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.commons.utils.io.FileUtil;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.mapper.business.assembly.assembler.XmlMetadataAssembler;
import co.gov.sic.migration.service.mapper.business.assembly.builder.XmlDocumentMetadataBuilder;
import co.gov.sic.migration.service.mapper.business.assembly.builder.XmlFolderMetadataBuilder;
import co.gov.sic.migration.service.migration.folder.TemporalFolderBuilder;
import co.gov.sic.migration.service.migration.folder.utils.CaseFileFolderUtil;
import co.gov.sic.migration.service.migration.loader.MigrationLoader;
import co.gov.sic.migration.service.migration.validator.BatchValidator;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.service.state.metadata.business.MetadataBoundary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class MigrationLoaderImpl implements MigrationLoader {

    private static final Logger LOGGER = LogManager.getLogger(MigrationLoaderImpl.class);

    private MetadataBoundary metadataBoundary;

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    private BatchValidator batchValidator;

    private TemporalFolderBuilder temporalFolderBuilder;

    private EjecucionBoundary ejecucionBoundary;

    private XmlMetadataAssembler xmlMetadataAssembler;

    @Autowired
    public MigrationLoaderImpl(MetadataBoundary metadataBoundary, AplicacionEstadoBoundary aplicacionEstadoBoundary, BatchValidator batchValidator, TemporalFolderBuilder temporalFolderBuilder, EjecucionBoundary ejecucionBoundary, XmlMetadataAssembler xmlMetadataAssembler) {
        this.metadataBoundary = metadataBoundary;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
        this.batchValidator = batchValidator;
        this.temporalFolderBuilder = temporalFolderBuilder;
        this.ejecucionBoundary = ejecucionBoundary;
        this.xmlMetadataAssembler = xmlMetadataAssembler;
    }

    public GeneralStatus prepareBatchMigrationOnStage(List<BatchFiltered<Metadatos, Workspace, Document>> migrationBatch, String uuid) throws SystemException {

        LOGGER.info("EXECUTE: MigrationLoaderImpl - prepareBatchMigrationOnStage - Parameters - migrationBatch: {} - UUID: {}", migrationBatch, uuid);

        File temporalFolder = temporalFolderBuilder.getTemporalFolder(uuid);
        String tempPathFolder = temporalFolder.getAbsolutePath();

        LOGGER.info("EXECUTE: MigrationLoaderImpl - prepareBatchMigrationOnStage - Create temporal Folder at path: {}", tempPathFolder);
        for (BatchFiltered<Metadatos, Workspace, Document> batch : migrationBatch) {

            final String caseFileName = batch.getDocument().getNombreExpediente();
            final String caseFileId = batch.getDocument().getIdExpediente();

            LOGGER.info("EXECUTE: MigrationLoaderImpl - prepareBatchMigrationOnStage - Create  - migrationBatch: {} - UUID: {}", migrationBatch, uuid);
            try {

                final String caseFileFolderName = CaseFileFolderUtil.nameBuilder(caseFileId, caseFileName);
                File caseFileFolderCreated = FileUtil.createFolder(caseFileFolderName, temporalFolder.getAbsolutePath());
                String folderCaseFilePath = caseFileFolderCreated.getAbsolutePath();
                Map<String, Object> folderMetadata = XmlFolderMetadataBuilder.createStructure(batch.getDocument(), batch.getWorkspace());
                xmlMetadataAssembler.assembler(folderMetadata, folderCaseFilePath);

                File tempDocument = new File(batch.getDocument().getRutaArchivo());

                String oldPath = tempDocument.getAbsolutePath();

                String fileCasePath = folderCaseFilePath.concat(File.separator).concat(tempDocument.getName());
                String caseFileFolderPath = FileUtil.moveFile(oldPath, fileCasePath);
                Map<String, Object> documentMetadata = XmlDocumentMetadataBuilder.assembler(batch.getDocument(), batch.getWorkspace());
                xmlMetadataAssembler.assembler(documentMetadata, caseFileFolderPath);

                ejecucionBoundary.updateExecutionState(batch.getDocument().getUuid(),
                        batch.getDocument().getIdregistro(),
                        EstadoParametro.ESCANEANDO,
                        "Proceso de Escaneo y asignación de propiedades del expediente fue Éxitoso. Ha(n) sido generado(s) los archivos de propiedades del expediente " + caseFileFolderName + ". La carpeta del Expediente y sus documentos se encuentran en la ubicación de cargue.",
                        Etapa.INICIADA);

                ejecucionBoundary.updateExecutionState(batch.getDocument().getUuid(),
                        batch.getDocument().getIdregistro(),
                        EstadoParametro.FINALIZO,
                        "Finalizado (Correcto) - Documento Cargado.",
                        Etapa.INICIADA);

            } catch (NullPointerException | IOException ne) {
                LOGGER.error("ERROR: MigrationLoaderImpl - prepareBatchMigrationOnStage - CANT CREATE FOLDER ");
                ejecucionBoundary.updateExecutionState(batch.getDocument().getUuid(),
                        batch.getDocument().getIdregistro(),
                        EstadoParametro.FALLO,
                        "No se ha creado la Carpeta del Expediente.",
                        Etapa.INICIADA);
            }
        }


        return setFinalStateAplication(uuid, tempPathFolder);
    }

    @Override
    public List<BatchFiltered<Metadatos, Workspace, Document>> createMigrationBatch(Integer batchSize, String uuid) throws SystemException {
        LOGGER.info("EXECUTE: MigrationLoaderImpl - createMigrationBatch - Parameters - batchSize: {} - UUID: {}", batchSize, uuid);
        List<Metadatos> migrationBatch = metadataBoundary.updateIntitalMigrationBatch(batchSize, uuid);

        for (Iterator<Metadatos> iterator = migrationBatch.iterator(); iterator.hasNext(); ) {
            Metadatos batch = iterator.next();
            EjecucionDTORequest ejecucion = EjecucionDTORequest.builder()
                    .estadoProceso(EstadoDTOResponse.builder().codEstado(EstadoParametro.ESCANEANDO.getId()).build())
                    .idRegistro(batch.getIdregistro()).uuid(uuid).mensajeEstadoProceso(EstadoParametro.ESCANEANDO.getName()).build();
            try {
                ejecucionBoundary.registrarEjecucionDocumento(ejecucion);
            } catch (EntityExistsException | DataIntegrityViolationException e) {
                Ejecucion ejecucion1 = ejecucionBoundary.findByIdRegistro(batch.getIdregistro());
                if(ejecucion1.getCodEstadoProceso().getCodEstado()==EstadoParametro.FINALIZO.getId()) {
                    ejecucionBoundary.updateExecutionState(uuid,
                            batch.getIdregistro(),
                            EstadoParametro.FALLO,
                            MensajeError.DOCUMENTS_ALREADY_MIGRATED.getName(),
                            Etapa.INICIADA);
                    iterator.remove();
                }else{
                    ejecucionBoundary.updateExecutionState(uuid,
                            batch.getIdregistro(),
                            EstadoParametro.ESCANEANDO,
                            EstadoParametro.ESCANEANDO.getName(),
                            Etapa.INICIADA);
                }
            }

        }

        MigrationStatusDTOResponse m = aplicacionEstadoBoundary.findLastAplicacionEstado();
        try {
            return batchValidator.validateBatch(migrationBatch, uuid);
        } catch (BusinessException be) {
            LOGGER.error("ERROR: MigrationLoaderImpl - createMigrationBatch - CANT CREATE FOLDER {} ", MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE);
            Integer estadoActual = m.getEstadoMigracion().getCodEstado();
            LOGGER.error("INFO-APPLICATION-GENERAL-STATUS: MigrationLoaderImpl - createMigrationBatch - STATUS {} ", estadoActual);
            if (estadoActual != EstadoParametro.DETENIENDO.getId()) {
                LOGGER.error("INFO-APPLICATION-GENERAL-STATUS: MigrationLoaderImpl - createMigrationBatch - NEW STATUS {} ", estadoActual);
                aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.INICIADA.getName(), uuid,
                        MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName(),
                        EstadoParametro.FALLO.getId(), AlfrescoBundleUtil.getProperty("host.folder"),
                        MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName());
            }
            return new ArrayList<>();
        }

    }


    private GeneralStatus setFinalStateAplication(String uuid, String pathMigration) {
        LOGGER.info("EXECUTE: MigrationLoaderImpl - setFinalStateAplication - Parameters - pathMigration: {} - UUID: {}", pathMigration, uuid);
        MigrationStatusDTOResponse migrationStatus = aplicacionEstadoBoundary.findLastAplicacionEstado();
        LOGGER.info("EXECUTE: MigrationLoaderImpl - setFinalStateAplication - ||||||||||||STATUS||||||||||| - {}", migrationStatus.toString());
        if (migrationStatus.getEstadoMigracion().getCodEstado() == EstadoParametro.DETENIENDO.getId())
            return GeneralStatus.builder().uuid(uuid).generalState(EstadoParametro.DETENIDO).pathMigration(pathMigration).build();
        else
            return GeneralStatus.builder().uuid(uuid).generalState(EstadoParametro.ESCANEANDO).pathMigration(pathMigration).build();
    }
}

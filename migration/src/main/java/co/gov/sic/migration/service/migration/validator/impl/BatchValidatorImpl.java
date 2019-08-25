package co.gov.sic.migration.service.migration.validator.impl;

import co.gov.sic.migration.adapters.alfresco.cmis.dao.MigrateLocation;
import co.gov.sic.migration.adapters.alfresco.cmis.util.AlfrescoCmisUtil;
import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.migration.validator.BatchValidator;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.service.state.metadata.business.MetadataBoundary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class BatchValidatorImpl implements BatchValidator {

    private static final Logger LOGGER = LogManager.getLogger(BatchValidatorImpl.class);

    private EjecucionBoundary ejecucionBoundary;
    private MigrateLocation migrateLocation;
    private AplicacionEstadoBoundary aplicacionEstadoBoundary;
    private MetadataBoundary metadataBoundary;
    private List<BatchFiltered<Metadatos, Workspace, Document>> migrationBatch;

    @Autowired
    public BatchValidatorImpl(EjecucionBoundary ejecucionBoundary, MigrateLocation migrateLocation, AplicacionEstadoBoundary aplicacionEstadoBoundary, MetadataBoundary metadataBoundary) {
        this.ejecucionBoundary = ejecucionBoundary;
        this.migrateLocation = migrateLocation;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
        this.metadataBoundary = metadataBoundary;
        this.migrationBatch = new ArrayList<>();
    }


    private Boolean filterExistDocument(Metadatos metadata) throws SystemException {
        LOGGER.info("EXECUTE: BatchValidatorImpl - filterExistDocument - Parameters - metadata: {}", metadata.toString());
        if (!new File(metadata.getRutaArchivo()).exists()) {
            LOGGER.error("ERROR: BatchValidatorImpl - filterExistDocument - El documento no se encuentra en la ruta especifica {}.", metadata.getRutaArchivo());
            ejecucionBoundary.updateExecutionState(metadata.getUuid(),
                    metadata.getIdregistro(),
                    EstadoParametro.FALLO,
                    "El documento no se encuentra en la ruta especifica.",
                    Etapa.INICIADA);
            return false;
        }
        return true;
    }

    private void filterDocumentsWithExistingLocalPath(List<Metadatos> documents) throws SystemException {

        LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingLocalPath - Parameters - documents: {}", documents.toString());
        this.migrationBatch = new ArrayList<>();

        Iterator<Metadatos> iterator = documents.iterator();
        while (iterator.hasNext()) {
            Metadatos metadata = iterator.next();
            if (filterExistDocument(metadata)) {

                Document document = Document.builder()
                        .path(new File(metadata.getRutaArchivo()).getAbsolutePath())
                        .build();

                BatchFiltered itemBatch = BatchFiltered.builder()
                        .document(metadata)
                        .file(document).build();

                LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingLocalPath - ADDING - itemBatch: {}", documents.toString());
                migrationBatch.add(itemBatch);

                ejecucionBoundary.updateExecutionState(metadata.getUuid(),
                        metadata.getIdregistro(),
                        EstadoParametro.ESCANEANDO,
                        "Documento Encontrado.",
                        Etapa.INICIADA);
            } else if (metadata.getRutaArchivo().trim().equalsIgnoreCase("SIN ARCHIVO") || metadata.getRutaArchivo().trim().equalsIgnoreCase("SINARCHIVO")) {

                LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingLocalPath - ADDING - itemBatch: {}", documents.toString());
                String ruta = AlfrescoBundleUtil.getProperty("imagen");

                Document document = Document.builder()
                        .path(new File(ruta).getAbsolutePath())
                        .build();

                BatchFiltered itemBatch = BatchFiltered.builder()
                        .document(metadata)
                        .file(document).build();

                LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingLocalPath - ADDING DEFAULT IMAGE - itemBatch: {}", itemBatch.toString());
                migrationBatch.add(itemBatch);

                ejecucionBoundary.updateExecutionState(metadata.getUuid(),
                        metadata.getIdregistro(),
                        EstadoParametro.ESCANEANDO,
                        "Aún no contiene un documento para migrar. Colocando Imagen Por Defecto.",
                        Etapa.INICIADA);

            } else {
                LOGGER.error("ERROR: BatchValidatorImpl - filterDocumentsWithExistingLocalPath - No se ha encontrado la ruta del documento.");
                ejecucionBoundary.updateExecutionState(metadata.getUuid(),
                        metadata.getIdregistro(),
                        EstadoParametro.FALLO,
                        "No se ha encontrado la ruta del documento.",
                        Etapa.INICIADA);
                iterator.remove();
            }
        }

    }

    private void filterDocumentsWithExistingWorkspace(List<Metadatos> batch) throws SystemException {
        LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingWorkspace - Parameters: {}", batch.toString());
        Iterator<Metadatos> iterator = batch.iterator();
        while (iterator.hasNext()) {
            Metadatos document = iterator.next();

            CmisDTO workspaceParameters = AlfrescoCmisUtil.validateWorkspaceParams(document);

            if (workspaceParameters != null) {

                String workspace = migrateLocation.findLocationDocument(workspaceParameters);

                if (!workspace.isEmpty()) {

                    for (int i = 0; i < migrationBatch.size(); i++) {
                        Metadatos item = migrationBatch.get(i).getDocument();
                        if (item.getIdregistro() == document.getIdregistro()) {
                            LOGGER.info("EXECUTE: BatchValidatorImpl - filterDocumentsWithExistingWorkspace - FOUND - Set Workspace to File Case or Document: {}", item.toString());
                            Workspace w = new Workspace();
                            w.setPath(workspace);
                            migrationBatch.get(i).setWorkspace(w);
                        }
                    }

                    ejecucionBoundary.updateExecutionState(document.getUuid(),
                            document.getIdregistro(),
                            EstadoParametro.ESCANEANDO,
                            "Workspace y ruta de archivo encontrado.",
                            Etapa.INICIADA);
                } else {
                    LOGGER.error("ERROR: BatchValidatorImpl - filterDocumentsWithExistingWorkspace - NOT FOUND - Not found workspace for File Case or Document: {}", document.toString());
                    ejecucionBoundary.updateExecutionState(document.getUuid(),
                            document.getIdregistro(),
                            EstadoParametro.FALLO,
                            "No se ha encontrado el workspace en alfresco para el documento, verifique los parametros de busqueda.",
                            Etapa.INICIADA);
                    iterator.remove();

                }
            } else {
                LOGGER.error("ERROR: BatchValidatorImpl - filterDocumentsWithExistingWorkspace - INCOMPLETE PARAMETERS - Not present all PARAMETERS for File Case or Document: {} with parameters: {}", document.toString(), workspaceParameters != null ? workspaceParameters.toString() : "IS EMPTY");
                ejecucionBoundary.updateExecutionState(document.getUuid(),
                        document.getIdregistro(),
                        EstadoParametro.FALLO,
                        "Parametros Incompletos para realizar la busqueda de la ruta de destino del documento o expediente en el ECM.",
                        Etapa.INICIADA);
                iterator.remove();
            }
        }
    }


    @Override
    public List<BatchFiltered<Metadatos, Workspace, Document>> validateBatch(List<Metadatos> metadataMigrationBatch, String uuid) throws BusinessException, SystemException {

        LOGGER.info("EXECUTE: BatchValidatorImpl - validateBatch - params: metadataMigrationBatch: {} - uuid {}", metadataMigrationBatch, uuid);

        if (!metadataMigrationBatch.isEmpty()) {
            filterPropertiesRequired(metadataMigrationBatch);
            if (!metadataMigrationBatch.isEmpty()) {
                filterExistsOnExecution(metadataMigrationBatch);
                if (!metadataMigrationBatch.isEmpty()) {
                    filterDocumentsWithExistingLocalPath(metadataMigrationBatch);
                    if (!metadataMigrationBatch.isEmpty()) {
                        filterDocumentsWithExistingWorkspace(metadataMigrationBatch);
                        if (metadataMigrationBatch.isEmpty()) {
                            LOGGER.error("ERROR: BatchValidatorImpl - validateBatch - MESSAGE: {}", MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName());
                            throw ExceptionBuilder.newBuilder().withCode(CodigoError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE)
                                    .withMessage(MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName())
                                    .buildBusinessException();
                        }
                    } else {
                        LOGGER.error("ERROR: BatchValidatorImpl - validateBatch - MESSAGE: {}", MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName());
                        throw ExceptionBuilder.newBuilder().withCode(CodigoError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE)
                                .withMessage(MensajeError.COULDNT_FIND_PATH_FROM_LOCAL_AND_WORKSPACE.getName())
                                .buildBusinessException();
                    }
                } else {
                    LOGGER.error("ERROR: BatchValidatorImpl - validateBatch - MESSAGE: {}", MensajeError.DOCUMENTS_ALREADY_MIGRATED.getName());
                    throw ExceptionBuilder.newBuilder().withCode(CodigoError.DOCUMENTS_ALREADY_MIGRATED)
                            .withMessage(MensajeError.DOCUMENTS_ALREADY_MIGRATED.getName())
                            .buildBusinessException();
                }
            } else {
                LOGGER.error("ERROR: BatchValidatorImpl - validateBatch - MESSAGE: {}", MensajeError.PARAMETERS_REQUIRED_EMPTY.getName());
                throw ExceptionBuilder.newBuilder().withCode(CodigoError.PARAMETERS_REQUIRED_EMPTY)
                        .withMessage(MensajeError.PARAMETERS_REQUIRED_EMPTY.getName())
                        .buildBusinessException();
            }
        } else {
            LOGGER.error("ERROR: BatchValidatorImpl - validateBatch - MESSAGE: {}", MensajeError.NOT_FOUND_METADATA_PENDING.getName());
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.NOT_FOUND_METADATA_EXCEPTION)
                    .withMessage(MensajeError.NOT_FOUND_METADATA_PENDING.getName())
                    .buildBusinessException();
        }
        LOGGER.info("EXECUTE: BatchValidatorImpl - validateBatch - Documentos Validados. Cantidad de Documentos a mover al STAGE: {}", migrationBatch.size());
        aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.INICIADA.getName(), uuid,
                "Documentos Validados. Cantidad de Documentos a mover al STAGE: " + migrationBatch.size(),
                EstadoParametro.ESCANEANDO.getId(), "Carpeta por defecto.", null);
        return migrationBatch;
    }


    private void filterPropertiesRequired(List<Metadatos> batchMigration) throws SystemException {
        for (Iterator<Metadatos> iterator = batchMigration.iterator(); iterator.hasNext(); ) {
            Metadatos metadatos = iterator.next();

            String baseError = "No se encontraron valores en los campos: ";
            int countError = 0;
            if (metadatos.getTipoContenido() == null || metadatos.getTipoContenido().isEmpty()) {
                baseError = baseError.concat("Tipo de Contenido");
                countError++;
            }
            if (metadatos.getTipoDocumental() == null || metadatos.getTipoDocumental().isEmpty()) {
                baseError = baseError.concat(" - Tipo Documental");
                countError++;
            }

            if (metadatos.getEntidad() == null || metadatos.getEntidad().isEmpty()) {
                baseError = baseError.concat(" - Entidad");
                countError++;
            }

            if (metadatos.getSede() == null || metadatos.getSede().isEmpty()) {
                baseError = baseError.concat(" - Sede");
                countError++;
            }

            if (metadatos.getDependenciaJerarquica() == null || metadatos.getDependenciaJerarquica().isEmpty()) {
                baseError = baseError.concat(" - Dependencia Jerarquica");
                countError++;
            }

            if (metadatos.getDependenciaProductora() == null || metadatos.getDependenciaProductora().isEmpty()) {
                baseError = baseError.concat(" - Dependencia Jerarquica");
                countError++;
            }

            if (metadatos.getSerie() == null || metadatos.getSerie().isEmpty()) {
                baseError = baseError.concat(" - Serie");
                countError++;
            }

            if (metadatos.getIdExpediente() == null || metadatos.getIdExpediente().isEmpty()) {
                baseError = baseError.concat(" - Identificador del Expediente");
                countError++;
            }

            if (metadatos.getNombreExpediente() == null || metadatos.getNombreExpediente().isEmpty()) {
                baseError = baseError.concat(" - Nombre del Expediente");
                countError++;
            }

            if (metadatos.getRutaArchivo() == null || metadatos.getRutaArchivo().isEmpty()) {
                baseError = baseError.concat(" - Nombre del Archivo");
                countError++;
            }


            if (countError > 0) {

                ejecucionBoundary.updateExecutionState(metadatos.getUuid(),
                        metadatos.getIdregistro(),
                        EstadoParametro.FALLO,
                        baseError,
                        Etapa.INICIADA);
                iterator.remove();
            }
        }
    }

    private void filterExistsOnExecution(List<Metadatos> metadatos) throws SystemException {
        for (Iterator<Metadatos> iterator = metadatos.iterator(); iterator.hasNext(); ) {
            Metadatos metadato = iterator.next();
            Metadatos documentExistentOnExecution = metadataBoundary.findMetadatoByIdRegistroAndEstadoProceso(metadato.getIdregistro(), EstadoParametro.FINALIZO);
            if (documentExistentOnExecution != null) {
                if (documentExistentOnExecution.getIdregistro() != null && documentExistentOnExecution.getIdregistro()== EstadoParametro.FINALIZO.getId()) {
                    ejecucionBoundary.updateExecutionState(documentExistentOnExecution.getUuid(),
                            metadato.getIdregistro(),
                            EstadoParametro.FALLO,
                            MensajeError.DOCUMENTS_ALREADY_MIGRATED.getName(),
                            Etapa.INICIADA);
                    iterator.remove();
                }
            }
        }


    }


}


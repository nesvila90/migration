package co.gov.sic.migration.service.state.execution.business;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoEndpointManager;
import co.gov.sic.migration.commons.domains.request.EjecucionDTORequest;
import co.gov.sic.migration.commons.domains.request.MetadatosDTORequest;
import co.gov.sic.migration.commons.domains.response.*;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.converter.EjecucionConverter;
import co.gov.sic.migration.commons.utils.converter.MetadatosConverter;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.execution.repository.impl.EjecucionControlImpl;
import co.gov.sic.migration.service.state.metadata.business.MetadataBoundary;
import co.gov.sic.migration.service.state.metadata.repository.impl.MetadatosControlImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EjecucionBoundary {

    private static final Logger LOGGER = LogManager.getLogger(EjecucionBoundary.class);
    private static EstadoParametro INITIAL_STATE = EstadoParametro.ESCANEANDO;
    private final EjecucionControlImpl ejecucionControl;
    private final MetadatosControlImpl metadatosControl;
    private final AlfrescoEndpointManager alfrescoEndpointManager;
    private final MetadataBoundary metadataBoundary;
    private final AplicacionEstadoBoundary aplicacionEstadoBoundary;

    @Autowired
    public EjecucionBoundary(EjecucionControlImpl ejecucionControl, MetadatosControlImpl metadatosControl, AplicacionEstadoBoundary aplicacionEstadoBoundary, AlfrescoEndpointManager alfrescoEndpointManager, MetadataBoundary metadataBoundary, AplicacionEstadoBoundary aplicacionEstadoBoundary1) {
        this.ejecucionControl = ejecucionControl;
        this.metadatosControl = metadatosControl;
        this.alfrescoEndpointManager = alfrescoEndpointManager;
        this.metadataBoundary = metadataBoundary;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary1;
    }


    private List<EjecucionDTOResponse> registerExecutionBatch(final List<Metadatos> metadataBatchUpdated) throws SystemException {
        List<EjecucionDTORequest> executionBatch = new ArrayList<>();
        EstadoDTOResponse e = EstadoDTOResponse.builder()
                .codEstado(INITIAL_STATE.getId())
                .descripcionEstado(INITIAL_STATE.getName())
                .etapa(Etapa.INICIADA.getName()).build();

        for (Metadatos metada : metadataBatchUpdated) {
            EjecucionDTORequest exection = EjecucionDTORequest.builder()
                    .uuid(metada.getUuid())
                    .estadoProceso(e)
                    .idRegistro(metada.getIdregistro())
                    .mensajeEstadoProceso(EstadoParametro.ESCANEANDO.getName()).build();
            executionBatch.add(exection);
        }
        List<EjecucionDTOResponse> executionRegistered = ejecucionControl.registerExecutionBatch(executionBatch);
        if (executionRegistered.size() > 0)
            return executionRegistered;
        else
            return new ArrayList<>();

    }

    public Ejecucion updateExecutionState(String uuid, Integer idRegistro, EstadoParametro estadoParametro, String mensajeEstado, Etapa etapa) throws SystemException {

        EjecucionDTORequest ejecucion = EjecucionDTORequest.builder()
                .uuid(uuid)
                .idRegistro(idRegistro)
                .mensajeEstadoProceso(mensajeEstado)
                .estadoProceso(EstadoDTOResponse.builder()
                        .codEstado(estadoParametro.getId())
                        .descripcionEstado(mensajeEstado)
                        .etapa(etapa.getName()).build())
                .build();

        Metadatos metadata = Metadatos.builder().idregistro(idRegistro).estadoProceso(estadoParametro.getId()).build();

        metadatosControl.updateMetadataStateById(metadata, estadoParametro.getId());
        return ejecucionControl.updateExecutionState(ejecucion);
    }


    public EjecucionDTOResponse registrarEjecucionDocumento(final EjecucionDTORequest ejecucion) throws ConstraintViolationException, SystemException {
        System.out.println("Ejecucuion Prueba " + ejecucion.toString());
        return ejecucionControl.registrarImportacion(ejecucion);
    }

    public List<Ejecucion> mostrarEjecuciones() throws SystemException {
        return ejecucionControl.findEjecuciones();
    }


    public ResponseEntity<MigracionGeneralStatusDTOResponse> estadoEjecucion() {
        return alfrescoEndpointManager.bulkImportStatus();
    }


    public EjecucionDTOResponse modificarEstadoEjecucion(final EjecucionDTORequest ejecucion, Metadatos metadata) throws SystemException {
        LOGGER.info("EjecucionBoundary - modificarEstadoEjecucion: {}", ejecucion.toString());

        MetadatosDTORequest metadataState = MetadatosConverter.entityToDTORequest(metadata);
        metadataBoundary.updateMetadataState(metadataState, ejecucion.getEstadoProceso().getCodEstado());
        return EjecucionConverter.entityToDTOResponse(ejecucionControl.updateExecutionState(ejecucion));
    }


    public EstadoMetadatoDTOResponse modificarEstadoMetadato(final MetadatosDTORequest metadatosRequest,
                                                             int estadoProceso) throws SystemException {
        LOGGER.info("EjecucionBoundary - updateMetadataState: {}", metadatosRequest.toString());
        return metadatosControl.updateMetadataStateById(MetadatosConverter.dtoToEntityRequest(metadatosRequest), estadoProceso);
    }


    public Metadatos buscarMetadato(final int idRegistro) throws SystemException {
        LOGGER.info("EjecucionBoundary - buscarMetadato: {}", idRegistro);
        return metadatosControl.findMetadatoByIdRegistro(idRegistro);
    }

    public void updateExecutionStateByUuid(String uuid, EstadoParametro estadoParametro, String mensajeEstado, Etapa etapa) throws SystemException, BusinessException {

        try {
            List<Metadatos> metadatos = metadataBoundary.findMetadataByUuid(uuid);
            if (!metadatos.isEmpty()) {
                for (Metadatos metadato : metadatos) {
                    EjecucionDTORequest ejecucion = EjecucionDTORequest.builder()
                            .uuid(uuid)
                            .idRegistro(metadato.getIdregistro())
                            .mensajeEstadoProceso(mensajeEstado)
                            .estadoProceso(EstadoDTOResponse.builder()
                                    .codEstado(estadoParametro.getId())
                                    .descripcionEstado(mensajeEstado)
                                    .etapa(etapa.getName()).build())
                            .build();

                    Metadatos metadata = Metadatos.builder().idregistro(metadato.getIdregistro()).estadoProceso(estadoParametro.getId()).build();

                    metadatosControl.updateMetadataStateById(metadata, estadoParametro.getId());
                    ejecucionControl.updateExecutionState(ejecucion);
                }
            }
        } catch (Exception nr) {
            System.out.println("ERROR ACTUALIZANDO: TODOS LOS REGISTROS TERMINANDO EJECUCION.");
            nr.printStackTrace();
        }


    }

    public Ejecucion findByIdRegistro(final Integer idRegistro) throws SystemException {
        return ejecucionControl.findByIdRegistro(idRegistro);
    }



    }

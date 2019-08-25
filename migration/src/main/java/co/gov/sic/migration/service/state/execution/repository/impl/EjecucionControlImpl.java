package co.gov.sic.migration.service.state.execution.repository.impl;

import co.gov.sic.migration.commons.domains.request.EjecucionDTORequest;
import co.gov.sic.migration.commons.domains.response.EjecucionDTOResponse;
import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.utils.converter.EjecucionConverter;
import co.gov.sic.migration.commons.utils.converter.EstadoParametroFinder;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Estados;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.JDBCException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;


@Component
public class EjecucionControlImpl {

    private static final Logger LOGGER = LogManager.getLogger(EjecucionControlImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EjecucionDTOResponse registrarImportacion(final EjecucionDTORequest ejecucion) throws ConstraintViolationException, SystemException {
        try {

            LOGGER.info("EjecucionControlImpl - registrarImportacion: {}", ejecucion.toString());
            Ejecucion e = EjecucionConverter.requestToEntity(ejecucion);
            em.persist(e);
            return EjecucionConverter.converterDtoRequestToDTOResponse(ejecucion);
        } catch (JDBCException e) {
            return null;
        } catch (Exception ex) {
            LOGGER.error(MensajeError.SYSTEM_EXCEPTION.getName(), ex);
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage(MensajeError.GENERIC_ERROR.getName()).withRootException(ex.getCause()).buildSystemException();
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<EjecucionDTOResponse> registerExecutionBatch(final List<EjecucionDTORequest> executionBatch) throws SystemException {
        try {
            LOGGER.info("EjecucionControlImpl - registerExecutionBatch: {}", executionBatch.toString());
            List<EjecucionDTOResponse> executionsCompleted = new ArrayList<>();
            for (EjecucionDTORequest execution : executionBatch) {
                Ejecucion e = EjecucionConverter.requestToEntity(execution);
                em.persist(e);
                executionsCompleted.add(EjecucionConverter.entityToDTOResponse(e));
            }
            return executionsCompleted;
        } catch (JDBCException e) {
            LOGGER.error("EjecucionControlImpl - registerExecutionBatch: " +
                    "ERROR -  Execution Batch {} - Exception {}", executionBatch.toString(), e.getCause());
            return null;
        } catch (Exception ex) {
            LOGGER.error("EjecucionControlImpl - registerExecutionBatch: " +
                    "ERROR -  Execution Batch {} - Exception {}", MensajeError.SYSTEM_EXCEPTION.getName(), ex);
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage(MensajeError.GENERIC_ERROR.getName())
                    .withRootException(ex.getCause()).buildSystemException();
        }

    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Ejecucion> findEjecuciones() throws SystemException {
        try {
            return em.createNamedQuery("Ejecucion.findEjecuciones").getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("EjecucionControlImpl - findEjecuciones: No se encontraron ejecuciones");
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error("EjecucionControlImpl - findEjecuciones: Error en la busqueda de ejecuciones{}", e);
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage("No se encontraron ejecuciones.").withRootException(e.getCause()).buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Ejecucion findByIdRegistro(final Integer idRegistro) throws SystemException {
        LOGGER.info("EjecucionBoundary - findByIdRegistro: Buscando por IdRegistro: {}", idRegistro);
        try {
            return em.createNamedQuery("Ejecucion.findByIdRegistro", Ejecucion.class)
                    .setParameter("idRegistro", idRegistro)
                    .getSingleResult();
        } catch (NoResultException nr) {
            LOGGER.warn("EjecucionControlImpl - findByIdRegistro: No se encontraron Ejecucion por idRegisto.", idRegistro);
            return new Ejecucion();
        } catch (Exception ex) {
            LOGGER.error("EjecucionControlImpl - findByIdRegistro: Error en la busqueda de ejecuciones por idRegistro", idRegistro);
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage("No se encontraron Ejecucion por idRegisto.").withRootException(ex.getCause()).buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ejecucion updateExecutionState(final EjecucionDTORequest ejecucion) throws SystemException {
        LOGGER.info("EjecucionBoundary - updateExecutionState: {}", ejecucion.toString());
        try {
            Ejecucion execution = findByIdRegistro(ejecucion.getIdRegistro());
            execution.setIdRegistro(ejecucion.getIdRegistro());
            execution.setUuid(ejecucion.getUuid());
            execution.setCodEstadoProceso(Estados.builder().codEstado(ejecucion.getEstadoProceso().getCodEstado())
                    .descripcionEstado(ejecucion.getMensajeEstadoProceso())
                    .etapa(ejecucion.getEstadoProceso().getEtapa())
                    .build());
            execution.setMensajeEstadoProceso(ejecucion.getMensajeEstadoProceso());

            em.merge(execution);
            em.flush();
            return execution;
        } catch (NoResultException nr) {
            LOGGER.warn("EjecucionControlImpl - updateExecutionState: No se encontraron ejecuciones para actulizar{}", ejecucion.toString());
            return new Ejecucion();
        } catch (SystemException e) {
            LOGGER.warn("EjecucionControlImpl - updateExecutionState: Error General{}", e);
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.GENERAL_EXCEPTION)
                    .withMessage("Error al actualizar estado del Metadatos.")
                    .withRootException(e)
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Ejecucion findLastEjecucionUuid() throws SystemException {
        try {
            return em.createNamedQuery("Ejecucion.findByFecha", Ejecucion.class)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (NoResultException nr) {
            return new Ejecucion();
        } catch (Exception e) {
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.GENERAL_EXCEPTION)
                    .withMessage("No se encontraron Registros.")
                    .withRootException(e)
                    .buildSystemException();
        }

    }

    private Ejecucion createExecution(String uuid, EstadoParametro estado, Integer idRegistro, Integer estadoConsulta) {
        String estadoEjecucion = EstadoParametroFinder.getEstadoParametroName(estado);
        return Ejecucion.builder()
                .uuid(uuid)
                .mensajeEstadoProceso(estadoEjecucion)
                .idRegistro(idRegistro)
                .codEstadoProceso(Estados.builder()
                        .codEstado(estadoConsulta)
                        .descripcionEstado(estadoEjecucion)
                        .etapa("En ejecución").build())
                .build();
    }

}

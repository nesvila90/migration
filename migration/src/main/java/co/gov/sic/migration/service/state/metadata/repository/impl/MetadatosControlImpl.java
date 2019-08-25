package co.gov.sic.migration.service.state.metadata.repository.impl;

import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.domains.response.MetadatosDTOResponse;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.utils.converter.EstadoParametroFinder;
import co.gov.sic.migration.commons.utils.converter.MetadatosConverter;
import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.state.metadata.repository.MetadatosControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Component
public class MetadatosControlImpl implements MetadatosControl {

    private static final Logger LOGGER = LogManager.getLogger(MetadatosControlImpl.class);

    @PersistenceContext
    private EntityManager em;

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Metadatos> findMetadatosByUuid(final String uuid) throws SystemException {
        try {
            return em.createNamedQuery("Metadatos.findByUuid", Metadatos.class)
                    .setParameter("UUID", uuid)
                    .getResultList();
        } catch (NoResultException nr) {
            return new ArrayList<>();
        } catch (Exception e) {
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(e.getCause())
                    .buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<EstadoMetadatoDTOResponse> findMetadataExecutionStateByUuid(final String uuid) throws SystemException {
        try {
            LOGGER.info("MetadatosControlImpl - findMetadataExecutionStateByUuid: uuid:{}", uuid);
            return em.createNamedQuery("Metadatos.consultaEstadoMetadatosPorUuid", EstadoMetadatoDTOResponse.class)
                    .setParameter("UUID", uuid)
                    .getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadataExecutionStateByUuid: " +
                    "No se encontraron registros por uuid:{}", uuid);
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error("GENERAL EXCEPTION: MetadatosControlImpl - findMetadataExecutionStateByUuid: " +
                    "No se encontraron registros por uuid:{} - Exception: {}", uuid, e.getCause());
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(e.getCause())
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Metadatos findMetadatoByIdRegistro(final int idRegistro) throws SystemException {
        LOGGER.info("MetadatosControlImpl - findMetadatoByIdRegistro: idRegistro:{}", idRegistro);
        try {
            return em.createNamedQuery("Metadatos.findByIdregistro", Metadatos.class)
                    .setParameter("idregistro", idRegistro)
                    .getSingleResult();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadatoByIdRegistro: " +
                    "No se encontraron registros por idRegistro:{}", idRegistro);
            return new Metadatos();
        } catch (Exception e) {
            LOGGER.error("GENERAL EXCEPTION: MetadatosControlImpl - findMetadatoByIdRegistro: " +
                    "No se encontraron registros por idRegistro:{} - Exception: {}", idRegistro, e.getCause());
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(e.getCause())
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Metadatos findMetadatoByIdRegistroAndEstadoProceso(final int idRegistro, EstadoParametro estado) throws SystemException {
        LOGGER.info("MetadatosControlImpl - findMetadatoByIdRegistroAndEstadoProceso: idRegistro:{} - estado: {}", idRegistro, estado);
        try {
            return em.createNamedQuery("Metadatos.findByIdregistroAndEstadoProceso", Metadatos.class)
                    .setParameter("idregistro", idRegistro)
                    .setParameter("estado", Estados.builder().codEstado(estado.getId()).descripcionEstado(estado.getName()).build())
                    .getSingleResult();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadatoByIdRegistro: " +
                    "No se encontraron registros por idRegistro:{}", idRegistro);
            return new Metadatos();
        } catch (Exception e) {
            LOGGER.error("GENERAL EXCEPTION: MetadatosControlImpl - findMetadatoByIdRegistroAndEstadoProceso: " +
                    "No se encontraron registros por idRegistro:{} - Exception: {}", idRegistro, e.getCause());
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(e.getCause())
                    .buildSystemException();
        }
    }

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Metadatos> findMetadatosByStateAndQuantityResults(final EstadoParametro estado, final Integer cantResultado) throws SystemException {
        LOGGER.info("MetadatosControlImpl - findMetadatosByStateAndQuantityResults: Estado: {} Cantidad:{}", estado.toString(), cantResultado);
        try {
            Integer estadoBusqueda = EstadoParametroFinder.getEstadoParametroId(estado);
            return em.createNamedQuery("Metadatos.findByEstadoProceso", Metadatos.class)
                    .setParameter("estadoProceso", estadoBusqueda)
                    .setMaxResults(cantResultado)
                    .getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadatosByStateAndQuantityResults: " +
                    "No se encontraron registros por Estado: {} y Cantidad:{}", estado.toString(), cantResultado);
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error("GENERAL EXCEPTION: MetadatosControlImpl - findMetadatosByStateAndQuantityResults: " +
                    "No se encontraron registros por Estado: {} y Cantidad:{} - Exception: {}", estado.toString(), cantResultado, e.getCause());
            e.printStackTrace();
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(e.getCause())
                    .buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public EstadoMetadatoDTOResponse updateMetadataStateById(final Metadatos datos, final Integer codigoEstadoProceso) throws SystemException {
        LOGGER.info("MetadatosControlImpl - updateMetadataStateById: Metadatos{} EstadoProceso{}", datos.toString(), codigoEstadoProceso);
        try {
            Metadatos metadatos = findMetadatoByIdRegistro(datos.getIdregistro());
            LOGGER.info("MetadatosControlImpl - updateMetadataStateById - findMetadatoByIdRegistro : Metadatos{} EstadoProceso{}", metadatos.toString(), codigoEstadoProceso);
            if (metadatos.getIdregistro() != null) {
                metadatos.setEstadoProceso(codigoEstadoProceso);
                em.merge(metadatos);
                em.flush();
                MetadatosDTOResponse metadatosDTOResponse = MetadatosConverter.entityToDTOResponse(metadatos);
                return EstadoMetadatoDTOResponse.builder()
                        .uuid(metadatosDTOResponse.getUuid())
                        .estadoProceso(metadatosDTOResponse.getEstadoProceso().toString())
                        .nombreArchivo(metadatosDTOResponse.getRutaArchivo())
                        .build();
            } else {
                LOGGER.warn("WARN: MetadatosControlImpl - updateMetadataStateById - findMetadatoByIdRegistro : NO ENCONTRO REGISTRO POR ACUALIZAR- Metadato: {}", metadatos.toString());
                return null;
            }
        } catch (Exception be) {
            throw ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName())
                    .withRootException(be.getCause()).buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Metadatos> findMetadataByState(Integer estado) throws SystemException {
        LOGGER.info("MetadatosControlImpl - findMetadataByState - Parameters - EstadoProceso: {}", estado);
        try {
            return em.createNamedQuery("Metadatos.findByEstadoProceso", Metadatos.class)
                    .setParameter("estadoProceso", estado)
                    .getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadataByState - NO RESULTS BY estado: {}", estado);
            return new ArrayList<>();
        } catch (Exception e) {
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName()).withRootException(e.getCause())
                    .buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<Metadatos> updateMetadataBatch(final List<Metadatos> metadatos, final EstadoParametro estadoFinal, String uuid) throws SystemException {
        try {
            LOGGER.info("MetadatosControlImpl - updateMetadataBatch - Parameters - metadatos: {} - estadoFinal: {} - uuid: {}", metadatos.stream().map(metadatos1 -> em.toString()), uuid);
            if (metadatos != null && metadatos.size() > 0)
                for (Metadatos m : metadatos) {
                    m.setUuid(uuid);
                    m.setEstadoProceso(EstadoParametroFinder.getEstadoParametroId(estadoFinal));
                    em.merge(m);
                }
            em.flush();
            return metadatos;
        } catch (Exception be) {
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.GENERAL_EXCEPTION)
                    .withMessage(be.getMessage()).withRootException(be.getCause()).buildSystemException();
        }
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<Metadatos> findMetadataByState(final EstadoParametro estado) throws SystemException {

        try {
            return em.createNamedQuery("Metadatos.findByEstadoProceso", Metadatos.class)
                    .setParameter("estadoProceso", EstadoParametroFinder.getEstadoParametroId(estado))
                    .getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadataByState - NO RESULTS BY estado: {}", estado);
            return new ArrayList<>();
        } catch (Exception e) {
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.BUSINESS_EXCEPTION)
                    .withMessage(MensajeError.SYSTEM_EXCEPTION.getName()).withRootException(e.getCause())
                    .buildSystemException();
        }

    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public List<EstadoMetadatoDTOResponse> findMetadataExecutionStateGeneral() throws SystemException {
        try {
            LOGGER.info("MetadatosControlImpl - findMetadataExecutionStateGeneral.");
            return em.createNamedQuery("Metadatos.consultaEstadoMetadatosGeneral", EstadoMetadatoDTOResponse.class)
                    .getResultList();
        } catch (NoResultException nr) {
            LOGGER.warn("MetadatosControlImpl - findMetadataExecutionStateGeneral: " +
                    "No se encontraron registros de Ejecución");
            return new ArrayList<>();
        } catch (Exception e) {
            LOGGER.error("MetadatosControlImpl - findMetadataExecutionStateGeneral: Error en la busqueda de ejecuciones{}", e);
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.PERSISTENCE_EXCEPTION)
                    .withMessage("No se encontraron ejecuciones.").withRootException(e.getCause()).buildSystemException();
        }
    }





}

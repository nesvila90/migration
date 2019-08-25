package co.gov.sic.migration.service.state.application.repository.impl;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.utils.converter.AplicacionEstadoConverter;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.service.state.application.repository.AplicacionEstadoControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Component
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AplicacionEstadoControlImpl implements AplicacionEstadoControl {

    private static final Logger LOGGER = LogManager.getLogger(AplicacionEstadoControl.class);

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public MigrationStatusDTOResponse findAplicacionEstado() {
        LOGGER.info("EXECUTION: - AplicacionEstadoControlImpl - findAplicacionEstado()");
        try {
            AplicacionEstado aplicacionEstado = em.createNamedQuery("AplicacionEstado.findByLastIdEjecucion", AplicacionEstado.class)
                    .setMaxResults(1)
                    .getSingleResult();
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(aplicacionEstado);
        } catch (NoResultException nr) {
            LOGGER.warn("AplicacionEstadoControlImpl - findAplicacionEstado() WARN: No se encontraron resultados de la útlima ejecucion.{}", nr.getMessage());
            return new MigrationStatusDTOResponse();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MigrationStatusDTOResponse registerAplicacionEstado(AplicacionEstado aplicacionEstado) {
        LOGGER.info("EXECUTION: AplicacionEstadoControlImpl - registerAplicacionEstado() {}", aplicacionEstado.toString());
        try {
            AplicacionEstado ap = em.createNamedQuery("AplicacionEstado.findByIdEjecucion", AplicacionEstado.class)
                    .setParameter("ID_EJECUCION", 1)
                    .getSingleResult();
            ap.setEstadoMigracion(aplicacionEstado.getEstadoMigracion());
            ap.setEstadoGeneralMigracion(aplicacionEstado.getEstadoGeneralMigracion());
            ap.setDescripcionEstadoMigracion(aplicacionEstado.getDescripcionEstadoMigracion());
            ap.setCarpetaMigracion(aplicacionEstado.getCarpetaMigracion());
            ap.setUuid(aplicacionEstado.getUuid());
            em.merge(ap);
            em.flush();
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(ap);
        } catch (EntityExistsException | IllegalArgumentException | TransactionRequiredException nr) {
            LOGGER.error("AplicacionEstadoControlImpl - registerAplicacionEstado() ERROR: {}", nr.getMessage());
            return MigrationStatusDTOResponse.builder().build();
        } catch (Exception e) {
            AplicacionEstado appEstado = new AplicacionEstado();
            appEstado.setIdEjecucion(1);
            appEstado.setUuid(aplicacionEstado.getUuid());
            appEstado.setCarpetaMigracion(aplicacionEstado.getCarpetaMigracion());
            appEstado.setDescripcionEstadoMigracion(aplicacionEstado.getDescripcionEstadoMigracion());
            appEstado.setEstadoGeneralMigracion(aplicacionEstado.getEstadoGeneralMigracion());
            Estados estados = new Estados();
            estados.setCodEstado(aplicacionEstado.getEstadoMigracion().getCodEstado());
            estados.setDescripcionEstado(aplicacionEstado.getEstadoMigracion().getDescripcionEstado());
            estados.setEtapa(aplicacionEstado.getEstadoMigracion().getEtapa());
            appEstado.setEstadoMigracion(estados);
            em.flush();
            em.persist(appEstado);
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(appEstado);

        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MigrationStatusDTOResponse updateAplicacionEstado(AplicacionEstado aplicacionEstado) {
        try {
            LOGGER.info("AplicacionEstadoControlImpl - updateAplicacionEstado() {}", aplicacionEstado.toString());
            AplicacionEstado a = em.createNamedQuery("AplicacionEstado.findByUuid", AplicacionEstado.class)
                    .setParameter("uuid", aplicacionEstado.getUuid())
                    .getSingleResult();
            mergeEstado(a, aplicacionEstado);
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(a);
        } catch (NoResultException nr) {
            LOGGER.error("AplicacionEstadoControlImpl - updateAplicacionEstado() NOT FOUND ENTITY TO UPDATE: {}", aplicacionEstado.toString());
            return new MigrationStatusDTOResponse();
        }

    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public MigrationStatusDTOResponse updateAplicacionEstado(AplicacionEstado aplicacionEstado, Integer id) {
        try {
            LOGGER.info("AplicacionEstadoControlImpl - updateAplicacionEstado() {}", aplicacionEstado.toString());
            AplicacionEstado a = em.createNamedQuery("AplicacionEstado.findByLastIdEjecucion", AplicacionEstado.class)
                    .setParameter("ID_EJECUCION", 1)
                    .getSingleResult();
            mergeEstado(a, aplicacionEstado);
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(a);
        } catch (NoResultException nr) {
            LOGGER.error("AplicacionEstadoControlImpl - updateAplicacionEstado() NOT FOUND ENTITY TO UPDATE: {}", aplicacionEstado.toString());
            return new MigrationStatusDTOResponse();
        }

    }

    private void mergeEstado(AplicacionEstado a, AplicacionEstado aplicacionEstado){
        a.setCarpetaMigracion(aplicacionEstado.getCarpetaMigracion());
        a.setDescripcionEstadoMigracion(aplicacionEstado.getDescripcionEstadoMigracion());
        a.setEstadoGeneralMigracion(aplicacionEstado.getEstadoGeneralMigracion());
        a.setEstadoMigracion(aplicacionEstado.getEstadoMigracion());
        em.merge(a);
    }


    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public MigrationStatusDTOResponse findLastAplicacionEstado() {
        LOGGER.info("EXECUTION: - AplicacionEstadoControlImpl - findLastAplicacionEstado()");
        try {
            AplicacionEstado aplicacionEstado = em.createNamedQuery("AplicacionEstado.findAll", AplicacionEstado.class)
                    .setMaxResults(1)
                    .getSingleResult();
            return AplicacionEstadoConverter.AplicacionEstadoEntityToDto(aplicacionEstado);
        } catch (NoResultException nr) {
            LOGGER.warn("AplicacionEstadoControlImpl - findAplicacionEstado() WARN: No se encontraron resultados de la útlima ejecucion.{}", nr.getMessage());
            return null;
        }
    }


}

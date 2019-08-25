package co.gov.sic.migration.service.state.application.business.impl;

import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.utils.converter.AplicacionEstadoConverter;
import co.gov.sic.migration.commons.utils.converter.EstadosConverter;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.application.repository.AplicacionEstadoControl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AplicacionEstadoBoundaryImpl implements AplicacionEstadoBoundary {

    private static final Logger LOGGER = LogManager.getLogger(AplicacionEstadoBoundaryImpl.class);


    private AplicacionEstadoControl aplicacionEstadoControl;

    @Autowired
    public AplicacionEstadoBoundaryImpl(AplicacionEstadoControl aplicacionEstadoControl) {
        this.aplicacionEstadoControl = aplicacionEstadoControl;
    }

    @Override
    public MigrationStatusDTOResponse findLastAplicacionEstado() {
        return aplicacionEstadoControl.findAplicacionEstado();
    }

    @Override
    public MigrationStatusDTOResponse registerAplicacionEstado(AplicacionEstado aplicacionEstado) {
        return aplicacionEstadoControl.registerAplicacionEstado(aplicacionEstado);
    }

    @Override
    public MigrationStatusDTOResponse updateAplicacionEstado(AplicacionEstado aplicacionEstado) throws BusinessException {
        return aplicacionEstadoControl.updateAplicacionEstado(aplicacionEstado);
    }

    @Override
    public void updateGeneralStateAplicaction(String etapa, String uuid, String descripcionEstado, Integer codEstado, String carpeta, String descripcionMigracion) {
        LOGGER.info("EXECUTE: AplicacionEstadoBoundaryImpl - updateGeneralStateAplicaction - etapa: {} - uuid: {} - descripcionEstado: {} - codEstado: {} - carpeta: {} - descripcionMigracion {}", etapa, uuid, descripcionEstado, codEstado, carpeta, descripcionMigracion);
        AplicacionEstado aplicacionEstado = AplicacionEstado.builder().uuid(uuid).descripcionEstadoMigracion(descripcionMigracion)
                .estadoGeneralMigracion(etapa)
                .estadoMigracion(Estados.builder().codEstado(codEstado).descripcionEstado(descripcionEstado).etapa(etapa).build())
                .carpetaMigracion(carpeta).build();
        aplicacionEstadoControl.updateAplicacionEstado(aplicacionEstado);
    }

    @Override
    public void registerGeneralStateAplicaction(String etapa, String uuid, String descripcionEstado, Integer codEstado, String carpeta, String descripcionMigracion) {
        AplicacionEstado aplicacionEstado = AplicacionEstado.builder()
                .uuid(uuid).descripcionEstadoMigracion(descripcionMigracion).estadoGeneralMigracion(etapa)
                .estadoMigracion(Estados.builder()
                        .codEstado(codEstado)
                        .descripcionEstado(descripcionEstado)
                        .etapa(etapa).build())
                .carpetaMigracion(carpeta).build();
        aplicacionEstadoControl.registerAplicacionEstado(aplicacionEstado);
    }


    public GeneralStatus findCurrentAplicacionEstado() {
        return AplicacionEstadoConverter.AplicacionEstadoDtoToGeneralStatus(aplicacionEstadoControl.findAplicacionEstado());
    }
}

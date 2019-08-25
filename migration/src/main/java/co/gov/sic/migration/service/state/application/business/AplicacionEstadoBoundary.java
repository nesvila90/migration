package co.gov.sic.migration.service.state.application.business;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;

public interface AplicacionEstadoBoundary {

    MigrationStatusDTOResponse findLastAplicacionEstado();
    MigrationStatusDTOResponse registerAplicacionEstado(AplicacionEstado aplicacionEstado);
    MigrationStatusDTOResponse updateAplicacionEstado(AplicacionEstado aplicacionEstado) throws BusinessException;
    void updateGeneralStateAplicaction(String etapa, String uuid, String descripcionEstado, Integer codEstado, String carpeta, String descripcionMigracion) ;
    void registerGeneralStateAplicaction(String etapa, String uuid, String descripcionEstado, Integer codEstado, String carpeta, String descripcionMigracion);
}

package co.gov.sic.migration.service.state.application.repository;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;

public interface AplicacionEstadoControl {
    MigrationStatusDTOResponse findAplicacionEstado();
    MigrationStatusDTOResponse  registerAplicacionEstado(AplicacionEstado aplicacionEstado);
    MigrationStatusDTOResponse  updateAplicacionEstado(AplicacionEstado aplicacionEstado);
}

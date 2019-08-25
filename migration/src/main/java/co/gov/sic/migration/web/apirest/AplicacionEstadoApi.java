package co.gov.sic.migration.web.apirest;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;

public interface AplicacionEstadoApi {

    MigrationStatusDTOResponse findLastAplicacionEstado() throws BusinessException;
    MigrationStatusDTOResponse registerAplicacionEstado(AplicacionEstado aplicacionEstado);
    MigrationStatusDTOResponse updateAplicacionEstado(AplicacionEstado aplicacionEstado) throws BusinessException;
}

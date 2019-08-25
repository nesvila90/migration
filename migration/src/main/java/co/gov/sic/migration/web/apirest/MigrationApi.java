package co.gov.sic.migration.web.apirest;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.SystemException;

public interface MigrationApi {

    MigrationStatusDTOResponse startMigration(final IniciarDTOResquest startMigrationRequest) throws SystemException;

    MigrationStatusDTOResponse stopMigration() throws SystemException;
}

package co.gov.sic.migration.thread.manager;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;

import java.util.concurrent.ExecutionException;

public interface MigrationThread {

    MigrationStatusDTOResponse startMigration(IniciarDTOResquest startMigrationRequest) throws ExecutionException, InterruptedException;

    MigrationStatusDTOResponse stopMigration() throws ExecutionException, InterruptedException;
}

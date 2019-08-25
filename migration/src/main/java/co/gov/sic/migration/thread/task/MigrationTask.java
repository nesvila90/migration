package co.gov.sic.migration.thread.task;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;

import java.io.IOException;

public interface MigrationTask  {

    Runnable migrationTaskStart(final IniciarDTOResquest iniciar) throws InterruptedException, BusinessException, IOException, SystemException;
    void migrationTaskStop();
    void setMigrationRequest(IniciarDTOResquest migrationRequest);

}

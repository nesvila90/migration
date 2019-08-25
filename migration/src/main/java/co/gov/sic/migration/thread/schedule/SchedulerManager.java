package co.gov.sic.migration.thread.schedule;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;

public interface SchedulerManager {

    MigrationStatusDTOResponse startScheduledTask(Runnable task, Long delay) throws Exception;
    void stopScheduledTask() throws Exception;

}

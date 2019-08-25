package co.gov.sic.migration.thread.manager.impl;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.thread.task.MigrationTask;
import co.gov.sic.migration.thread.manager.MigrationThread;
import co.gov.sic.migration.thread.schedule.SchedulerManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class MigrationThreadImpl implements MigrationThread {


    @Autowired
    private MigrationTask migrationTask;

    @Autowired
    private SchedulerManager schedulerManager;

    @Autowired
    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    private ScheduledFuture<?> scheduledFutureTask;

    @Override
    public MigrationStatusDTOResponse startMigration(IniciarDTOResquest startMigrationRequest) {
        try {
            System.out.println("Migration request: " + startMigrationRequest.toString());
            aplicacionEstadoBoundary.registerGeneralStateAplicaction(Etapa.INICIADA.getName(), "", "Iniciando Ejecución", 1, "", "En Ejecución");
            return schedulerManager.startScheduledTask(
                    migrationTask.migrationTaskStart(startMigrationRequest),
                    TimeUnit.MINUTES.toMillis(startMigrationRequest.getIntervaloEjecucion()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public MigrationStatusDTOResponse stopMigration() {
        try {
            MigrationStatusDTOResponse migrationStatus = scheduledFutureTask == null ?
                    new MigrationStatusDTOResponse() :
                    (MigrationStatusDTOResponse) scheduledFutureTask.get();
            migrationTask.migrationTaskStop();
            schedulerManager.stopScheduledTask();
            return migrationStatus;
        } catch (Exception e) {

            e.printStackTrace();
            return new MigrationStatusDTOResponse();
        }

    }
}

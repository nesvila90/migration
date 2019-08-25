package co.gov.sic.migration.thread.schedule.impl;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.enums.Etapa;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.configuration.SchedulerConfig;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.thread.schedule.SchedulerManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;


@Component
@EnableScheduling
public class SchedulerManagerImpl implements SchedulerManager {

    private static final Logger LOGGER = LogManager.getLogger(SchedulerManagerImpl.class);


    private SchedulerConfig scheduler;

    private ScheduledFuture scheduledFuture;

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    private EjecucionBoundary ejecucionBoundary;

    @Autowired
    public SchedulerManagerImpl(SchedulerConfig scheduler, AplicacionEstadoBoundary aplicacionEstadoBoundary) {
        this.scheduler = scheduler;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
    }

    @Async("asynchExecutor")
    @Override
    public MigrationStatusDTOResponse startScheduledTask(Runnable task, Long executionTimeInterval)
            throws RejectedExecutionException, ExecutionException, BusinessException, SystemException {
        System.out.println(" SchedulerManagerImpl Migration request: Runnable Value: " + task + "Interval: " + executionTimeInterval);

        this.scheduledFuture = scheduler.taskScheduler().scheduleAtFixedRate(task, executionTimeInterval);
        MigrationStatusDTOResponse status = new MigrationStatusDTOResponse();
        try {
            return (MigrationStatusDTOResponse) scheduledFuture
                    .get();
        } catch (InterruptedException | CancellationException e) {
            //e.printStackTrace();
            System.out.println("Migración Detenida.");
            LOGGER.warn("EXECUTE: MigrationExecutorImpl - stopExecution -  MIGRATION STOPPED!! ---> OK ");
            //try {
                MigrationStatusDTOResponse migrationStatus = aplicacionEstadoBoundary.findLastAplicacionEstado();
                aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.FINALIZADA.getName(), migrationStatus.getUuid(), EstadoParametro.DETENIDO.getName(),
                        EstadoParametro.DETENIDO.getId(), AlfrescoBundleUtil.getProperty("host.folder").concat(migrationStatus.getUuid()),
                        "Migración Detenida - Los lotes en cola han sido cancelados.");
            /*}catch (Exception ex){
                System.out.println("ERROR DETENIENDO MIGRACION.");

                ex.printStackTrace();

            }*/
            return status;
        }
    }


    @Override
    public void stopScheduledTask() throws RejectedExecutionException {
        try {
            System.out.println("Ejecutando : Deteniendo.");
            if (scheduler != null) {
                scheduledFuture.cancel(false);
            }
        } catch (NullPointerException e) {
            System.out.println("Empty Execution.");
            MigrationStatusDTOResponse migrationStatus = aplicacionEstadoBoundary.findLastAplicacionEstado();
            aplicacionEstadoBoundary.updateGeneralStateAplicaction(Etapa.FINALIZADA.getName(), migrationStatus.getUuid(), EstadoParametro.DETENIDO.getName(),
                    EstadoParametro.DETENIDO.getId(), AlfrescoBundleUtil.getProperty("host.folder").concat(migrationStatus.getUuid()),
                    "Migración Detenida - Los lotes en cola han sido cancelados.");
        }
    }

}

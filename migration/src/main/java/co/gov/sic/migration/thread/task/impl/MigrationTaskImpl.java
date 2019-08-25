package co.gov.sic.migration.thread.task.impl;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.migration.manager.executor.MigrationExecutor;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.service.state.metadata.repository.impl.MetadatosControlImpl;
import co.gov.sic.migration.thread.task.MigrationTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class MigrationTaskImpl implements MigrationTask {

    private MigrationExecutor migrationExecutor;

    private MetadatosControlImpl metadatosControl;

    private IniciarDTOResquest migrationRequest;

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    private Boolean isStopped;

    @Autowired
    public MigrationTaskImpl(MigrationExecutor migrationExecutor, EjecucionBoundary ejecucionBoundary, MetadatosControlImpl metadatosControl, AplicacionEstadoBoundary aplicacionEstadoBoundary) {
        this.migrationExecutor = migrationExecutor;
        this.metadatosControl = metadatosControl;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
        this.isStopped = false;
    }

    public void setMigrationRequest(IniciarDTOResquest migrationRequest) {
        this.migrationRequest = migrationRequest;
    }


    public Runnable migrationTaskStart(final IniciarDTOResquest iniciar) {
        isStopped = false;
        System.out.println("is Stopped :" + isStopped);
        return () -> {
            System.out.println("Runnable - MigrationTaskStart -Start Migration Request:  " + iniciar.toString());
            System.out.println("Runnable -Start Migration Request:  " + iniciar.toString());
            if (pendingDocumentsProcessMigration(iniciar.getCantidad())) {
                try {
                    migrationExecutor.startExecution(iniciar);
                    System.out.println("Tarea Terminada...");
                } catch (BusinessException | IOException | SystemException e) {
                    e.printStackTrace();
                }
            }
        };
    }

    public void migrationTaskStop() {
        System.out.println("Stopping ");
        isStopped = true;
        //Thread.currentThread().interrupt();
        try {
            migrationExecutor.stopExecution();
        } catch (BusinessException e) {
            e.printStackTrace();
        }
    }

    private Boolean pendingDocumentsProcessMigration(final Integer quantityDocuments) {
        try {
            List<Metadatos> filesPending = metadatosControl.findMetadatosByStateAndQuantityResults(EstadoParametro.PENDIENTE,  quantityDocuments);
            System.out.println("Numero de Documentos Pendientes: " + filesPending.size() + "Request " + quantityDocuments);
            if ((filesPending.size() > 0)) {
                System.out.println("Numero de Documentos Pendientes a procesar: " + filesPending.size());
                return true;
            }
            return false;

        } /*catch (BusinessException e) {
            e.printStackTrace();
            return false;
        }*/ catch (SystemException e) {
            e.printStackTrace();
            return false;
        }
    }


}

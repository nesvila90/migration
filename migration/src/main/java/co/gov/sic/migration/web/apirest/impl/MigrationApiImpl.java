package co.gov.sic.migration.web.apirest.impl;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.thread.manager.MigrationThread;
import co.gov.sic.migration.web.apirest.MigrationApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST,  RequestMethod.DELETE})
@RequestMapping(value = "managerMigration/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class MigrationApiImpl implements MigrationApi {

    private final MigrationThread migrationThread;

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    @Autowired
    public MigrationApiImpl(MigrationThread migrationThread, AplicacionEstadoBoundary aplicacionEstadoBoundary) {
        this.migrationThread = migrationThread;
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
    }

    @Override
    @PostMapping("startMigration")
    public MigrationStatusDTOResponse startMigration(@RequestBody final IniciarDTOResquest startMigrationRequest) throws SystemException {

        try {
            System.out.println("StartMigration: " + startMigrationRequest.toString());
            System.out.println("Migration Request: " + startMigrationRequest.toString());
            return migrationThread.startMigration(startMigrationRequest);
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.NOT_FOUND_METADATA_EXCEPTION).withMessage("").buildSystemException();
        }
    }

    @Override
    @DeleteMapping("stopMigration")
    public MigrationStatusDTOResponse stopMigration() throws SystemException {
        try {
            return migrationThread.stopMigration();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
            throw ExceptionBuilder.newBuilder().withCode(CodigoError.NOT_FOUND_METADATA_EXCEPTION).withMessage("").buildSystemException();
        }
    }


    @GetMapping("statusMigration")
    public MigrationStatusDTOResponse statusMigration() throws SystemException {
            return aplicacionEstadoBoundary.findLastAplicacionEstado();

    }
}

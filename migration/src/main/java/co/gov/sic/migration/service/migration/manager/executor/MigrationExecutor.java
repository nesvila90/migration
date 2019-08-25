package co.gov.sic.migration.service.migration.manager.executor;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.enums.EstadoParametro;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public interface MigrationExecutor {

    void startExecution(final IniciarDTOResquest request) throws IndexOutOfBoundsException, BusinessException,
            IOException, SystemException;
    void stopExecution() throws BusinessException;
    void updateExecutionState(final String uuid, final Metadatos fileImport, final EstadoParametro estadoParametro, String stateDescription);

}

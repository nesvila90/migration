package co.gov.sic.migration.service.migration.manager.executor.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoEndpointManager;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.service.migration.loader.MigrationLoader;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MigrationExecutorImplTest {

    @Autowired
    private MigrationLoader migrationLoader;
    @Autowired
    private AlfrescoEndpointManager alfrescoEndpointManager;


    @Test
    public void iniciarEjecucion() throws SystemException {
        /*String uuid = GenerarConsecutivoUtil.generarCosecutivo();
        List<BatchFiltered<Metadatos, Workspace, Document>> migration = migrationLoader.createMigrationBatch(request.getCantidad(), uuid);
        String pathMigration = migrationLoader.prepareBatchMigrationOnStage(migration, uuid);
        alfrescoEndpointManager.bulkImportStart(Boolean.toString(request.isReplaceExisting()), pathMigration);*/
    }
}
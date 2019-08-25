package co.gov.sic.migration.service.migration.loader;

import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.GeneralStatus;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;

import java.util.List;

public interface MigrationLoader {

    List<BatchFiltered<Metadatos, Workspace, Document>> createMigrationBatch(final Integer batchSize, String uuid) throws SystemException;
    GeneralStatus prepareBatchMigrationOnStage(List<BatchFiltered<Metadatos, Workspace, Document>> migrationBatch, String uuid) throws SystemException;

}

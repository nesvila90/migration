package co.gov.sic.migration.service.migration.validator;

import co.gov.sic.migration.commons.domains.generic.BatchFiltered;
import co.gov.sic.migration.commons.domains.generic.Document;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.persistence.entities.Metadatos;

import java.util.List;

public interface BatchValidator {

    List<BatchFiltered<Metadatos, Workspace, Document>> validateBatch(List<Metadatos> metadataMigrationBatch, String uuid ) throws BusinessException, SystemException;

}

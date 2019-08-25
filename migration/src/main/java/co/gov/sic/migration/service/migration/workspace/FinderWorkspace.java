package co.gov.sic.migration.service.migration.workspace;

import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.exceptions.SystemException;

public interface FinderWorkspace {
    Workspace findDocumentWorspace(CmisDTO cmis) throws SystemException;
}

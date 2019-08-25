package co.gov.sic.migration.adapters.alfresco.cmis.dao;

import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.exceptions.SystemException;

public interface MigrateLocation {

    String findLocationDocument(CmisDTO locationParams) throws SystemException;

}

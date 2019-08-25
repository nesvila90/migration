package co.gov.sic.migration.service.migration.workspace.impl;

import co.gov.sic.migration.adapters.alfresco.cmis.dao.MigrateLocation;
import co.gov.sic.migration.adapters.alfresco.domain.request.CmisDTO;
import co.gov.sic.migration.commons.domains.generic.Workspace;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.service.migration.validator.impl.BatchValidatorImpl;
import co.gov.sic.migration.service.migration.workspace.FinderWorkspace;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinderWorkspaceImpl implements FinderWorkspace {

    private static final Logger LOGGER = LogManager.getLogger(FinderWorkspaceImpl.class);

    private MigrateLocation migrateLocation;

    @Autowired
    public FinderWorkspaceImpl(MigrateLocation migrateLocation) {
        this.migrateLocation = migrateLocation;
    }

    @Override
    public Workspace findDocumentWorspace(CmisDTO cmis) throws SystemException {
        LOGGER.info("EXECUTE: FinderWorkspaceImpl - findDocumentWorspace - Parameters - cmis: {}", cmis.toString());
        String workspace = migrateLocation.findLocationDocument(cmis);
        return Workspace.builder().path(workspace.isEmpty() ? "" : workspace).build();
    }


}

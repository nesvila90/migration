package co.gov.sic.migration.adapters.alfresco.cmis.dao.impl;

import co.gov.sic.migration.adapters.alfresco.cmis.dao.AlfrescoWorkspaceFinder;
import co.gov.sic.migration.adapters.alfresco.cmis.dao.CmisDaoAbstract;
import co.gov.sic.migration.adapters.alfresco.infrastructure.AlfrescoCmisConnection;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AlfrescoWorkspaceFinderImpl extends CmisDaoAbstract implements AlfrescoWorkspaceFinder {


    private final AlfrescoCmisConnection alfrescoCmisConnection;

    private Session session;

    @Autowired
    protected AlfrescoWorkspaceFinderImpl(AlfrescoCmisConnection alfrescoCmisConnection) {
        super(alfrescoCmisConnection);
        this.alfrescoCmisConnection = alfrescoCmisConnection;
    }

    @Override
    public ItemIterable<QueryResult> executeQuery(String query) {
        this.session = alfrescoCmisConnection.createSession();
        return session.query(query, false);
    }

}

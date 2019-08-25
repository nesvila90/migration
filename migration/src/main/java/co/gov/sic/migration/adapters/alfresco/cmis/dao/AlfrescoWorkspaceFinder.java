package co.gov.sic.migration.adapters.alfresco.cmis.dao;

import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.QueryResult;

public interface AlfrescoWorkspaceFinder extends CmisDao {

    ItemIterable<QueryResult> executeQuery(String query);

}

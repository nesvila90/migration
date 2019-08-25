package co.gov.sic.migration.adapters.alfresco.cmis.dao;

import co.gov.sic.migration.adapters.alfresco.infrastructure.AlfrescoCmisConnection;
import org.apache.chemistry.opencmis.client.api.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class CmisDaoAbstract implements CmisDao{

    private AlfrescoCmisConnection alfrescoCmisConnection;

    @Autowired
    protected CmisDaoAbstract(AlfrescoCmisConnection alfrescoCmisConnection) {
        this.alfrescoCmisConnection = alfrescoCmisConnection;
    }

    @Override
    public Session createSession() {
        return alfrescoCmisConnection.createSession();
    }


}

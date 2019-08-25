package co.gov.sic.migration.adapters.alfresco.infrastructure;

import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class AlfrescoCmisConnection {

    private final AlfrescoCmisConfig alfrescoCmisConfig;
    private final String bindingType;
    private final String idRepository;
    private Map<String, String> parameter;

    @Autowired
    public AlfrescoCmisConnection(AlfrescoCmisConfig alfrescoCmisConfig) {
        this.alfrescoCmisConfig = alfrescoCmisConfig;
        this.bindingType = BindingType.ATOMPUB.value();
        this.idRepository = "-default-";
        this.parameter = new HashMap<>();
    }

    private void bindingCredentialsConnection() {
        parameter.put(SessionParameter.USER, AlfrescoBundleUtil.getProperty("user"));
        parameter.put(SessionParameter.PASSWORD, AlfrescoBundleUtil.getProperty("password"));
        parameter.forEach((s, s2) -> System.out.println("Credentials - Propertie: " + s +" Value: " +s2));
    }

    private SessionFactory sessionFactory() {
        return SessionFactoryImpl.newInstance();
    }

    public Session createSession() {
        parameter.put(SessionParameter.ATOMPUB_URL, alfrescoCmisConfig.propertiesCmis().getUrl());
        parameter.put(SessionParameter.BINDING_TYPE, bindingType);
        parameter.put(SessionParameter.REPOSITORY_ID, idRepository);
        bindingCredentialsConnection();
        return sessionFactory().createSession(parameter);
    }
}

package co.gov.sic.migration.adapters.alfresco.api;

import co.gov.sic.migration.adapters.alfresco.domain.request.LoginRequest;
import org.springframework.http.ResponseEntity;

public interface AlfrescoSecurityManager {


    ResponseEntity<?> logIn (LoginRequest login);

}

package co.gov.sic.migration.adapters.alfresco.api.bulkimport.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoSecurityManager;
import co.gov.sic.migration.adapters.alfresco.domain.request.LoginRequest;
import co.gov.sic.migration.adapters.alfresco.domain.response.LoginResponse;
import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import co.gov.sic.migration.commons.utils.serializer.JSONUtil;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AlfrescoSecurityManagerImpl implements AlfrescoSecurityManager {

    @Override
    public ResponseEntity<LoginResponse> logIn(LoginRequest login) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<LoginRequest> request = new HttpEntity<>(login, headers);
        try {
            restTemplate.postForEntity(AlfrescoBundleUtil.getProperty("alfresco.login"), request, LoginResponse.class);
            return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(LoginResponse.builder().username(login.getUsername()).build());
        } catch (HttpClientErrorException clientErrorException) {
            return new ResponseEntity<>(LoginResponse.builder().username("Credenciales NO validas.").build(), HttpStatus.OK);
        }

    }
}


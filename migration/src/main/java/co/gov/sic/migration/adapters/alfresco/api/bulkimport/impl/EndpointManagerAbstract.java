package co.gov.sic.migration.adapters.alfresco.api.bulkimport.impl;

import co.gov.sic.migration.adapters.alfresco.api.EndpointManager;
import co.gov.sic.migration.adapters.alfresco.infrastructure.EndpointConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public abstract class EndpointManagerAbstract implements EndpointManager {

    private static final Logger LOGGER = LogManager.getLogger(EndpointManagerAbstract.class);

    private EndpointConfig endpointConfig;

    @Autowired
    public EndpointManagerAbstract(EndpointConfig endpointConfig) {
        this.endpointConfig = endpointConfig;
    }

    public ResponseEntity endpointConsumerClient(final String urlEndpoint,
                                                    final Class<?> typeResponse,
                                                    final HttpMethod method) {
        LOGGER.info("EXECUTE: EndpointManagerAbstract - endpointConsumerClient - Parameters - urlEndpoint: {}, typeResponse {}, method {}", urlEndpoint, typeResponse, method);

        RestTemplate clientConsumer = endpointConfig.authenticateConsumerEndpoint();
        HttpHeaders headersConsumer = endpointConfig.createAuthenticationHeaders();
        return clientConsumer.exchange(urlEndpoint, method, new HttpEntity<>(headersConsumer), typeResponse);
    }

}
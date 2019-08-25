package co.gov.sic.migration.adapters.alfresco.infrastructure;

import co.gov.sic.migration.commons.utils.bundles.AlfrescoBundleUtil;
import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestTemplate;

@Configuration
public class EndpointConfig {

    @Bean
    @ConfigurationProperties(prefix = "alfresco.client")
    public EndpointProperties properties() {
        return new EndpointProperties();
    }

    @Bean
    public HttpHeaders createAuthenticationHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @Bean
    public RestTemplate authenticateConsumerEndpoint() {
        RestTemplate endpointClientManager = new RestTemplate();
        endpointClientManager.getInterceptors().add(
                new BasicAuthenticationInterceptor(
                        AlfrescoBundleUtil.getProperty("user"),
                        AlfrescoBundleUtil.getProperty("password")));
        return endpointClientManager;
    }

    @Data
    @Builder
    @Getter
    @Setter
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    private static class EndpointProperties {
        private String user;
        private String password;
    }
}
package co.gov.sic.migration.adapters.alfresco.infrastructure;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AlfrescoCmisConfig {

    @Bean
    @ConfigurationProperties(prefix = "cmis")
    public CmisConfig propertiesCmis() {
        return new CmisConfig();
    }

    @Data
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @ToString
    public static class CmisConfig {
        private String url;
        private String user;
        private String password;
    }

}

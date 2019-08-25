package com.soaint.migracion.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Configuration
@EnableConfigurationProperties
@PropertySource("classpath:migracion-api.properties")
public class ConfiguracionAPI {

	@Bean
	@ConfigurationProperties(prefix = "endpoint")
	public Enpoint propertyEnpoint(){return  new Enpoint();}

	@Data
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Enpoint {
		private String habilitado;
		private String url;
	}

	@Bean
	@ConfigurationProperties(prefix = "api")
	public Api propertyApi(){return  new Api();}

	@Data
	@Getter
	@Setter
	@Builder
	@AllArgsConstructor
	@NoArgsConstructor
	public static class Api {
		
		private String registrar;


	}
	
}

package com.soaint.migracion;

import com.soaint.migracion.api.ejecucion.Auditoria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class InteraccionApiSicApplication {



    public static void main(String[] args) {
        SpringApplication.run(InteraccionApiSicApplication.class, args);
    }




}

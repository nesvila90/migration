package com.soaint.migracion.api.ejecucion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EndpointTester {

    @Autowired
    private Auditoria a;

    @GetMapping("v1")
    public void endpoint1(){
        a.auditarEjecucion("2a3bee8f-2f88-4729-8fab-62063e1d2bc3",39, "prueba proceso sebastian", 2);
    }

}

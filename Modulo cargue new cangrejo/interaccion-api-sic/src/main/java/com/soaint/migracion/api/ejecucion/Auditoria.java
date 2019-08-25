package com.soaint.migracion.api.ejecucion;

import com.soaint.migracion.dto.Ejecutar;
import com.soaint.migracion.dto.EstadoDTO;
import com.soaint.migracion.dto.RegistrarEjecucion;
import com.sun.net.httpserver.Headers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import com.soaint.migracion.config.ConfiguracionAPI;

public class Auditoria {


    private interface enpointsMigracion {
        static final String REGISTRAR_ENPOINT = "http://localhost:8181/migration/api/ejecucion/registrar";
        static final String MODIFICAR_ENPOINT = "http://localhost:8181/migration/api/ejecucion/modificar";
    }

    private RestTemplate executeClientEndpoint;

    public Auditoria() {
        this.executeClientEndpoint = new RestTemplate();
    }

    public ResponseEntity<?> consumirEndPoint(final Ejecutar datosRequest, final String endpointUrl,
                                              final HttpMethod method) {
        try {
            System.out.println("ID Documento "+datosRequest.getIdDocumento());

            HttpEntity<Ejecutar> ejecutarRequest = new HttpEntity<>(datosRequest, new HttpHeaders());
            ResponseEntity<?> respuestaEjecucion = executeClientEndpoint
                    .exchange(endpointUrl, method, ejecutarRequest, String.class);

            if (respuestaEjecucion.getStatusCodeValue() == HttpStatus.OK.value())
                return ResponseEntity.ok(respuestaEjecucion);
            else
                return ResponseEntity.badRequest()
                        .body("ERROR: " + respuestaEjecucion.getStatusCodeValue());
        } catch (Exception ex) {
            return ResponseEntity.badRequest()
                    .body("ERROR: " + ex.getMessage());
        }
    }

    public ResponseEntity<?> registrarEjecucion(Integer idRegistro, String mensajeEstadoProceso, Integer codEstado ) {

          /*Ejecutar e = Ejecutar.builder()
                .idDocumento(idRegistro)
                .mensajeEstadoProceso(mensajeEstadoProceso)
                .estadoProceso(codEstado)
                .build();*/


        RegistrarEjecucion Re = RegistrarEjecucion.builder()
                .idRegistro(idRegistro)
                .mensajeEstadoProceso(mensajeEstadoProceso)
                .estadoProceso(EstadoDTO.builder().codEstado(codEstado).build())
                .build();

        System.out.println("Registro de Auditaria de Ejecución - REGISTRAR: " + Re.toString());

        HttpEntity<Ejecutar> ejecutarRequest = new HttpEntity(Re, new HttpHeaders());
        ResponseEntity<?> respuestaEjecucion = executeClientEndpoint
                .exchange(enpointsMigracion.REGISTRAR_ENPOINT, HttpMethod.POST, ejecutarRequest, String.class);

        if (respuestaEjecucion.getStatusCodeValue() == HttpStatus.OK.value())
            return ResponseEntity.ok(respuestaEjecucion);
        else
            return ResponseEntity.badRequest()
                    .body("ERROR: " + respuestaEjecucion.getStatusCodeValue());

      // return consumirEndPoint(e, enpointsMigracion.REGISTRAR_ENPOINT, HttpMethod.POST);
    }

    public ResponseEntity<?> modificarEstadoEjecucionConRegistro(final String uuid, final String mensajeEstadoProceso , final String idRegistro, final int codEstadoProceso) {
        Ejecutar e = Ejecutar.builder()
                .uuid(uuid)
                .mensajeEstadoProceso(mensajeEstadoProceso)
                .estadoProceso(codEstadoProceso)
                .idDocumento(Integer.parseInt(idRegistro))
                .build();

        System.out.println("Registro de Auditaria de Ejecución - MODIFICAR A DETENIDO: " + e.toString());
        return consumirEndPoint(e, enpointsMigracion.MODIFICAR_ENPOINT, HttpMethod.PUT);
    }


    public ResponseEntity<?> modificarEstadoEjecucion(String uuid, String mensajeEstadoProceso, int estadoProceso) {
        Ejecutar e = Ejecutar.builder()
                .uuid(uuid)
                .mensajeEstadoProceso(mensajeEstadoProceso)
                .estadoProceso(estadoProceso)
                .build();
        System.out.println("Registro de Auditaria de Ejecución - MODIFICAR: " + e.toString());
        return consumirEndPoint(e, enpointsMigracion.MODIFICAR_ENPOINT, HttpMethod.PUT);
    }


    public static void main(String[] args) {


    }

}

package com.soaint.migracion.api.ejecucion;

import com.soaint.migracion.config.RecursosBundle;
import com.soaint.migracion.dto.Ejecutar;
import com.soaint.migracion.dto.Registrar;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


public class Auditoria {

	public static void auditarEjecucion(final Registrar datosRequest)
	{
		try {


	    	RestTemplate restTemplate = new RestTemplate();
	    	ResponseEntity<String> respuestaEjecucion = restTemplate.postForEntity(RecursosBundle.getProperty("endpoint.basepath")+ RecursosBundle.getProperty("api.registrar"),
	    			datosRequest,
	    			String.class);

		}
		catch (Exception ex)
		{
            System.out.println("ERROR " + ex.getMessage());
		}

	}
	
	public static void auditarEjecucion(final String uuid, final int idRegistro, final String mensajeEstadoProceso, final int estadoProceso)
	{		
		try {
			System.out.println("Auditoria ");

	    	RestTemplate restTemplate = new RestTemplate();


            Ejecutar ejecutar = new Ejecutar(uuid,idRegistro,mensajeEstadoProceso,estadoProceso);

	    	
	    	ResponseEntity<String> respuestaEjecucion = restTemplate.postForEntity(RecursosBundle.getProperty("endpoint.basepath")+ RecursosBundle.getProperty("api.modificar"),
	    			ejecutar,
	    			String.class);
			System.out.println("Auditoria  configuracion endpoint HABILITADO: contenido objeto " + respuestaEjecucion.toString() );

		}
		catch (Exception ex)
		{
			System.out.println("Error al registrar auditoria "+ex.getCause());
            System.out.println("ERROR " + ex.getMessage());
		}

	}
	
	public static void auditarEjecucion(final String uuid, final  String mensajeEstadoProceso, final int estadoProceso)
	{		
		try {

			RestTemplate restTemplate = new RestTemplate();
	    	Registrar datosSolicitud = new Registrar();
	    	
	    	datosSolicitud.setEstadoProceso(estadoProceso);
	    	datosSolicitud.setMensajeEstadoProceso(mensajeEstadoProceso);
	    	datosSolicitud.setUuid(uuid);
	    	ResponseEntity<String> respuestaEjecucion = restTemplate.postForEntity(RecursosBundle.getProperty("endpoint.basepath")+ RecursosBundle.getProperty("api.modificar"),
					datosSolicitud,
	    			String.class);

		}
		catch (Exception ex)
		{
            System.out.println("ERROR " + ex.getMessage());
		}


	}

}

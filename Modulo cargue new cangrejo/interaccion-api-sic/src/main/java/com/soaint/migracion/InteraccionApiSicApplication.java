package com.soaint.migracion;

import com.soaint.migracion.api.ejecucion.Auditoria;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


@SpringBootApplication
@EnableConfigurationProperties
public class InteraccionApiSicApplication  extends SpringBootServletInitializer {

	private static Class<InteraccionApiSicApplication> interaccionApi = InteraccionApiSicApplication.class;

		public static void main(String[] args) {
			Auditoria au = new Auditoria();

		//System.out.println(auditarEjecucion("1253ff", "completo", 1));

		//Ejecutar prueba = new Ejecutar("505050-90890809-8980980","mensajeEstadoProceso","11",1);



		//System.out.println("Auditar Registro Ejecicion "+au.registrarEjecucion(22,"PruebaCangrejo",1));
		//System.out.println("Modificar: "+ au.modificarEstadoEjecucionConRegistro("c2a89bd7-bc08-4d9d-8ee7-8479a302966f","modificaBien","12",3));


			//System.out.println("Auditar 3 parametros "+auditarEjecucion("9090909-90890809-8980980","mensajeEstadoProceso",1));
		//System.out.println("Auditar Ejecucion "+auditarEjecucion(prueba));
		System.out.println("Modificar: "+ au.modificarEstadoEjecucion("c2a89bd7-bc08-4d9d-8ee7-8479a302966f","ModificadoCangrejo",2));

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(interaccionApi);
	}
}


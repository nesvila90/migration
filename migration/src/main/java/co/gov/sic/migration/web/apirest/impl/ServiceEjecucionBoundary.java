package co.gov.sic.migration.web.apirest.impl;

import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import co.gov.sic.migration.persistence.entities.Estados;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.web.bind.annotation.*;
import co.gov.sic.migration.commons.exceptions.SystemException;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value = "servicio", produces = MediaType.APPLICATION_JSON_VALUE)
public class ServiceEjecucionBoundary {
    @Autowired
    TaskScheduler taskScheduler;
    ScheduledFuture<?> scheduledFuture;
    @Autowired
    private EjecucionBoundary control;
    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    @PostMapping("iniciar")
    public void consultarEstadoEjecucion(@RequestBody final IniciarDTOResquest iniciarRequest) throws ExecutionException, InterruptedException, BusinessException, IOException, SystemException {
        System.out.println("Tiempo de intervalo de ejecucion: " + TimeUnit.MINUTES.toMillis(iniciarRequest.getIntervaloEjecucion()));
        Thread hiloEjecucion;
     /*   if (iniciarRequest.getIntervaloEjecucion() > 0) {
            try {
                if (scheduledFuture != null) {
                    if (scheduledFuture.isDone()) {
                        scheduledFuture = taskScheduler.scheduleAtFixedRate(repository.consultarEstadoEjecucion(iniciarRequest), TimeUnit.MINUTES.toMillis(iniciarRequest.getIntervaloEjecucion()));
                        //scheduledFuture = taskScheduler.scheduleAtFixedRate(repository.consultarEstadoEjecucion(iniciarRequest), iniciarRequest.getIntervaloEjecucion());
                        System.out.println("obtener es scheduleFuture por true: " + scheduledFuture.get());
                        System.out.println("Estado del Schedule en true --- " + scheduledFuture.isDone());
                    } else {
                        System.out.println("No lo voy a ejecutar porque ya esta iniciado");
                        Thread.sleep(5000);
                    }
                } else {

                    scheduledFuture = taskScheduler.scheduleAtFixedRate(repository.consultarEstadoEjecucion(iniciarRequest), iniciarRequest.getIntervaloEjecucion());
                    System.out.println("-----------------------");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

    @RequestMapping("detener")
    ResponseEntity<Void> stop() {
        scheduledFuture.cancel(false);
        System.out.println("Estado del Schedule DETENER:  " + scheduledFuture.isDone());
        System.out.println("----------------------------------");
        Estados e = new Estados();
        e.setCodEstado(7);
        e.setDescripcionEstado("Detenido por el usuario");
        e.setEtapa("Sin Ejecución");
        AplicacionEstado ap = new AplicacionEstado();
        ap.setEstadoMigracion(e);
        ap.setEstadoGeneralMigracion("Sin Ejecución");
        ap.setDescripcionEstadoMigracion("DETENIDO POR EL USUARIO");
        aplicacionEstadoBoundary.registerAplicacionEstado(ap);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}

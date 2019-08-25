package co.gov.sic.migration.web.apirest.impl;

import co.gov.sic.migration.commons.domains.request.EjecucionDTORequest;
import co.gov.sic.migration.commons.domains.request.MetadatosDTORequest;
import co.gov.sic.migration.commons.domains.request.ModificarEjecucionDTORequest;
import co.gov.sic.migration.commons.domains.response.EjecucionDTOResponse;
import co.gov.sic.migration.commons.domains.response.EjecucionYMetadatosResponse;
import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.commons.utils.converter.MetadatosConverter;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Metadatos;
import co.gov.sic.migration.service.state.application.repository.impl.EstadoControlImpl;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping(value = "ejecucion", produces = MediaType.APPLICATION_JSON_VALUE)
public class EjecucionRestApi {

    private static final Logger LOGGER = LogManager.getLogger(EjecucionRestApi.class);
    private final EstadoControlImpl estadoControl;
    private final EjecucionBoundary ejecucionBoundary;

    @Autowired
    public EjecucionRestApi(EstadoControlImpl estadoControl, EjecucionBoundary ejecucionBoundary) {
        this.estadoControl = estadoControl;
        this.ejecucionBoundary = ejecucionBoundary;
    }


    @GetMapping("mostrarEjecucionesv1")
    public List<Ejecucion> mostrarEjecuciones() throws SystemException {
        return ejecucionBoundary.mostrarEjecuciones();
    }



    @PostMapping(value = "registrar")
    public ResponseEntity<?> registrarMetadatosImportacion(@RequestBody EjecucionDTORequest ejecucionRequest) throws SystemException {
        LOGGER.info("EjecucionRestApi - registrarImportacion: {}", ejecucionRequest);
        EjecucionDTOResponse ejecucionResponse = ejecucionBoundary.registrarEjecucionDocumento(ejecucionRequest);
        return ejecucionResponse != null ? ResponseEntity
                .ok("User Registered - STATUS CODE: " + HttpStatus.CREATED) :
                ResponseEntity.unprocessableEntity()
                        .body("Ejecucion NOT Registered - STATUSCODE: " + HttpStatus.CONFLICT + "\n" + new EjecucionDTOResponse());

    }

    @PostMapping(value = "modificar")
    public ResponseEntity<?> modificarEstadoEjecucion(@RequestBody final ModificarEjecucionDTORequest ejecucion) throws BusinessException, SystemException {
        LOGGER.info("EjecucionRestApi - modificarEstadoEjecucion: {}", ejecucion.toString());
        EjecucionDTORequest e = EjecucionDTORequest.builder().uuid(ejecucion.getUuid())
                .idRegistro(ejecucion.getIdRegistro())
                .mensajeEstadoProceso(ejecucion.getMensajeEstadoProceso())
                .estadoProceso(EstadoDTOResponse.builder()
                        .codEstado(ejecucion.getEstadoProceso())
                        .build())
                .build();

        //EjecucionDTOResponse ejecucionResponse = new EjecucionDTOResponse();
        //Actualizamos el estado en la tabla metadatos
        System.out.println("Ejecucion Rest Api --- Modificar Estado Ejecucion--- id Registro:  " + ejecucion.getIdRegistro());
        Metadatos metadatos = ejecucionBoundary.buscarMetadato(ejecucion.getIdRegistro());
        MetadatosDTORequest m = MetadatosConverter.entityToDTORequest(metadatos);
        EjecucionDTOResponse ejecucionResponse = ejecucionBoundary.modificarEstadoEjecucion(e,metadatos);
        //MetadatosDTORequest m = new MetadatosDTORequest();
        m.setEstadoProceso(ejecucion.getEstadoProceso());
        m.setUuid(ejecucion.getUuid());
        EstadoMetadatoDTOResponse estadoMetadatoDTOResponse;
        try {
            estadoMetadatoDTOResponse = ejecucionBoundary.modificarEstadoMetadato(m, ejecucion.getEstadoProceso());
            return ResponseEntity.ok(EjecucionYMetadatosResponse.builder()
                    .ejecucionDTOResponse(ejecucionResponse)
                    .estadoMetadatoDTOResponse(estadoMetadatoDTOResponse)
                    .build());
        } catch (SystemException e1) {
            return ResponseEntity.unprocessableEntity()
                    .body("Estado de Ejecucion Fallido - STATUSCODE: " + HttpStatus.BAD_REQUEST + "\n" +
                            ejecucionResponse.toString() + "Estado Metadatos " + new EstadoMetadatoDTOResponse());
        }

    }
}

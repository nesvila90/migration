package co.gov.sic.migration.web.apirest.impl;

import co.gov.sic.migration.adapters.alfresco.api.AlfrescoSecurityManager;
import co.gov.sic.migration.adapters.alfresco.domain.request.LoginRequest;
import co.gov.sic.migration.adapters.alfresco.domain.response.LoginResponse;
import co.gov.sic.migration.commons.domains.request.IniciarDTOResquest;
import co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse;
import co.gov.sic.migration.commons.enums.CodigoError;
import co.gov.sic.migration.commons.enums.MensajeError;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.commons.exceptions.builder.ExceptionBuilder;
import co.gov.sic.migration.commons.exceptions.SystemException;
import co.gov.sic.migration.service.state.execution.business.EjecucionBoundary;
import co.gov.sic.migration.service.state.metadata.business.MetadataBoundary;
import org.hibernate.boot.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping(value = "control", produces = MediaType.APPLICATION_JSON_VALUE)
public class ControlUiApi {

    @Autowired
    private EjecucionBoundary ejecucionBoundary;

    @Autowired
    private MetadataBoundary metadataBoundary;

    @Autowired
    private AlfrescoSecurityManager alfrescoSecurityManager;

    @PostMapping("iniciar")
    public void iniciarImportacion(@RequestBody final IniciarDTOResquest format) throws IndexOutOfBoundsException,
            BusinessException {
        try{
         //   ejecucionBoundary.startExecution(format);
        }catch (IndexOutOfBoundsException io){
            throw  ExceptionBuilder.newBuilder()
                    .withCode(CodigoError.INDEX_OUT_OF_BOUNDS_EXCEPTION).withRootException(io)
                    .withMessage(MensajeError.NOT_FOUND_METADATA_PENDING.getName())
                    .buildBusinessException();
        }catch (Throwable th){

                throw  ExceptionBuilder.newBuilder()
                        .withCode(CodigoError.PERSISTENCE_EXCEPTION)
                        .withRootException(th).withMessage(MensajeError.NO_DATA.getName())
                        .buildBusinessException();
        }
    }


    @PostMapping("pausar")
    public ResponseEntity<?> pausarImportacion() throws InterruptedException {
        //return  ejecucionBoundary.stopExecution();
        return null;
    }

    @PostMapping("reanudar")
    public ResponseEntity<?> reanudarImportacion() throws InterruptedException {
      //  return ejecucionBoundary.reanudarEjecucion();
        return null;
    }

    @PostMapping("parar")
    public ResponseEntity<?> pararImportacion() throws InterruptedException {
        return null;
    }

    @GetMapping("estado")
    public ResponseEntity<?> estadoImportacion() throws InterruptedException {
        return ejecucionBoundary.estadoEjecucion();
    }
    @GetMapping("estadoMetadato")
    public List<EstadoMetadatoDTOResponse> estadosMetadatos()
            throws BusinessException, SystemException {
        //return metadataBoundary.findAllMetadataExecutionState();
        return metadataBoundary.findAllMetadataExecutionState();
    }

    @PostMapping("login")
    public ResponseEntity<?> logIn(@RequestBody LoginRequest login) {
        return alfrescoSecurityManager.logIn(login);
    }



}

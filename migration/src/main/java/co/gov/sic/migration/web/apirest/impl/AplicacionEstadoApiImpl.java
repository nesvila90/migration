package co.gov.sic.migration.web.apirest.impl;

import co.gov.sic.migration.commons.domains.response.MigrationStatusDTOResponse;
import co.gov.sic.migration.commons.exceptions.BusinessException;
import co.gov.sic.migration.persistence.entities.AplicacionEstado;
import co.gov.sic.migration.service.state.application.business.AplicacionEstadoBoundary;
import co.gov.sic.migration.web.apirest.AplicacionEstadoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RequestMapping(value = "aplicacion/v1/", produces = MediaType.APPLICATION_JSON_VALUE)
public class AplicacionEstadoApiImpl implements AplicacionEstadoApi {

    private AplicacionEstadoBoundary aplicacionEstadoBoundary;

    @Autowired
    public AplicacionEstadoApiImpl(AplicacionEstadoBoundary aplicacionEstadoBoundary) {
        this.aplicacionEstadoBoundary = aplicacionEstadoBoundary;
    }

    @Override
    @GetMapping("findLastAplicacionEstado")
    public MigrationStatusDTOResponse findLastAplicacionEstado() throws BusinessException {
        return aplicacionEstadoBoundary.findLastAplicacionEstado();
    }

    @Override
    @PostMapping("registerAplicacionEstado")
    public MigrationStatusDTOResponse registerAplicacionEstado(@RequestBody final AplicacionEstado aplicacionEstado) {
        return aplicacionEstadoBoundary.registerAplicacionEstado(aplicacionEstado);
    }

    @Override
    @PutMapping("updateAplicacionEstado")
    public MigrationStatusDTOResponse updateAplicacionEstado(@RequestBody final AplicacionEstado aplicacionEstado) throws BusinessException {
        return aplicacionEstadoBoundary.updateAplicacionEstado(aplicacionEstado);
    }
}

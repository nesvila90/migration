package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.commons.domains.request.EjecucionDTORequest;
import co.gov.sic.migration.commons.domains.response.EjecucionDTOResponse;
import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import co.gov.sic.migration.commons.domains.response.MetadatosDTOResponse;
import co.gov.sic.migration.persistence.entities.Ejecucion;
import co.gov.sic.migration.persistence.entities.Estados;

import java.util.ArrayList;
import java.util.List;

public class EjecucionConverter{

    public static EjecucionDTOResponse converterEntityToDto(Ejecucion e, List<MetadatosDTOResponse> metadatos){
        return EjecucionDTOResponse.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getCodEstadoProceso() != null ?
                        EstadosConverter.converterEntityToDto(e.getCodEstadoProceso()) : new EstadoDTOResponse())
                .metadatosList(metadatos != null ? metadatos : new ArrayList<>())
                .build();
    }

    public static EjecucionDTOResponse converterDtoRequestToDTOResponse(EjecucionDTORequest e){
        return EjecucionDTOResponse.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getEstadoProceso() != null ?
                        e.getEstadoProceso() : new EstadoDTOResponse())
                .uuid(e.getUuid())
                .build();
    }

    public static EjecucionDTOResponse entityToDTOResponse(Ejecucion e){
        return EjecucionDTOResponse.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getCodEstadoProceso() != null ?
                        EstadosConverter.converterEntityToDto(e.getCodEstadoProceso()) : new EstadoDTOResponse())
                .uuid(e.getUuid())
                .build();
    }
    public static EjecucionDTORequest converterEntityToDTORequest(Ejecucion e){
        return EjecucionDTORequest.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .estadoProceso(e.getCodEstadoProceso() != null ?
                        EstadosConverter.converterEntityToDto(e.getCodEstadoProceso()) : new EstadoDTOResponse())
                .uuid(e.getUuid())
                .build();
    }

    public static Ejecucion requestToEntity(EjecucionDTORequest e){
        return Ejecucion.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .codEstadoProceso(e.getEstadoProceso() != null ?
                        EstadosConverter.converterDtoToEntity(e.getEstadoProceso()) : new Estados())
                .uuid(e.getUuid())
                .build();
    }

    public static Ejecucion converterDtoResponseToEntity(EjecucionDTOResponse e){
        return Ejecucion.builder()
                .idRegistro(e.getIdRegistro())
                .mensajeEstadoProceso(e.getMensajeEstadoProceso())
                .codEstadoProceso(EstadosConverter.converterDtoToEntity(e.getEstadoProceso()))
                .build();
    }

}

package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import co.gov.sic.migration.persistence.entities.Estados;

public class EstadosConverter {

    public static EstadoDTOResponse converterEntityToDto(final Estados e){
        return EstadoDTOResponse.builder()
                .codEstado(e.getCodEstado())
                .descripcionEstado(e.getDescripcionEstado())
                .etapa(e.getEtapa())
                .build();
    }

    public static Estados converterDtoToEntity(final EstadoDTOResponse e){
        return Estados.builder()
                .codEstado(e.getCodEstado())
                .descripcionEstado(e.getDescripcionEstado())
                .etapa(e.getEtapa())
                .build();
    }

}

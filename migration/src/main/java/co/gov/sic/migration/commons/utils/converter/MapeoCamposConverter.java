package co.gov.sic.migration.commons.utils.converter;

import co.gov.sic.migration.commons.domains.response.MapeoCamposDTOResponse;
import co.gov.sic.migration.persistence.entities.MapeoCampos;

public class MapeoCamposConverter {

    public static MapeoCamposDTOResponse converterEntityToDto(MapeoCampos m){
        return MapeoCamposDTOResponse.builder()
                .columna(m.getColumna())
                .campoAlfresco(m.getCampoAlfresco())
                .tipoAlfresco(m.getTipoAlfresco())
                .build();
    }

    public static MapeoCampos converterDtoToEntity(MapeoCamposDTOResponse m){
        return MapeoCampos.builder()
                .columna(m.getColumna())
                .campoAlfresco(m.getCampoAlfresco())
                .tipoAlfresco(m.getTipoAlfresco())
                .build();
    }

}

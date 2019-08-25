package co.gov.sic.migration.commons.domains.response;

import lombok.*;

import java.io.Serializable;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
public class EstadoDTOResponse implements Serializable {

    private Integer codEstado;
    private String descripcionEstado;
    private String etapa;

    public EstadoDTOResponse(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public EstadoDTOResponse() {
    }
}

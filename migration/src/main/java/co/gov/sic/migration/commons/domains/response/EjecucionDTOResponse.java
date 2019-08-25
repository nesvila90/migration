package co.gov.sic.migration.commons.domains.response;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EjecucionDTOResponse implements Serializable {

    private String uuid;
    private String mensajeEstadoProceso;
    private int idRegistro;
    private List<MetadatosDTOResponse> metadatosList;
    private EstadoDTOResponse estadoProceso;

}

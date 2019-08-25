package co.gov.sic.migration.commons.domains.request;


import lombok.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class RegistrarEjecucionDTORequest implements Serializable {

    private String mensajeEstadoProceso;
    private int idRegistro;
    private int estadoProceso;

    public RegistrarEjecucionDTORequest() {
    }



}

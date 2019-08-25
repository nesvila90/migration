package co.gov.sic.migration.commons.domains.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class ModificarEjecucionDTORequest implements Serializable {

    private String uuid;
    private String mensajeEstadoProceso;
    private int idRegistro;
    private int estadoProceso;


    public ModificarEjecucionDTORequest() {
    }




}

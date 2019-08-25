package co.gov.sic.migration.commons.domains.request;

import co.gov.sic.migration.commons.domains.response.EstadoDTOResponse;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@ToString
public class EjecucionDTORequest implements Serializable {

    private String uuid;
    private String mensajeEstadoProceso;
    private int idRegistro;
    private EstadoDTOResponse estadoProceso;

    public EjecucionDTORequest(String uuid, String mensajeEstadoProceso, int idRegistro, EstadoDTOResponse estadoProceso) {
        this.uuid = uuid;
        this.mensajeEstadoProceso = mensajeEstadoProceso;
        this.idRegistro = idRegistro;
        this.estadoProceso = estadoProceso;
    }

    public EjecucionDTORequest() {
    }

}

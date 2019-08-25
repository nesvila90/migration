package co.gov.sic.migration.commons.domains.response;


import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ModificarEjecucionDTOResponse {

    String uuid;
    String mensajeEstadoProceso;
    String idRegistro;
    int ccodEstado;
    boolean modificado;

}

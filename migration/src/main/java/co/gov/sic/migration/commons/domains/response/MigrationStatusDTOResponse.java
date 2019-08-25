package co.gov.sic.migration.commons.domains.response;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MigrationStatusDTOResponse implements Serializable {

    private Integer idEjecucion;
    private String uuid;
    private String carpetaMigracion;
    private String descripcionEstadoMigracion;
    private String estadoGeneralMigracion;
    private EstadoDTOResponse estadoMigracion;
}

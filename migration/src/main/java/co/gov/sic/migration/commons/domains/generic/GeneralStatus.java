package co.gov.sic.migration.commons.domains.generic;

import co.gov.sic.migration.commons.enums.EstadoParametro;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeneralStatus {

    private String uuid;
    private EstadoParametro generalState;
    private String pathMigration;

}

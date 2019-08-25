package co.gov.sic.migration.commons.domains.request;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class RutaDTORequest implements Serializable {
    private String codUnidadAdmiPadre;
    private String codDependencia;
    private String codSerie;
    private String codSubSerie;
}

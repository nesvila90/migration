package co.gov.sic.migration.adapters.alfresco.domain.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CmisDTO {
    private String codigoUnidadAdministrativaPadre;
    private String codigoDependecia;
    private String serie;
    private String subserie;
}

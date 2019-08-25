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
public class IniciarDTOResquest implements Serializable {

    private boolean replaceExisting;
    private int cantidad;
    private Long intervaloEjecucion;
}

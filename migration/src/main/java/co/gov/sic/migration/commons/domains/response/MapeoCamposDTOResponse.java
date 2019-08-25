package co.gov.sic.migration.commons.domains.response;


import lombok.*;

import java.io.Serializable;


@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class MapeoCamposDTOResponse implements Serializable {

    private String columna;
    private String campoAlfresco;
    private String tipoAlfresco;


}

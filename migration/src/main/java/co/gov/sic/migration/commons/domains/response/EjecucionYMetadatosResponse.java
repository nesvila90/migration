package co.gov.sic.migration.commons.domains.response;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EjecucionYMetadatosResponse implements Serializable {
    private EjecucionDTOResponse ejecucionDTOResponse;
    private EstadoMetadatoDTOResponse estadoMetadatoDTOResponse;
}

package co.gov.sic.migration.commons.domains.request;

import co.gov.sic.migration.persistence.entities.Ejecucion;
import lombok.*;

import java.io.Serializable;
@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EstadoMetadatoDTORequest implements Serializable {
    private Ejecucion uuid;

    public EstadoMetadatoDTORequest(String uuid){
        this.uuid.setUuid(uuid);
    }
}

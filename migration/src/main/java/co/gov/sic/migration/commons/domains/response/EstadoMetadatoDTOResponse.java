package co.gov.sic.migration.commons.domains.response;

import lombok.*;

import java.io.File;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@ToString

@NoArgsConstructor
public class EstadoMetadatoDTOResponse implements Serializable {
    private String uuid;
    private String descripcionEstado;
    private String estadoProceso;
    private String nombreArchivo;
    private Integer idRegistro;
    private Date fechaRegistroAuditoria;

    public EstadoMetadatoDTOResponse(String uuid, String descripcionEstado, String estadoProceso, String nombreArchivo, Integer idRegistro, Date fechaRegistroAuditoria) {
        this.uuid = uuid;
        this.descripcionEstado = descripcionEstado;
        this.estadoProceso = estadoProceso;
        this.nombreArchivo = new File(nombreArchivo).getName();
        this.idRegistro = idRegistro;
        this.fechaRegistroAuditoria = fechaRegistroAuditoria;
    }

    public EstadoMetadatoDTOResponse(String uuid, String descripcionEstado, String estadoProceso, String nombreArchivo, Integer idRegistro) {
        this.uuid = uuid;
        this.descripcionEstado = descripcionEstado;
        this.estadoProceso = estadoProceso;
        this.nombreArchivo = nombreArchivo;
        this.idRegistro = idRegistro;
    }
}

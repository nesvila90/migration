package co.gov.sic.migration.commons.domains.request;

import co.gov.sic.migration.persistence.entities.Ejecucion;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MetadatosDTORequest implements Serializable {

    private Integer idregistro;
    private String numeroRadicado;
    private Integer numeroRadicacion;
    private Integer anioRadicacion;
    private Integer consecutivoRadicacion;
    private String tipoContenido;
    private String autor;
    private Date fechaRadicacion;
    private String tipoComunicacion;
    private String tipoDocumento;
    private String tipoDocumental;
    private Integer tramite;
    private Integer evento;
    private Integer actuacion;
    private String tipoActo;
    private Integer numeroActo;
    private Date fechaActo;
    private String grupoSeguridad;
    private String entidad;
    private String sede;
    private String dependenciaJerarquica;
    private String dependenciaProductora;
    private String serie;
    private String subserie;
    private String idExpediente;
    private String nombreExpediente;
    private Date fechaCierre;
    private Date fechaFaseArchivo;
    private String faseArchivo;
    private String idCategoriaRetencion;
    private Integer secuenciaEvento;
    private String controlRadicacion;
    private String rutaArchivo;
    private Integer estadoProceso;
    private String uuid;

}

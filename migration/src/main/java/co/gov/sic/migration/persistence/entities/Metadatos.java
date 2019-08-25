/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.sic.migration.persistence.entities;

import lombok.*;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nestor
 */
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
@Entity
@Table(name = "`Metadatos`")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Metadatos.findAll", query = "SELECT m FROM Metadatos m")
        , @NamedQuery(name = "Metadatos.findByUuid", query = "SELECT m FROM Metadatos m where m.uuid =:UUID")
        , @NamedQuery(name = "Metadatos.findByIdregistro", query = "SELECT m FROM Metadatos m WHERE m.idregistro = :idregistro")
        , @NamedQuery(name = "Metadatos.findByIdregistroAndEstadoProceso", query = "SELECT m FROM Metadatos m INNER JOIN Ejecucion e ON m.idregistro = e.idRegistro WHERE m.idregistro = :idregistro AND e.codEstadoProceso = :estado ORDER BY m.idregistro ASC")
        , @NamedQuery(name = "Metadatos.findByNumeroRadicado", query = "SELECT m FROM Metadatos m WHERE m.numeroRadicado = :numeroRadicado")
        , @NamedQuery(name = "Metadatos.findByNumeroRadicacion", query = "SELECT m FROM Metadatos m WHERE m.numeroRadicacion = :numeroRadicacion")
        , @NamedQuery(name = "Metadatos.findByAnioRadicacion", query = "SELECT m FROM Metadatos m WHERE m.anioRadicacion = :anioRadicacion")
        , @NamedQuery(name = "Metadatos.findByConsecutivoRadicacion", query = "SELECT m FROM Metadatos m WHERE m.consecutivoRadicacion = :consecutivoRadicacion")
        , @NamedQuery(name = "Metadatos.findByTipoContenido", query = "SELECT m FROM Metadatos m WHERE m.tipoContenido = :tipoContenido")
        , @NamedQuery(name = "Metadatos.findByAutor", query = "SELECT m FROM Metadatos m WHERE m.autor = :autor")
        , @NamedQuery(name = "Metadatos.findByFechaRadicacion", query = "SELECT m FROM Metadatos m WHERE m.fechaRadicacion = :fechaRadicacion")
        , @NamedQuery(name = "Metadatos.findByTipoComunicacion", query = "SELECT m FROM Metadatos m WHERE m.tipoComunicacion = :tipoComunicacion")
        , @NamedQuery(name = "Metadatos.findByTipoDocumento", query = "SELECT m FROM Metadatos m WHERE m.tipoDocumento = :tipoDocumento")
        , @NamedQuery(name = "Metadatos.findByTipoDocumental", query = "SELECT m FROM Metadatos m WHERE m.tipoDocumental = :tipoDocumental")
        , @NamedQuery(name = "Metadatos.findByTramite", query = "SELECT m FROM Metadatos m WHERE m.tramite = :tramite")
        , @NamedQuery(name = "Metadatos.findByEvento", query = "SELECT m FROM Metadatos m WHERE m.evento = :evento")
        , @NamedQuery(name = "Metadatos.findByActuacion", query = "SELECT m FROM Metadatos m WHERE m.actuacion = :actuacion")
        , @NamedQuery(name = "Metadatos.findByTipoActo", query = "SELECT m FROM Metadatos m WHERE m.tipoActo = :tipoActo")
        , @NamedQuery(name = "Metadatos.findByNumeroActo", query = "SELECT m FROM Metadatos m WHERE m.numeroActo = :numeroActo")
        , @NamedQuery(name = "Metadatos.findByFechaActo", query = "SELECT m FROM Metadatos m WHERE m.fechaActo = :fechaActo")
        , @NamedQuery(name = "Metadatos.findByGrupoSeguridad", query = "SELECT m FROM Metadatos m WHERE m.grupoSeguridad = :grupoSeguridad")
        , @NamedQuery(name = "Metadatos.findByEntidad", query = "SELECT m FROM Metadatos m WHERE m.entidad = :entidad")
        , @NamedQuery(name = "Metadatos.findBySede", query = "SELECT m FROM Metadatos m WHERE m.sede = :sede")
        , @NamedQuery(name = "Metadatos.findByDependenciaJerarquica", query = "SELECT m FROM Metadatos m WHERE m.dependenciaJerarquica = :dependenciaJerarquica")
        , @NamedQuery(name = "Metadatos.findByDependenciaProductora", query = "SELECT m FROM Metadatos m WHERE m.dependenciaProductora = :dependenciaProductora")
        , @NamedQuery(name = "Metadatos.findBySerie", query = "SELECT m FROM Metadatos m WHERE m.serie = :serie")
        , @NamedQuery(name = "Metadatos.findBySubserie", query = "SELECT m FROM Metadatos m WHERE m.subserie = :subserie")
        , @NamedQuery(name = "Metadatos.findByIdExpediente", query = "SELECT m FROM Metadatos m WHERE m.idExpediente = :idExpediente")
        , @NamedQuery(name = "Metadatos.findByNombreExpediente", query = "SELECT m FROM Metadatos m WHERE m.nombreExpediente = :nombreExpediente")
        , @NamedQuery(name = "Metadatos.findByFechaCierre", query = "SELECT m FROM Metadatos m WHERE m.fechaCierre = :fechaCierre")
        , @NamedQuery(name = "Metadatos.findByFechaFaseArchivo", query = "SELECT m FROM Metadatos m WHERE m.fechaFaseArchivo = :fechaFaseArchivo")
        , @NamedQuery(name = "Metadatos.findByFaseArchivo", query = "SELECT m FROM Metadatos m WHERE m.faseArchivo = :faseArchivo")
        , @NamedQuery(name = "Metadatos.findByIdCategoriaRetencion", query = "SELECT m FROM Metadatos m WHERE m.idCategoriaRetencion = :idCategoriaRetencion")
        , @NamedQuery(name = "Metadatos.findBySecuenciaEvento", query = "SELECT m FROM Metadatos m WHERE m.secuenciaEvento = :secuenciaEvento")
        , @NamedQuery(name = "Metadatos.findByControlRadicacion", query = "SELECT m FROM Metadatos m WHERE m.controlRadicacion = :controlRadicacion")
        , @NamedQuery(name = "Metadatos.findByRutaArchivo", query = "SELECT m FROM Metadatos m WHERE m.rutaArchivo = :rutaArchivo")
        , @NamedQuery(name = "Metadatos.findByEstadoProceso", query = "SELECT m FROM Metadatos m WHERE m.estadoProceso = :estadoProceso ORDER BY m.idregistro ASC")
        , @NamedQuery(name = "Metadatos.findByEstadoProcesoyUUID", query = "SELECT m FROM Metadatos m WHERE m.estadoProceso = :estadoProceso and m.uuid = :UUID")
        , @NamedQuery(name = "Metadatos.consultarMetadatosPorEstado", query = "select new co.gov.sic.migration.commons.domains.response.MetadatosDTOResponse(m.idregistro, m.numeroRadicado, m.numeroRadicacion, m.anioRadicacion," +
        "m.consecutivoRadicacion, m.tipoContenido, m.autor, m.fechaRadicacion, m.tipoComunicacion, m.tipoDocumento," +
        "m.tipoDocumental, m.tramite, m.evento, m.actuacion, m.tipoActo, m.numeroActo, m.fechaActo, m.grupoSeguridad," +
        "m.entidad, m.sede, m.dependenciaJerarquica, m.dependenciaProductora, m.serie, m.subserie, m.idExpediente," +
        "m.nombreExpediente, m.fechaCierre,m.fechaFaseArchivo, m.faseArchivo, m.idCategoriaRetencion," +
        "m.secuenciaEvento,m.controlRadicacion, m.rutaArchivo, m.estadoProceso, m.uuid ,m.autorexpediente) " +
        "from Metadatos m where m.estadoProceso = :ESTADO")
        , @NamedQuery(name = "Metadatos.consultaMetadatosPorUuid", query = "select new co.gov.sic.migration.commons.domains.response.MetadatosDTOResponse(m.uuid) from Metadatos m where m.uuid =:UUID ")
        , @NamedQuery(name = "Metadatos.consultaEstadoMetadatosPorUuid",
        query = "select new co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse(" +
                "m.uuid, e.mensajeEstadoProceso, s.descripcionEstado,  m.rutaArchivo, e.idRegistro) " +
                "from Metadatos m inner join Ejecucion e  " +
                "on m.idregistro = e.idRegistro " +
                "inner join Estados s " +
                "on e.codEstadoProceso = s.codEstado " +
                "where m.uuid =:UUID ")
        , @NamedQuery(name = "Metadatos.consultaEstadoMetadatosGeneral",
        query = "select new co.gov.sic.migration.commons.domains.response.EstadoMetadatoDTOResponse(" +
                "m.uuid, e.mensajeEstadoProceso, s.descripcionEstado,  m.rutaArchivo, e.idRegistro, e.fechaRegistroAuditoria) " +
                "from Metadatos m inner join Ejecucion e  " +
                "on m.idregistro = e.idRegistro " +
                "inner join Estados s " +
                "on e.codEstadoProceso = s.codEstado ORDER BY e.fechaRegistroAuditoria DESC")
})


public class Metadatos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "`idregistro`")
    private Integer idregistro;
    @Column(name = "`numeroRadicado`")
    private String numeroRadicado;
    @Column(name = "`numeroRadicacion`")
    private Integer numeroRadicacion;
    @Column(name = "`anioRadicacion`")
    private Integer anioRadicacion;
    @Column(name = "`consecutivoRadicacion`")
    private Integer consecutivoRadicacion;
    @Column(name = "`tipoContenido`")
    private String tipoContenido;
    @Column(name = "`autor`")
    private String autor;
    @Column(name = "`fechaRadicacion`")
    @Temporal(TemporalType.DATE)
    private Date fechaRadicacion;
    @Column(name = "`tipoComunicacion`")
    private String tipoComunicacion;
    @Column(name = "`tipoDocumento`")
    private String tipoDocumento;
    @Column(name = "`tipoDocumental`")
    private String tipoDocumental;
    @Column(name = "`tramite`")
    private Integer tramite;
    @Column(name = "`evento`")
    private Integer evento;
    @Column(name = "`actuacion`")
    private Integer actuacion;
    @Column(name = "`tipoActo`")
    private String tipoActo;
    @Column(name = "`numeroActo`")
    private Integer numeroActo;
    @Column(name = "`fechaActo`")
    @Temporal(TemporalType.DATE)
    private Date fechaActo;
    @Column(name = "`grupoSeguridad`")
    private String grupoSeguridad;
    @Column(name = "`entidad`")
    private String entidad;
    @Column(name = "`sede`")
    private String sede;
    @Column(name = "`dependenciaJerarquica`")
    private String dependenciaJerarquica;
    @Column(name = "`dependenciaProductora`")
    private String dependenciaProductora;
    @Column(name = "`serie`")
    private String serie;
    @Column(name = "`subserie`")
    private String subserie;
    @Column(name = "`idExpediente`")
    private String idExpediente;
    @Column(name = "`nombreExpediente`")
    private String nombreExpediente;
    @Column(name = "`fechaCierre`")
    @Temporal(TemporalType.DATE)
    private Date fechaCierre;
    @Column(name = "`fechaFaseArchivo`")
    @Temporal(TemporalType.DATE)
    private Date fechaFaseArchivo;
    @Column(name = "`faseArchivo`")
    private String faseArchivo;
    @Column(name = "`idCategoriaRetencion`")
    private String idCategoriaRetencion;
    @Column(name = "`secuenciaEvento`")
    private Integer secuenciaEvento;
    @Column(name = "`controlRadicacion`")
    private String controlRadicacion;
    @Basic(optional = false)
    @Column(name = "`rutaArchivo`")
    private String rutaArchivo;
    @Column(name = "`estadoProceso`")
    private Integer estadoProceso;
    @Column(name = "`uuid`")
    private String uuid;
    @Column(name = "`autorexpediente`")
    private String autorexpediente;
    @Column(name = "`fechainicial`")
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Column(name = "`fechafinal`")
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Column(name = "`soporte`")
    private String soporte;



    public Metadatos(String numeroRadicado, Integer numeroRadicacion, Integer anioRadicacion, Integer consecutivoRadicacion, String tipoContenido, String autor, Date fechaRadicacion, String tipoComunicacion, String tipoDocumento, String tipoDocumental, Integer tramite, Integer evento, Integer actuacion, String tipoActo, Integer numeroActo, Date fechaActo, String grupoSeguridad, String entidad, String sede, String dependenciaJerarquica, String dependenciaProductora, String serie, String subserie, String idExpediente, String nombreExpediente, Date fechaCierre, Date fechaFaseArchivo, String faseArchivo, String idCategoriaRetencion, Integer secuenciaEvento, String controlRadicacion, String rutaArchivo, Integer estadoProceso, String uuid, String autorexpediente, Date fechaInicial, Date fechaFinal, String soporte) {
        this.numeroRadicado = numeroRadicado;
        this.numeroRadicacion = numeroRadicacion;
        this.anioRadicacion = anioRadicacion;
        this.consecutivoRadicacion = consecutivoRadicacion;
        this.tipoContenido = tipoContenido;
        this.autor = autor;
        this.fechaRadicacion = fechaRadicacion;
        this.tipoComunicacion = tipoComunicacion;
        this.tipoDocumento = tipoDocumento;
        this.tipoDocumental = tipoDocumental;
        this.tramite = tramite;
        this.evento = evento;
        this.actuacion = actuacion;
        this.tipoActo = tipoActo;
        this.numeroActo = numeroActo;
        this.fechaActo = fechaActo;
        this.grupoSeguridad = grupoSeguridad;
        this.entidad = entidad;
        this.sede = sede;
        this.dependenciaJerarquica = dependenciaJerarquica;
        this.dependenciaProductora = dependenciaProductora;
        this.serie = serie;
        this.subserie = subserie;
        this.idExpediente = idExpediente;
        this.nombreExpediente = nombreExpediente;
        this.fechaCierre = fechaCierre;
        this.fechaFaseArchivo = fechaFaseArchivo;
        this.faseArchivo = faseArchivo;
        this.idCategoriaRetencion = idCategoriaRetencion;
        this.secuenciaEvento = secuenciaEvento;
        this.controlRadicacion = controlRadicacion;
        this.rutaArchivo = rutaArchivo;
        this.estadoProceso = estadoProceso;
        this.uuid = uuid;
        this.autorexpediente = autorexpediente;
        this.fechaInicial = fechaInicial;
        this.fechaFinal = fechaFinal;
        this.soporte = soporte;
    }

    public Metadatos(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public Metadatos(Integer idregistro, String rutaArchivo) {
        this.idregistro = idregistro;
        this.rutaArchivo = rutaArchivo;
    }

    public Integer getIdregistro() {
        return idregistro;
    }

    public void setIdregistro(Integer idregistro) {
        this.idregistro = idregistro;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public Integer getNumeroRadicacion() {
        return numeroRadicacion;
    }

    public void setNumeroRadicacion(Integer numeroRadicacion) {
        this.numeroRadicacion = numeroRadicacion;
    }

    public Integer getAnioRadicacion() {
        return anioRadicacion;
    }

    public void setAnioRadicacion(Integer anioRadicacion) {
        this.anioRadicacion = anioRadicacion;
    }

    public Integer getConsecutivoRadicacion() {
        return consecutivoRadicacion;
    }

    public void setConsecutivoRadicacion(Integer consecutivoRadicacion) {
        this.consecutivoRadicacion = consecutivoRadicacion;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public String getTipoComunicacion() {
        return tipoComunicacion;
    }

    public void setTipoComunicacion(String tipoComunicacion) {
        this.tipoComunicacion = tipoComunicacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getTipoDocumental() {
        return tipoDocumental;
    }

    public void setTipoDocumental(String tipoDocumental) {
        this.tipoDocumental = tipoDocumental;
    }

    public Integer getTramite() {
        return tramite;
    }

    public void setTramite(Integer tramite) {
        this.tramite = tramite;
    }

    public Integer getEvento() {
        return evento;
    }

    public void setEvento(Integer evento) {
        this.evento = evento;
    }

    public Integer getActuacion() {
        return actuacion;
    }

    public void setActuacion(Integer actuacion) {
        this.actuacion = actuacion;
    }

    public String getTipoActo() {
        return tipoActo;
    }

    public void setTipoActo(String tipoActo) {
        this.tipoActo = tipoActo;
    }

    public Integer getNumeroActo() {
        return numeroActo;
    }

    public void setNumeroActo(Integer numeroActo) {
        this.numeroActo = numeroActo;
    }

    public Date getFechaActo() {
        return fechaActo;
    }

    public void setFechaActo(Date fechaActo) {
        this.fechaActo = fechaActo;
    }

    public String getGrupoSeguridad() {
        return grupoSeguridad;
    }

    public void setGrupoSeguridad(String grupoSeguridad) {
        this.grupoSeguridad = grupoSeguridad;
    }

    public String getEntidad() {
        return entidad;
    }

    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getDependenciaJerarquica() {
        return dependenciaJerarquica;
    }

    public void setDependenciaJerarquica(String dependenciaJerarquica) {
        this.dependenciaJerarquica = dependenciaJerarquica;
    }

    public String getDependenciaProductora() {
        return dependenciaProductora;
    }

    public void setDependenciaProductora(String dependenciaProductora) {
        this.dependenciaProductora = dependenciaProductora;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getSubserie() {
        return subserie;
    }

    public void setSubserie(String subserie) {
        this.subserie = subserie;
    }

    public String getIdExpediente() {
        return idExpediente;
    }

    public void setIdExpediente(String idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getNombreExpediente() {
        return nombreExpediente;
    }

    public void setNombreExpediente(String nombreExpediente) {
        this.nombreExpediente = nombreExpediente;
    }

    public Date getFechaCierre() {
        return fechaCierre;
    }

    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Date getFechaFaseArchivo() {
        return fechaFaseArchivo;
    }

    public void setFechaFaseArchivo(Date fechaFaseArchivo) {
        this.fechaFaseArchivo = fechaFaseArchivo;
    }

    public String getFaseArchivo() {
        return faseArchivo;
    }

    public void setFaseArchivo(String faseArchivo) {
        this.faseArchivo = faseArchivo;
    }

    public String getIdCategoriaRetencion() {
        return idCategoriaRetencion;
    }

    public void setIdCategoriaRetencion(String idCategoriaRetencion) {
        this.idCategoriaRetencion = idCategoriaRetencion;
    }

    public Integer getSecuenciaEvento() {
        return secuenciaEvento;
    }

    public void setSecuenciaEvento(Integer secuenciaEvento) {
        this.secuenciaEvento = secuenciaEvento;
    }

    public String getControlRadicacion() {
        return controlRadicacion;
    }

    public void setControlRadicacion(String controlRadicacion) {
        this.controlRadicacion = controlRadicacion;
    }

    public String getRutaArchivo() {
        return rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
    }

    public Integer getEstadoProceso() {
        return estadoProceso;
    }

    public void setEstadoProceso(Integer estadoProceso) {
        this.estadoProceso = estadoProceso;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getAutorexpediente() {
        return autorexpediente;
    }

    public void setAutorexpediente(String autorexpediente) {
        this.autorexpediente = autorexpediente;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getSoporte() {
        return soporte;
    }

    public void setSoporte(String soporte) {
        this.soporte = soporte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idregistro != null ? idregistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Metadatos)) {
            return false;
        }
        Metadatos other = (Metadatos) object;
        if ((this.idregistro == null && other.idregistro != null) || (this.idregistro != null && !this.idregistro.equals(other.idregistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.sic.migration.repository.entities.Metadatos[ idregistro=" + idregistro + " ]";
    }

}

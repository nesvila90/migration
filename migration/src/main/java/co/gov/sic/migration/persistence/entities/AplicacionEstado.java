/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.sic.migration.persistence.entities;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Nestor
 */
@Data
@Builder
@Entity
@ToString
@Table(name = "aplicacion_estado")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AplicacionEstado.findAll", query = "SELECT a FROM AplicacionEstado a")

        , @NamedQuery(name = "AplicacionEstado.findByLastIdEjecucion", query = "SELECT a FROM AplicacionEstado a order by a.idEjecucion desc")
        , @NamedQuery(name = "AplicacionEstado.findByIdEjecucion", query = "SELECT a FROM AplicacionEstado a WHERE a.idEjecucion = :ID_EJECUCION order by a.idEjecucion desc")
        , @NamedQuery(name = "AplicacionEstado.findByUuid", query = "SELECT a FROM AplicacionEstado a WHERE a.uuid = :uuid")

        , @NamedQuery(name = "AplicacionEstado.findByCarpetaMigracion", query = "SELECT a FROM AplicacionEstado a WHERE a.carpetaMigracion = :carpetaMigracion")
        , @NamedQuery(name = "AplicacionEstado.findByDescripcionEstadoMigracion", query = "SELECT a FROM AplicacionEstado a WHERE a.descripcionEstadoMigracion = :descripcionEstadoMigracion")
        , @NamedQuery(name = "AplicacionEstado.findByEstadoGeneralMigracion", query = "SELECT a FROM AplicacionEstado a WHERE a.estadoGeneralMigracion = :estadoGeneralMigracion")})
public class AplicacionEstado implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id_ejecucion")
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEjecucion;
    @Basic(optional = false)
    @Column(name = "uuid")
    private String uuid;
    @Basic(optional = false)
    @Column(name = "carpeta_migracion")
    private String carpetaMigracion;
    @Column(name = "descripcion_estado_migracion")
    private String descripcionEstadoMigracion;
    @Basic(optional = false)
    @Column(name = "estado_general_migracion")
    private String estadoGeneralMigracion;
    @JoinColumn(name = "estado_migracion", referencedColumnName = "`codEstado`")
    @ManyToOne(optional = false)
    private Estados estadoMigracion;

    public AplicacionEstado() {
    }

    public AplicacionEstado(Integer idEjecucion, String uuid, String carpetaMigracion, String descripcionEstadoMigracion, String estadoGeneralMigracion, Estados estadoMigracion) {
        this.idEjecucion = idEjecucion;
        this.uuid = uuid;
        this.carpetaMigracion = carpetaMigracion;
        this.descripcionEstadoMigracion = descripcionEstadoMigracion;
        this.estadoGeneralMigracion = estadoGeneralMigracion;
        this.estadoMigracion = estadoMigracion;
    }

    public AplicacionEstado(Integer idEjecucion) {
        this.idEjecucion = idEjecucion;
    }

    public AplicacionEstado(Integer idEjecucion, String uuid, String carpetaMigracion, String estadoGeneralMigracion) {
        this.idEjecucion = idEjecucion;
        this.uuid = uuid;
        this.carpetaMigracion = carpetaMigracion;
        this.estadoGeneralMigracion = estadoGeneralMigracion;
    }

    public Integer getIdEjecucion() {
        return idEjecucion;
    }

    public void setIdEjecucion(Integer idEjecucion) {
        this.idEjecucion = idEjecucion;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCarpetaMigracion() {
        return carpetaMigracion;
    }

    public void setCarpetaMigracion(String carpetaMigracion) {
        this.carpetaMigracion = carpetaMigracion;
    }

    public String getDescripcionEstadoMigracion() {
        return descripcionEstadoMigracion;
    }

    public void setDescripcionEstadoMigracion(String descripcionEstadoMigracion) {
        this.descripcionEstadoMigracion = descripcionEstadoMigracion;
    }

    public String getEstadoGeneralMigracion() {
        return estadoGeneralMigracion;
    }

    public void setEstadoGeneralMigracion(String estadoGeneralMigracion) {
        this.estadoGeneralMigracion = estadoGeneralMigracion;
    }

    public Estados getEstadoMigracion() {
        return estadoMigracion;
    }

    public void setEstadoMigracion(Estados estadoMigracion) {
        this.estadoMigracion = estadoMigracion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEjecucion != null ? idEjecucion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AplicacionEstado)) {
            return false;
        }
        AplicacionEstado other = (AplicacionEstado) object;
        if ((this.idEjecucion == null && other.idEjecucion != null) || (this.idEjecucion != null && !this.idEjecucion.equals(other.idEjecucion))) {
            return false;
        }
        return true;
    }

}

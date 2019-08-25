/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.sic.migration.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Nestor
 */
@Data
@Builder
@AllArgsConstructor
@Entity
@Table(name = "`Ejecucion`")
@XmlRootElement
@NamedQueries({
          @NamedQuery(name = "Ejecucion.findAll", query = "SELECT e FROM Ejecucion e")
        , @NamedQuery(name = "Ejecucion.findByUuid", query = "SELECT e FROM Ejecucion e WHERE e.uuid = :uuid")
        , @NamedQuery(name = "Ejecucion.findByMensajeEstadoProceso", query = "SELECT e FROM Ejecucion e WHERE e.mensajeEstadoProceso = :mensajeEstadoProceso")
        , @NamedQuery(name = "Ejecucion.findByIdRegistro", query = "SELECT e FROM Ejecucion e WHERE e.idRegistro = :idRegistro")
        , @NamedQuery(name = "Ejecucion.findByIdRegistroAndCodEstado", query = "SELECT e FROM Ejecucion e WHERE e.idRegistro = :idRegistro and e.codEstadoProceso = :codEstado")
        , @NamedQuery(name = "Ejecucion.findByFecha", query = "select m from Ejecucion m where m.fechaRegistroAuditoria is not null order by m.fechaRegistroAuditoria ASC")})


public class Ejecucion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "`uuid`")
    private String uuid;
    @Column(name = "`mensajeEstadoProceso`")
    private String mensajeEstadoProceso;
    @Id
    @Basic(optional = false)
    @Column(name = "`idRegistro`")
    private Integer idRegistro;
    @Column(name = "`fecha_registro_auditoria`")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistroAuditoria;
    @JoinColumn(name = "`codEstadoProceso`", referencedColumnName = "`codEstado`")
    @ManyToOne
    private Estados codEstadoProceso;


    public Ejecucion() {
    }

    public Ejecucion(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Ejecucion(Integer idRegistro, String uuid) {
        this.idRegistro = idRegistro;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getMensajeEstadoProceso() {
        return mensajeEstadoProceso;
    }

    public void setMensajeEstadoProceso(String mensajeEstadoProceso) {
        this.mensajeEstadoProceso = mensajeEstadoProceso;
    }

    public Integer getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public Date getFechaRegistroAuditoria() {
        return fechaRegistroAuditoria;
    }

    public void setFechaRegistroAuditoria(Date fechaRegistroAuditoria) {
        this.fechaRegistroAuditoria = fechaRegistroAuditoria;
    }

    public Estados getCodEstadoProceso() {
        return codEstadoProceso;
    }

    public void setCodEstadoProceso(Estados codEstadoProceso) {
        this.codEstadoProceso = codEstadoProceso;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idRegistro != null ? idRegistro.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ejecucion)) {
            return false;
        }
        Ejecucion other = (Ejecucion) object;
        if ((this.idRegistro == null && other.idRegistro != null) || (this.idRegistro != null && !this.idRegistro.equals(other.idRegistro))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.sic.migration.repository.entities.Ejecucion[ idRegistro=" + idRegistro + " ]";
    }

}

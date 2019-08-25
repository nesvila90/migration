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

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Nestor
 */


@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "`Estados`")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Estados.findAll", query = "SELECT e FROM Estados e")
        , @NamedQuery(name = "Estados.findByCodEstado", query = "SELECT e FROM Estados e WHERE e.codEstado = :codEstado")
        , @NamedQuery(name = "Estados.findByDescripcionEstado", query = "SELECT e FROM Estados e WHERE e.descripcionEstado = :descripcionEstado")
        , @NamedQuery(name = "Estados.findByEtapa", query = "SELECT e FROM Estados e WHERE e.etapa = :etapa")})
public class Estados implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "`codEstado`")
    private Integer codEstado;
    @Column(name = "`descripcionEstado`")
    private String descripcionEstado;
    @Column(name = "`etapa`")
    private String etapa;
    /*
    @OneToMany(mappedBy = "codEstadoProceso")
    private List<Ejecucion> ejecucionList;
    */
    public Estados() {
    }

    public Estados(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public Integer getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(Integer codEstado) {
        this.codEstado = codEstado;
    }

    public String getDescripcionEstado() {
        return descripcionEstado;
    }

    public void setDescripcionEstado(String descripcionEstado) {
        this.descripcionEstado = descripcionEstado;
    }

    public String getEtapa() {
        return etapa;
    }

    public void setEtapa(String etapa) {
        this.etapa = etapa;
    }
    /*
    @XmlTransient
    public List<Ejecucion> getEjecucionList() {
        return ejecucionList;
    }

    public void setEjecucionList(List<Ejecucion> ejecucionList) {
        this.ejecucionList = ejecucionList;
    }
    */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codEstado != null ? codEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estados)) {
            return false;
        }
        Estados other = (Estados) object;
        if ((this.codEstado == null && other.codEstado != null) || (this.codEstado != null && !this.codEstado.equals(other.codEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.sic.migration.repository.entities.Estados[ codEstado=" + codEstado + " ]";
    }
    
}

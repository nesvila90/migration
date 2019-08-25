/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.gov.sic.migration.persistence.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Nestor
 */

@Data
@Builder
@AllArgsConstructor

@Entity
@Table(name = "`MapeoCampos`", catalog = "SOADOC_Migracion", schema = "`AppMigracion`")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "MapeoCampos.findAll", query = "SELECT m FROM MapeoCampos m")
        , @NamedQuery(name = "MapeoCampos.findByColumna", query = "SELECT m FROM MapeoCampos m WHERE m.columna = :columna")
        , @NamedQuery(name = "MapeoCampos.findByCampoAlfresco", query = "SELECT m FROM MapeoCampos m WHERE m.campoAlfresco = :campoAlfresco")
        , @NamedQuery(name = "MapeoCampos.findByTipoAlfresco", query = "SELECT m FROM MapeoCampos m WHERE m.tipoAlfresco = :tipoAlfresco")})
public class MapeoCampos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "`columna`")
    private String columna;
    @Column(name = "`campoAlfresco`")
    private String campoAlfresco;
    @Column(name = "`tipoAlfresco`")
    private String tipoAlfresco;

    public MapeoCampos() {
    }

    public MapeoCampos(String columna) {
        this.columna = columna;
    }

    public String getColumna() {
        return columna;
    }

    public void setColumna(String columna) {
        this.columna = columna;
    }

    public String getCampoAlfresco() {
        return campoAlfresco;
    }

    public void setCampoAlfresco(String campoAlfresco) {
        this.campoAlfresco = campoAlfresco;
    }

    public String getTipoAlfresco() {
        return tipoAlfresco;
    }

    public void setTipoAlfresco(String tipoAlfresco) {
        this.tipoAlfresco = tipoAlfresco;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (columna != null ? columna.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MapeoCampos)) {
            return false;
        }
        MapeoCampos other = (MapeoCampos) object;
        if ((this.columna == null && other.columna != null) || (this.columna != null && !this.columna.equals(other.columna))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.sic.migration.repository.entities.MapeoCampos[ columna=" + columna + " ]";
    }
    
}

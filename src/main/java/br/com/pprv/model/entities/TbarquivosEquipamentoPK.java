/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author ioliveira
 */
@Embeddable
public class TbarquivosEquipamentoPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "idequipamento")
    private int idequipamento;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tmdataupload")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataupload;

    public TbarquivosEquipamentoPK() {
    }

    public TbarquivosEquipamentoPK(int idequipamento, Date tmdataupload) {
        this.idequipamento = idequipamento;
        this.tmdataupload = tmdataupload;
    }

    public int getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(int idequipamento) {
        this.idequipamento = idequipamento;
    }

    public Date getTmdataupload() {
        return tmdataupload;
    }

    public void setTmdataupload(Date tmdataupload) {
        this.tmdataupload = tmdataupload;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idequipamento;
        hash += (tmdataupload != null ? tmdataupload.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbarquivosEquipamentoPK)) {
            return false;
        }
        TbarquivosEquipamentoPK other = (TbarquivosEquipamentoPK) object;
        if (this.idequipamento != other.idequipamento) {
            return false;
        }
        if ((this.tmdataupload == null && other.tmdataupload != null) || (this.tmdataupload != null && !this.tmdataupload.equals(other.tmdataupload))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.TbarquivosEquipamentoPK[ idequipamento=" + idequipamento + ", tmdataupload=" + tmdataupload + " ]";
    }
    
}

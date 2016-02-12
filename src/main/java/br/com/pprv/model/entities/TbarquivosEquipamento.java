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
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbarquivos_equipamento")
public class TbarquivosEquipamento implements Serializable {

    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbequipamento tbequipamento;
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TbarquivosEquipamentoPK tbarquivosEquipamentoPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nmarquivo")
    private String nmarquivo;
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbusuario idusuario;

    public TbarquivosEquipamento() {
    }

    public TbarquivosEquipamento(TbarquivosEquipamentoPK tbarquivosEquipamentoPK) {
        this.tbarquivosEquipamentoPK = tbarquivosEquipamentoPK;
    }

    public TbarquivosEquipamento(TbarquivosEquipamentoPK tbarquivosEquipamentoPK, String nmarquivo) {
        this.tbarquivosEquipamentoPK = tbarquivosEquipamentoPK;
        this.nmarquivo = nmarquivo;
    }

    public TbarquivosEquipamento(int idequipamento, Date tmdataupload) {
        this.tbarquivosEquipamentoPK = new TbarquivosEquipamentoPK(idequipamento, tmdataupload);
    }

    public TbarquivosEquipamentoPK getTbarquivosEquipamentoPK() {
        return tbarquivosEquipamentoPK;
    }

    public void setTbarquivosEquipamentoPK(TbarquivosEquipamentoPK tbarquivosEquipamentoPK) {
        this.tbarquivosEquipamentoPK = tbarquivosEquipamentoPK;
    }

    public String getNmarquivo() {
        return nmarquivo;
    }

    public void setNmarquivo(String nmarquivo) {
        this.nmarquivo = nmarquivo;
    }

    public Tbusuario getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(Tbusuario idusuario) {
        this.idusuario = idusuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tbarquivosEquipamentoPK != null ? tbarquivosEquipamentoPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbarquivosEquipamento)) {
            return false;
        }
        TbarquivosEquipamento other = (TbarquivosEquipamento) object;
        if ((this.tbarquivosEquipamentoPK == null && other.tbarquivosEquipamentoPK != null) || (this.tbarquivosEquipamentoPK != null && !this.tbarquivosEquipamentoPK.equals(other.tbarquivosEquipamentoPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.TbarquivosEquipamento[ tbarquivosEquipamentoPK=" + tbarquivosEquipamentoPK + " ]";
    }

    public Tbequipamento getTbequipamento() {
        return tbequipamento;
    }

    public void setTbequipamento(Tbequipamento tbequipamento) {
        this.tbequipamento = tbequipamento;
    }

}

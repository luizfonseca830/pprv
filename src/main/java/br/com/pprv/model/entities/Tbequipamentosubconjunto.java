/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbequipamentosubconjunto")
public class Tbequipamentosubconjunto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipamentosubconjunto")
    private Integer idequipamentoSubconjunto;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tbequipamento idequipamento;

    public Tbequipamentosubconjunto() {
    }

    public Tbequipamentosubconjunto(Integer idequipamentoSubconjunto) {
        this.idequipamentoSubconjunto = idequipamentoSubconjunto;
    }

    public Integer getIdequipamentosubconjunto() {
        return idequipamentoSubconjunto;
    }

    public void setIdequipamentosubconjunto(Integer idequipamentosubconjunto) {
        this.idequipamentoSubconjunto = idequipamentosubconjunto;
    }

    public Tbsubconjunto getIdsubconjunto() {
        return idsubconjunto;
    }

    public void setIdsubconjunto(Tbsubconjunto idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
    }

    public Tbequipamento getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(Tbequipamento idequipamento) {
        this.idequipamento = idequipamento;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipamentoSubconjunto != null ? idequipamentoSubconjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbequipamentosubconjunto)) {
            return false;
        }
        Tbequipamentosubconjunto other = (Tbequipamentosubconjunto) object;
        if ((this.idequipamentoSubconjunto == null && other.idequipamentoSubconjunto != null) || (this.idequipamentoSubconjunto != null && !this.idequipamentoSubconjunto.equals(other.idequipamentoSubconjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.entities.Tbequipamentosubconjunto[ idequipamentosubconjunto=" + idequipamentoSubconjunto + " ]";
    }

}

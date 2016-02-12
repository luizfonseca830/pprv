/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities;

import br.com.pprv.web.faces.converter.Identificador;
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
@Table(name = "tbequipamento_subconjunto")
public class TbequipamentoSubconjunto implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipamento_subconjunto")
    private Integer idequipamentoSubconjunto;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbequipamento idequipamento;

    public TbequipamentoSubconjunto() {
    }

    public TbequipamentoSubconjunto(Integer idequipamentoSubconjunto) {
        this.idequipamentoSubconjunto = idequipamentoSubconjunto;
    }

    public Integer getIdequipamentoSubconjunto() {
        return idequipamentoSubconjunto;
    }

    public void setIdequipamentoSubconjunto(Integer idequipamentoSubconjunto) {
        this.idequipamentoSubconjunto = idequipamentoSubconjunto;
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
        if (!(object instanceof TbequipamentoSubconjunto)) {
            return false;
        }
        TbequipamentoSubconjunto other = (TbequipamentoSubconjunto) object;
        if ((this.idequipamentoSubconjunto == null && other.idequipamentoSubconjunto != null) || (this.idequipamentoSubconjunto != null && !this.idequipamentoSubconjunto.equals(other.idequipamentoSubconjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.TbequipamentoSubconjunto[ idequipamentoSubconjunto=" + idequipamentoSubconjunto + " ]";
    }

    @Override
    public Integer getPK() {
        return idequipamentoSubconjunto;
    }

}

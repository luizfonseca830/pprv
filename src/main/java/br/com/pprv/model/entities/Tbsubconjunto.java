/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities;

import br.com.pprv.web.faces.converter.Identificador;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author JorgeFonseca
 */
@Entity
@Table(name = "tbsubconjunto")
public class Tbsubconjunto implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idsubconjunto")
    private Integer idsubconjunto;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nmsubconjunto")
    private String nmsubconjunto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "condicao")
    private int condicao;
    @OneToMany(mappedBy = "idsubconjunto", fetch = FetchType.EAGER)
    private List<Tbtecnica> tbtecnicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubconjunto", fetch = FetchType.EAGER)
    private List<Tblaudo> tblaudoList;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbequipamento idequipamento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idsubconjunto", fetch = FetchType.EAGER)
    private List<Tbequipamento> tbequipamentoList;

    public Tbsubconjunto() {
    }

    public Tbsubconjunto(Integer idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
    }

    public Tbsubconjunto(Integer idsubconjunto, String nmsubconjunto, int condicao) {
        this.idsubconjunto = idsubconjunto;
        this.nmsubconjunto = nmsubconjunto;
        this.condicao = condicao;
    }

    public Integer getIdsubconjunto() {
        return idsubconjunto;
    }

    public void setIdsubconjunto(Integer idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
    }

    public String getNmsubconjunto() {
        return nmsubconjunto;
    }

    public void setNmsubconjunto(String nmsubconjunto) {
        this.nmsubconjunto = nmsubconjunto;
    }

    public int getCondicao() {
        return condicao;
    }

    public void setCondicao(int condicao) {
        this.condicao = condicao;
    }

    @XmlTransient
    public List<Tbtecnica> getTbtecnicaList() {
        return tbtecnicaList;
    }

    public void setTbtecnicaList(List<Tbtecnica> tbtecnicaList) {
        this.tbtecnicaList = tbtecnicaList;
    }

    @XmlTransient
    public List<Tblaudo> getTblaudoList() {
        return tblaudoList;
    }

    public void setTblaudoList(List<Tblaudo> tblaudoList) {
        this.tblaudoList = tblaudoList;
    }

    public Tbequipamento getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(Tbequipamento idequipamento) {
        this.idequipamento = idequipamento;
    }

    @XmlTransient
    public List<Tbequipamento> getTbequipamentoList() {
        return tbequipamentoList;
    }

    public void setTbequipamentoList(List<Tbequipamento> tbequipamentoList) {
        this.tbequipamentoList = tbequipamentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsubconjunto != null ? idsubconjunto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbsubconjunto)) {
            return false;
        }
        Tbsubconjunto other = (Tbsubconjunto) object;
        if ((this.idsubconjunto == null && other.idsubconjunto != null) || (this.idsubconjunto != null && !this.idsubconjunto.equals(other.idsubconjunto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbsubconjunto[ idsubconjunto=" + idsubconjunto + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdsubconjunto();
    }

}

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
@Table(name = "tbequipamento")
public class Tbequipamento implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idequipamento")
    private Integer idequipamento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nmequipamenta")
    private String nmequipamenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criticidade")
    private int criticidade;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "descequipamento")
    private String descequipamento;
    @OneToMany(mappedBy = "idequipamento", fetch = FetchType.EAGER)
    private List<Tbtecnica> tbtecnicaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idequipamento", fetch = FetchType.EAGER)
    private List<Tblaudo> tblaudoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idequipamento", fetch = FetchType.EAGER)
    private List<Tbsubconjunto> tbsubconjuntoList;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idinspecao", referencedColumnName = "idinspecao")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbinspecao idinspecao;
    @JoinColumn(name = "idtecnica", referencedColumnName = "idtecnica")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbtecnica idtecnica;

    public Tbequipamento() {
    }

    public Tbequipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    public Tbequipamento(Integer idequipamento, String nmequipamenta, int criticidade, String descequipamento) {
        this.idequipamento = idequipamento;
        this.nmequipamenta = nmequipamenta;
        this.criticidade = criticidade;
        this.descequipamento = descequipamento;
    }

    public Integer getIdequipamento() {
        return idequipamento;
    }

    public void setIdequipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    public String getNmequipamenta() {
        return nmequipamenta;
    }

    public void setNmequipamenta(String nmequipamenta) {
        this.nmequipamenta = nmequipamenta;
    }

    public int getCriticidade() {
        return criticidade;
    }

    public void setCriticidade(int criticidade) {
        this.criticidade = criticidade;
    }

    public String getDescequipamento() {
        return descequipamento;
    }

    public void setDescequipamento(String descequipamento) {
        this.descequipamento = descequipamento;
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

    @XmlTransient
    public List<Tbsubconjunto> getTbsubconjuntoList() {
        return tbsubconjuntoList;
    }

    public void setTbsubconjuntoList(List<Tbsubconjunto> tbsubconjuntoList) {
        this.tbsubconjuntoList = tbsubconjuntoList;
    }

    public Tbsubconjunto getIdsubconjunto() {
        return idsubconjunto;
    }

    public void setIdsubconjunto(Tbsubconjunto idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
    }

    public Tbinspecao getIdinspecao() {
        return idinspecao;
    }

    public void setIdinspecao(Tbinspecao idinspecao) {
        this.idinspecao = idinspecao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idequipamento != null ? idequipamento.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbequipamento)) {
            return false;
        }
        Tbequipamento other = (Tbequipamento) object;
        if ((this.idequipamento == null && other.idequipamento != null) || (this.idequipamento != null && !this.idequipamento.equals(other.idequipamento))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbequipamento[ idequipamento=" + idequipamento + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdequipamento();
    }

}

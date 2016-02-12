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
 * @author ioliveira
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
    @Size(max = 2147483647)
    @Column(name = "nmequipamenta")
    private String nmequipamenta;
    @Basic(optional = false)
    @NotNull
    @Column(name = "criticidade")
    private int criticidade;
    @Size(max = 80)
    @Column(name = "descequipamento")
    private String descequipamento;
    @Column(name = "condicao")
    private Integer condicao;
    @OneToMany(mappedBy = "tbequipamento", fetch = FetchType.EAGER)
    private List<TbarquivosEquipamento> tbarquivosEquipamentoList;
    @JoinColumn(name = "idtecnica", referencedColumnName = "idtecnica")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbtecnica idtecnica;
    @JoinColumn(name = "idinspecao", referencedColumnName = "idinspecao")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbinspecao idinspecao;

    public Tbequipamento() {
    }

    public Tbequipamento(Integer idequipamento) {
        this.idequipamento = idequipamento;
    }

    public Tbequipamento(Integer idequipamento, int criticidade) {
        this.idequipamento = idequipamento;
        this.criticidade = criticidade;
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

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    public Tbtecnica getIdtecnica() {
        return idtecnica;
    }

    public void setIdtecnica(Tbtecnica idtecnica) {
        this.idtecnica = idtecnica;
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
        return idequipamento;
    }

    @XmlTransient
    public List<TbarquivosEquipamento> getTbarquivosEquipamentoList() {
        return tbarquivosEquipamentoList;
    }

    public void setTbarquivosEquipamentoList(List<TbarquivosEquipamento> tbarquivosEquipamentoList) {
        this.tbarquivosEquipamentoList = tbarquivosEquipamentoList;
    }

}

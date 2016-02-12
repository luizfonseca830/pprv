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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbinspecao")
public class Tbinspecao implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idinspecao")
    private Integer idinspecao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nminspecao")
    private String nminspecao;

    public Tbinspecao() {
    }

    public Tbinspecao(Integer idinspecao) {
        this.idinspecao = idinspecao;
    }

    public Tbinspecao(Integer idinspecao, String nminspecao) {
        this.idinspecao = idinspecao;
        this.nminspecao = nminspecao;
    }

    public Integer getIdinspecao() {
        return idinspecao;
    }

    public void setIdinspecao(Integer idinspecao) {
        this.idinspecao = idinspecao;
    }

    public String getNminspecao() {
        return nminspecao;
    }

    public void setNminspecao(String nminspecao) {
        this.nminspecao = nminspecao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idinspecao != null ? idinspecao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbinspecao)) {
            return false;
        }
        Tbinspecao other = (Tbinspecao) object;
        if ((this.idinspecao == null && other.idinspecao != null) || (this.idinspecao != null && !this.idinspecao.equals(other.idinspecao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbinspecao[ idinspecao=" + idinspecao + " ]";
    }

    @Override
    public Integer getPK() {
        return idinspecao;
    }

}

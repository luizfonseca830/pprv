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
    @Column(name = "condicao")
    private Integer condicao;

    public Tbsubconjunto() {
    }

    public Tbsubconjunto(Integer idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
    }

    public Tbsubconjunto(Integer idsubconjunto, String nmsubconjunto) {
        this.idsubconjunto = idsubconjunto;
        this.nmsubconjunto = nmsubconjunto;
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

    public Integer getCondicao() {
        return condicao;
    }

    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
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
        return idsubconjunto;
    }

}

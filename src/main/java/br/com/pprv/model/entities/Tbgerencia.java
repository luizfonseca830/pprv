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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author ioliveira
 */
@Entity
@Table(name = "tbgerencia")
public class Tbgerencia implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idgerencia")
    private Integer idgerencia;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "nmgerencia")
    private String nmgerencia;
    @JoinColumn(name = "idinspecao", referencedColumnName = "idinspecao")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbinspecao idinspecao;

    public Tbgerencia() {
    }

    public Tbgerencia(Integer idgerencia) {
        this.idgerencia = idgerencia;
    }

    public Tbgerencia(Integer idgerencia, String nmgerencia) {
        this.idgerencia = idgerencia;
        this.nmgerencia = nmgerencia;
    }

    public Integer getIdgerencia() {
        return idgerencia;
    }

    public void setIdgerencia(Integer idgerencia) {
        this.idgerencia = idgerencia;
    }

    public String getNmgerencia() {
        return nmgerencia;
    }

    public void setNmgerencia(String nmgerencia) {
        this.nmgerencia = nmgerencia;
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
        hash += (idgerencia != null ? idgerencia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbgerencia)) {
            return false;
        }
        Tbgerencia other = (Tbgerencia) object;
        if ((this.idgerencia == null && other.idgerencia != null) || (this.idgerencia != null && !this.idgerencia.equals(other.idgerencia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbgerencia[ idgerencia=" + idgerencia + " ]";
    }

    @Override
    public Integer getPK() {
        return idgerencia;
    }

}

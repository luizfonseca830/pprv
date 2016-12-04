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
@Table(name = "tbperfil")
public class Tbperfil implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idperfil")
    private Integer idperfil;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nmperfil")
    private String nmperfil;
    @Basic(optional = false)
    @NotNull
    @Column(name = "nivelacessoperfil")
    private Integer nivelacessoperfil;

    public Tbperfil() {
    }

    public Tbperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public Tbperfil(Integer idperfil, String nmperfil) {
        this.idperfil = idperfil;
        this.nmperfil = nmperfil;
    }

    public Integer getIdperfil() {
        return idperfil;
    }

    public void setIdperfil(Integer idperfil) {
        this.idperfil = idperfil;
    }

    public String getNmperfil() {
        return nmperfil;
    }

    public void setNmperfil(String nmperfil) {
        this.nmperfil = nmperfil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idperfil != null ? idperfil.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbperfil)) {
            return false;
        }
        Tbperfil other = (Tbperfil) object;
        if ((this.idperfil == null && other.idperfil != null) || (this.idperfil != null && !this.idperfil.equals(other.idperfil))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbperfil[ idperfil=" + idperfil + " ]";
    }

    @Override
    public Integer getPK() {
        return idperfil;
    }

    /**
     * @return the nivelacessoperfil
     */
    public Integer getNivelacessoperfil() {
        return nivelacessoperfil;
    }

    /**
     * @param nivelacessoperfil the nivelacessoperfil to set
     */
    public void setNivelacessoperfil(Integer nivelacessoperfil) {
        this.nivelacessoperfil = nivelacessoperfil;
    }

}

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
 * @author JorgeFonseca
 */
@Entity
@Table(name = "tbtecnica")
public class Tbtecnica implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idtecnica")
    private Integer idtecnica;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 80)
    @Column(name = "nmtecnica")
    private String nmtecnica;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idinspecao", referencedColumnName = "idinspecao")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbinspecao idinspecao;
    @JoinColumn(name = "idgerencia", referencedColumnName = "idgerencia")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbgerencia idgerencia;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(fetch = FetchType.EAGER)
    private Tbequipamento idequipamento;

    public Tbtecnica() {
    }

    public Tbtecnica(Integer idtecnica) {
        this.idtecnica = idtecnica;
    }

    public Tbtecnica(Integer idtecnica, String nmtecnica) {
        this.idtecnica = idtecnica;
        this.nmtecnica = nmtecnica;
    }

    public Integer getIdtecnica() {
        return idtecnica;
    }

    public void setIdtecnica(Integer idtecnica) {
        this.idtecnica = idtecnica;
    }

    public String getNmtecnica() {
        return nmtecnica;
    }

    public void setNmtecnica(String nmtecnica) {
        this.nmtecnica = nmtecnica;
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

    public Tbgerencia getIdgerencia() {
        return idgerencia;
    }

    public void setIdgerencia(Tbgerencia idgerencia) {
        this.idgerencia = idgerencia;
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
        hash += (idtecnica != null ? idtecnica.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tbtecnica)) {
            return false;
        }
        Tbtecnica other = (Tbtecnica) object;
        if ((this.idtecnica == null && other.idtecnica != null) || (this.idtecnica != null && !this.idtecnica.equals(other.idtecnica))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tbtecnica[ idtecnica=" + idtecnica + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdtecnica();
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities;

import br.com.pprv.web.faces.converter.Identificador;
import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author JorgeFonseca
 */
@Entity
@Table(name = "tblaudo")
public class Tblaudo implements Serializable, Identificador<Integer> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idlaudo")
    private Integer idlaudo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2147483647)
    @Column(name = "diagnostico")
    private String diagnostico;
    @Basic(optional = false)
    @NotNull
    @Column(name = "limiteexecucao")
    @Temporal(TemporalType.DATE)
    private Date limiteexecucao;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 25)
    @Column(name = "ordemmanutencao")
    private String ordemmanutencao;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dataanalise")
    @Temporal(TemporalType.DATE)
    private Date dataanalise;
    @Basic(optional = false)
    @NotNull
    @Column(name = "datacadastro")
    @Temporal(TemporalType.DATE)
    private Date datacadastro;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idgerencia", referencedColumnName = "idgerencia")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbgerencia idgerencia;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbequipamento idequipamento;

    public Tblaudo() {
    }

    public Tblaudo(Integer idlaudo) {
        this.idlaudo = idlaudo;
    }

    public Tblaudo(Integer idlaudo, String status, String diagnostico, Date limiteexecucao, String ordemmanutencao, Date dataanalise, Date datacadastro) {
        this.idlaudo = idlaudo;
        this.status = status;
        this.diagnostico = diagnostico;
        this.limiteexecucao = limiteexecucao;
        this.ordemmanutencao = ordemmanutencao;
        this.dataanalise = dataanalise;
        this.datacadastro = datacadastro;
    }

    public Integer getIdlaudo() {
        return idlaudo;
    }

    public void setIdlaudo(Integer idlaudo) {
        this.idlaudo = idlaudo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public Date getLimiteexecucao() {
        return limiteexecucao;
    }

    public void setLimiteexecucao(Date limiteexecucao) {
        this.limiteexecucao = limiteexecucao;
    }

    public String getOrdemmanutencao() {
        return ordemmanutencao;
    }

    public void setOrdemmanutencao(String ordemmanutencao) {
        this.ordemmanutencao = ordemmanutencao;
    }

    public Date getDataanalise() {
        return dataanalise;
    }

    public void setDataanalise(Date dataanalise) {
        this.dataanalise = dataanalise;
    }

    public Date getDatacadastro() {
        return datacadastro;
    }

    public void setDatacadastro(Date datacadastro) {
        this.datacadastro = datacadastro;
    }

    public Tbsubconjunto getIdsubconjunto() {
        return idsubconjunto;
    }

    public void setIdsubconjunto(Tbsubconjunto idsubconjunto) {
        this.idsubconjunto = idsubconjunto;
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
        hash += (idlaudo != null ? idlaudo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tblaudo)) {
            return false;
        }
        Tblaudo other = (Tblaudo) object;
        if ((this.idlaudo == null && other.idlaudo != null) || (this.idlaudo != null && !this.idlaudo.equals(other.idlaudo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "br.com.pprv.model.entities.Tblaudo[ idlaudo=" + idlaudo + " ]";
    }

    @Override
    public Integer getPK() {
        return getIdlaudo();
    }

}

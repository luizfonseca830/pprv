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
    @Column(name = "nmrecomendacao")
    private String nmrecomendacao;
    @Basic(optional = false)
    @Column(name = "nmdiagnostico")
    private String nmdiagnostico;
    @Basic(optional = false)
    @Column(name = "limiteexecucao")
    @Temporal(TemporalType.DATE)
    private Date limiteexecucao;
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
    @Column(name = "boolos")
    private Boolean boolos;
    @Column(name = "osmaximo")
    private Integer osmaximo;
    @Basic(optional = false)
    @Column(name = "dtdatalaudo")
    @Temporal(TemporalType.DATE)
    private Date dtdatalaudo;
    @Basic(optional = false)
    @Column(name = "tmdatalaudo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatalaudo;
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

    public Tblaudo(Integer idlaudo, String nmrecomendacao, String nmdiagnostico, Date limiteexecucao, Date dataanalise, Date datacadastro, Date dtdatalaudo, Date tmdatalaudo) {
        this.idlaudo = idlaudo;
        this.nmrecomendacao = nmrecomendacao;
        this.nmdiagnostico = nmdiagnostico;
        this.limiteexecucao = limiteexecucao;
        this.dataanalise = dataanalise;
        this.datacadastro = datacadastro;
        this.dtdatalaudo = dtdatalaudo;
        this.tmdatalaudo = tmdatalaudo;
    }

    public Integer getIdlaudo() {
        return idlaudo;
    }

    public void setIdlaudo(Integer idlaudo) {
        this.idlaudo = idlaudo;
    }

    public Date getLimiteexecucao() {
        return limiteexecucao;
    }

    public void setLimiteexecucao(Date limiteexecucao) {
        this.limiteexecucao = limiteexecucao;
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

    /**
     * @return the nmrecomendacao
     */
    public String getNmrecomendacao() {
        return nmrecomendacao;
    }

    /**
     * @param nmrecomendacao the nmrecomendacao to set
     */
    public void setNmrecomendacao(String nmrecomendacao) {
        this.nmrecomendacao = nmrecomendacao;
    }

    /**
     * @return the nmdiagnostico
     */
    public String getNmdiagnostico() {
        return nmdiagnostico;
    }

    /**
     * @param nmdiagnostico the nmdiagnostico to set
     */
    public void setNmdiagnostico(String nmdiagnostico) {
        this.nmdiagnostico = nmdiagnostico;
    }

    /**
     * @return the boolos
     */
    public Boolean getBoolos() {
        return boolos;
    }

    /**
     * @param boolos the boolos to set
     */
    public void setBoolos(Boolean boolos) {
        this.boolos = boolos;
    }

    /**
     * @return the osmaximo
     */
    public Integer getOsmaximo() {
        return osmaximo;
    }

    /**
     * @param osmaximo the osmaximo to set
     */
    public void setOsmaximo(Integer osmaximo) {
        this.osmaximo = osmaximo;
    }

    /**
     * @return the tmdatalaudo
     */
    public Date getTmdatalaudo() {
        return tmdatalaudo;
    }

    /**
     * @param tmdatalaudo the tmdatalaudo to set
     */
    public void setTmdatalaudo(Date tmdatalaudo) {
        this.tmdatalaudo = tmdatalaudo;
    }

    /**
     * @return the dtdatalaudo
     */
    public Date getDtdatalaudo() {
        return dtdatalaudo;
    }

    /**
     * @param dtdatalaudo the dtdatalaudo to set
     */
    public void setDtdatalaudo(Date dtdatalaudo) {
        this.dtdatalaudo = dtdatalaudo;
    }

}

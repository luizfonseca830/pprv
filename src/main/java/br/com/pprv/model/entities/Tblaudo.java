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
 * @author ioliveira
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
    @Column(name = "nmrecomendacao")
    private String nmrecomendacao;
    @Column(name = "nmdiagnostico")
    private String nmdiagnostico;
    @Column(name = "limiteexecucao")
    @Temporal(TemporalType.DATE)
    private Date limiteexecucao;
    @Basic(optional = false)
    @Column(name = "dtdatacadastro")
    @Temporal(TemporalType.DATE)
    private Date dtdatacadastro;
    @Column(name = "boolomsap")
    private Boolean boolomsap;
    @Column(name = "omsap")
    private String omsap;
    @Basic(optional = false)
    @Column(name = "dtdatalaudo")
    @Temporal(TemporalType.DATE)
    private Date dtdatalaudo;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tmdatalaudo")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdatalaudo;
    @Column(name = "nmobservacao")
    private String nmobservacao;
    @Column(name = "nmrisco")
    private String nmrisco;
    @Basic(optional = false)
    @Column(name = "prazoexecucao")
    private Integer prazoexecucao;
    @Basic(optional = false)
    @Column(name = "condicao")
    private Integer condicao;
    @Column(name = "responsavel")
    private String responsavel;
    @Basic(optional = false)
    @Column(name = "dtdataexecucao")
    @Temporal(TemporalType.DATE)
    private Date dtdataexecucao;
    @Basic(optional = false)
    @Column(name = "tmdataexecucao")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmdataexecucao;
    @JoinColumn(name = "idsubconjunto", referencedColumnName = "idsubconjunto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbsubconjunto idsubconjunto;
    @JoinColumn(name = "idgerencia", referencedColumnName = "idgerencia")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbgerencia idgerencia;
    @JoinColumn(name = "idequipamento", referencedColumnName = "idequipamento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbequipamento idequipamento;

    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Tbusuario idusuario;

    public Tblaudo() {
    }

    public Tblaudo(Integer idlaudo) {
        this.idlaudo = idlaudo;
    }

    public Tblaudo(Integer idlaudo, Date dtdatacadastro, Date dtdatalaudo, Date tmdatalaudo, int prazoexecucao) {
        this.idlaudo = idlaudo;
        this.dtdatacadastro = dtdatacadastro;
        this.dtdatalaudo = dtdatalaudo;
        this.tmdatalaudo = tmdatalaudo;
        this.prazoexecucao = prazoexecucao;
    }

    public Integer getIdlaudo() {
        return idlaudo;
    }

    public void setIdlaudo(Integer idlaudo) {
        this.idlaudo = idlaudo;
    }

    public String getNmrecomendacao() {
        return nmrecomendacao;
    }

    public void setNmrecomendacao(String nmrecomendacao) {
        this.nmrecomendacao = nmrecomendacao;
    }

    public String getNmdiagnostico() {
        return nmdiagnostico;
    }

    public void setNmdiagnostico(String nmdiagnostico) {
        this.nmdiagnostico = nmdiagnostico;
    }

    public Date getLimiteexecucao() {
        return limiteexecucao;
    }

    public void setLimiteexecucao(Date limiteexecucao) {
        this.limiteexecucao = limiteexecucao;
    }

    /**
     * @return the dtdatacadastro
     */
    public Date getDtdatacadastro() {
        return dtdatacadastro;
    }

    /**
     * @param dtdatacadastro the dtdatacadastro to set
     */
    public void setDtdatacadastro(Date dtdatacadastro) {
        this.dtdatacadastro = dtdatacadastro;
    }

    /**
     * @return the boolomsap
     */
    public Boolean getBoolomsap() {
        return boolomsap;
    }

    /**
     * @param boolomsap the boolomsap to set
     */
    public void setBoolomsap(Boolean boolomsap) {
        this.boolomsap = boolomsap;
    }

    /**
     * @return the nmobservacao
     */
    public String getNmobservacao() {
        return nmobservacao;
    }

    /**
     * @param nmobservacao the nmobservacao to set
     */
    public void setNmobservacao(String nmobservacao) {
        this.nmobservacao = nmobservacao;
    }

    /**
     * @return the nmrisco
     */
    public String getNmrisco() {
        return nmrisco;
    }

    /**
     * @param nmrisco the nmrisco to set
     */
    public void setNmrisco(String nmrisco) {
        this.nmrisco = nmrisco;
    }

    /**
     * @return the prazoexecucao
     */
    public Integer getPrazoexecucao() {
        return prazoexecucao;
    }

    /**
     * @param prazoexecucao the prazoexecucao to set
     */
    public void setPrazoexecucao(Integer prazoexecucao) {
        this.prazoexecucao = prazoexecucao;
    }

    public Date getDtdatalaudo() {
        return dtdatalaudo;
    }

    public void setDtdatalaudo(Date dtdatalaudo) {
        this.dtdatalaudo = dtdatalaudo;
    }

    public Date getTmdatalaudo() {
        return tmdatalaudo;
    }

    public void setTmdatalaudo(Date tmdatalaudo) {
        this.tmdatalaudo = tmdatalaudo;
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
        return idlaudo;
    }

    /**
     * @return the condicao
     */
    public Integer getCondicao() {
        return condicao;
    }

    /**
     * @param condicao the condicao to set
     */
    public void setCondicao(Integer condicao) {
        this.condicao = condicao;
    }

    /**
     * @return the omsap
     */
    public String getOmsap() {
        return omsap;
    }

    /**
     * @param omsap the omsap to set
     */
    public void setOmsap(String omsap) {
        this.omsap = omsap;
    }

    /**
     * @return the responsavel
     */
    public String getResponsavel() {
        return responsavel;
    }

    /**
     * @param responsavel the responsavel to set
     */
    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    /**
     * @return the dtdataexecucao
     */
    public Date getDtdataexecucao() {
        return dtdataexecucao;
    }

    /**
     * @param dtdataexecucao the dtdataexecucao to set
     */
    public void setDtdataexecucao(Date dtdataexecucao) {
        this.dtdataexecucao = dtdataexecucao;
    }

    /**
     * @return the tmdataexecucao
     */
    public Date getTmdataexecucao() {
        return tmdataexecucao;
    }

    /**
     * @param tmdataexecucao the tmdataexecucao to set
     */
    public void setTmdataexecucao(Date tmdataexecucao) {
        this.tmdataexecucao = tmdataexecucao;
    }

    /**
     * @return the idusuario
     */
    public Tbusuario getIdusuario() {
        return idusuario;
    }

    /**
     * @param idusuario the idusuario to set
     */
    public void setIdusuario(Tbusuario idusuario) {
        this.idusuario = idusuario;
    }

}

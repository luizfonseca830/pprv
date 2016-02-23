/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities.custom;

import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbgerencia;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ioliveira
 */
public class LaudoMdl implements Serializable {

    private Integer idEquipamentoSubconjunto;
    private String nmDiagnostico;
    private String nmRecomendacao;
    private Tbgerencia tbgerencia;
    private Date dtDatalimiteExecucao;
    private Date dtDataAnalise;
    private Date dtDataCadastro;
    private Integer intOsMaximo;
    private boolean naoPreencherOs;
    private Integer prazoExecucao;
    private String nmObservacao;
    private String nmRisco;
    private TbequipamentoSubconjunto Tbequipamentosubconjunto;

    public LaudoMdl() {
        dtDataCadastro = new Date();
        dtDataAnalise = new Date();
        naoPreencherOs = false;
    }

    /**
     * @return the idEquipamentoSubconjunto
     */
    public Integer getIdEquipamentoSubconjunto() {
        return idEquipamentoSubconjunto;
    }

    /**
     * @param idEquipamentoSubconjunto the idEquipamentoSubconjunto to set
     */
    public void setIdEquipamentoSubconjunto(Integer idEquipamentoSubconjunto) {
        this.idEquipamentoSubconjunto = idEquipamentoSubconjunto;
    }

    /**
     * @return the nmDiagnostico
     */
    public String getNmDiagnostico() {
        return nmDiagnostico;
    }

    /**
     * @param nmDiagnostico the nmDiagnostico to set
     */
    public void setNmDiagnostico(String nmDiagnostico) {
        this.nmDiagnostico = nmDiagnostico;
    }

    /**
     * @return the nmRecomendacao
     */
    public String getNmRecomendacao() {
        return nmRecomendacao;
    }

    /**
     * @param nmRecomendacao the nmRecomendacao to set
     */
    public void setNmRecomendacao(String nmRecomendacao) {
        this.nmRecomendacao = nmRecomendacao;
    }

    /**
     * @return the tbgerencia
     */
    public Tbgerencia getTbgerencia() {
        return tbgerencia;
    }

    /**
     * @param tbgerencia the tbgerencia to set
     */
    public void setTbgerencia(Tbgerencia tbgerencia) {
        this.tbgerencia = tbgerencia;
    }

    /**
     * @return the dtDatalimiteExecucao
     */
    public Date getDtDatalimiteExecucao() {
        return dtDatalimiteExecucao;
    }

    /**
     * @param dtDatalimiteExecucao the dtDatalimiteExecucao to set
     */
    public void setDtDatalimiteExecucao(Date dtDatalimiteExecucao) {
        this.dtDatalimiteExecucao = dtDatalimiteExecucao;
    }

    /**
     * @return the dtDataAnalise
     */
    public Date getDtDataAnalise() {
        return dtDataAnalise;
    }

    /**
     * @param dtDataAnalise the dtDataAnalise to set
     */
    public void setDtDataAnalise(Date dtDataAnalise) {
        this.dtDataAnalise = dtDataAnalise;
    }

    /**
     * @return the dtDataCadastro
     */
    public Date getDtDataCadastro() {
        return dtDataCadastro;
    }

    /**
     * @param dtDataCadastro the dtDataCadastro to set
     */
    public void setDtDataCadastro(Date dtDataCadastro) {
        this.dtDataCadastro = dtDataCadastro;
    }

    /**
     * @return the intOsMaximo
     */
    public Integer getIntOsMaximo() {
        return intOsMaximo;
    }

    /**
     * @param intOsMaximo the intOsMaximo to set
     */
    public void setIntOsMaximo(Integer intOsMaximo) {
        this.intOsMaximo = intOsMaximo;
    }

    /**
     * @return the tbequipamentoSubconjunto
     */
    public TbequipamentoSubconjunto getTbequipamentoSubconjunto() {
        return Tbequipamentosubconjunto;
    }

    /**
     * @param Tbequipamentosubconjunto the tbequipamentoSubconjunto to set
     */
    public void setTbequipamentoSubconjunto(TbequipamentoSubconjunto Tbequipamentosubconjunto) {
        this.Tbequipamentosubconjunto = Tbequipamentosubconjunto;
    }

    /**
     * @return the naoPreencherOs
     */
    public boolean isNaoPreencherOs() {
        return naoPreencherOs;
    }

    /**
     * @param naoPreencherOs the naoPreencherOs to set
     */
    public void setNaoPreencherOs(boolean naoPreencherOs) {
        this.naoPreencherOs = naoPreencherOs;
    }

    /**
     * @return the nmObservacao
     */
    public String getNmObservacao() {
        return nmObservacao;
    }

    /**
     * @param nmObservacao the nmObservacao to set
     */
    public void setNmObservacao(String nmObservacao) {
        this.nmObservacao = nmObservacao;
    }

    /**
     * @return the nmRisco
     */
    public String getNmRisco() {
        return nmRisco;
    }

    /**
     * @param nmRisco the nmRisco to set
     */
    public void setNmRisco(String nmRisco) {
        this.nmRisco = nmRisco;
    }

    /**
     * @return the prazoExecucao
     */
    public Integer getPrazoExecucao() {
        return prazoExecucao;
    }

    /**
     * @param prazoExecucao the prazoExecucao to set
     */
    public void setPrazoExecucao(Integer prazoExecucao) {
        this.prazoExecucao = prazoExecucao;
    }

}

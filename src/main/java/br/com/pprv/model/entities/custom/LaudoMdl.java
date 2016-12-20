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
    private Date dtDataCadastro;
    private String strOmSap;
    private boolean naoPreencherOmSap;
    private Integer prazoExecucao;
    private String nmObservacao;
    private String nmRisco;
    private TbequipamentoSubconjunto Tbequipamentosubconjunto;
    private Integer situation;
    private Integer idLaudo;
    private Date dtDataExecucao;
    private Date tmDataExecucao;

    public LaudoMdl() {
        dtDataCadastro = new Date();
        naoPreencherOmSap = false;
        situation = 1;
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

    /**
     * @return the naoPreencherOmSap
     */
    public boolean isNaoPreencherOmSap() {
        return naoPreencherOmSap;
    }

    /**
     * @param naoPreencherOmSap the naoPreencherOmSap to set
     */
    public void setNaoPreencherOmSap(boolean naoPreencherOmSap) {
        this.naoPreencherOmSap = naoPreencherOmSap;
    }

    /**
     * @return the strOmSap
     */
    public String getStrOmSap() {
        return strOmSap;
    }

    /**
     * @param strOmSap the strOmSap to set
     */
    public void setStrOmSap(String strOmSap) {
        this.strOmSap = strOmSap;
    }

    /**
     * @return the situation
     */
    public Integer getSituation() {
        return situation;
    }

    /**
     * @param situation the situation to set
     */
    public void setSituation(Integer situation) {
        this.situation = situation;
    }

    /**
     * @return the idLaudo
     */
    public Integer getIdLaudo() {
        return idLaudo;
    }

    /**
     * @param idLaudo the idLaudo to set
     */
    public void setIdLaudo(Integer idLaudo) {
        this.idLaudo = idLaudo;
    }

    /**
     * @return the dtDataExecucao
     */
    public Date getDtDataExecucao() {
        return dtDataExecucao;
    }

    /**
     * @param dtDataExecucao the dtDataExecucao to set
     */
    public void setDtDataExecucao(Date dtDataExecucao) {
        this.dtDataExecucao = dtDataExecucao;
    }

    /**
     * @return the tmDataExecucao
     */
    public Date getTmDataExecucao() {
        return tmDataExecucao;
    }

    /**
     * @param tmDataExecucao the tmDataExecucao to set
     */
    public void setTmDataExecucao(Date tmDataExecucao) {
        this.tmDataExecucao = tmDataExecucao;
    }

}

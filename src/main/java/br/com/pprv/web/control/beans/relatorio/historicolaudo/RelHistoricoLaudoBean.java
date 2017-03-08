/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.relatorio.historicolaudo;

import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.control.report.historicolaudo.ReportHistoricoLaudo;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "relHistoricoLaudoBean")
@ViewScoped
public class RelHistoricoLaudoBean implements Serializable {

    @EJB
    private ReportHistoricoLaudo reportHistoricoLaudo;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private GerenciaLogic gerenciaLogic;

    private List<Tbgerencia> listTbgerencias;
    private List<Tbtecnica> listTbtecnica;
    private List<Tblaudo> listTblaudos;

    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;
    private Integer condicaoEquipamento;
    private Date dtAnalysisBegin;
    private Date dtAnalysisEnd;
    private Date dtLimitExecutionBegin;
    private Date dtLimitExecutionEnd;

    @PostConstruct
    public void init() {
        listTbgerencias = gerenciaLogic.findAllTbgerencia();
        listTbtecnica = tecnicaLogic.getListTbtecnica();
    }

    public void searchHistoric() {
        if (tbtecnica != null || tbgerencia != null || condicaoEquipamento != null || (dtAnalysisBegin != null && dtAnalysisEnd != null)) {
            listTblaudos = reportHistoricoLaudo.getHistoricLaudo(tbtecnica, tbgerencia, condicaoEquipamento, dtAnalysisBegin, dtAnalysisEnd);
            if (listTblaudos == null || listTblaudos.isEmpty()) {
                listTblaudos = null;
                AbstractFacesContextUtils.addMessageWarn("Nenhum resultado encontrado.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("É necessário preecher pelo menos um parâmetro de pesquisa.");
        }
    }

    public void printFile() {
        reportHistoricoLaudo.setTbtecnica(tbtecnica);
        reportHistoricoLaudo.setTbgerencia(tbgerencia);
        reportHistoricoLaudo.setCondicaoEquipamento(condicaoEquipamento);
        reportHistoricoLaudo.setDtAnalysisBegin(dtAnalysisBegin);
        reportHistoricoLaudo.setDtAnalysisEnd(dtAnalysisEnd);
        if (tbtecnica != null || tbgerencia != null || condicaoEquipamento != null || (dtAnalysisBegin != null && dtAnalysisEnd != null)) {
            if (reportHistoricoLaudo.preparaParam()) {
                reportHistoricoLaudo.createPdfReport();
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("É necessário preecher pelo menos um parâmetro de pesquisa.");
        }
    }

    /**
     * @return the listTbgerencias
     */
    public List<Tbgerencia> getListTbgerencias() {
        return listTbgerencias;
    }

    /**
     * @param listTbgerencias the listTbgerencias to set
     */
    public void setListTbgerencias(List<Tbgerencia> listTbgerencias) {
        this.listTbgerencias = listTbgerencias;
    }

    /**
     * @return the listTbtecnica
     */
    public List<Tbtecnica> getListTbtecnica() {
        return listTbtecnica;
    }

    /**
     * @param listTbtecnica the listTbtecnica to set
     */
    public void setListTbtecnica(List<Tbtecnica> listTbtecnica) {
        this.listTbtecnica = listTbtecnica;
    }

    /**
     * @return the tbtecnica
     */
    public Tbtecnica getTbtecnica() {
        return tbtecnica;
    }

    /**
     * @param tbtecnica the tbtecnica to set
     */
    public void setTbtecnica(Tbtecnica tbtecnica) {
        this.tbtecnica = tbtecnica;
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
     * @return the condicaoEquipamento
     */
    public Integer getCondicaoEquipamento() {
        return condicaoEquipamento;
    }

    /**
     * @param condicaoEquipamento the condicaoEquipamento to set
     */
    public void setCondicaoEquipamento(Integer condicaoEquipamento) {
        this.condicaoEquipamento = condicaoEquipamento;
    }

    /**
     * @return the listTblaudos
     */
    public List<Tblaudo> getListTblaudos() {
        return listTblaudos;
    }

    /**
     * @param listTblaudos the listTblaudos to set
     */
    public void setListTblaudos(List<Tblaudo> listTblaudos) {
        this.listTblaudos = listTblaudos;
    }

    /**
     * @return the dtAnalysisBegin
     */
    public Date getDtAnalysisBegin() {
        return dtAnalysisBegin;
    }

    /**
     * @param dtAnalysisBegin the dtAnalysisBegin to set
     */
    public void setDtAnalysisBegin(Date dtAnalysisBegin) {
        this.dtAnalysisBegin = dtAnalysisBegin;
    }

    /**
     * @return the dtAnalysisEnd
     */
    public Date getDtAnalysisEnd() {
        return dtAnalysisEnd;
    }

    /**
     * @param dtAnalysisEnd the dtAnalysisEnd to set
     */
    public void setDtAnalysisEnd(Date dtAnalysisEnd) {
        this.dtAnalysisEnd = dtAnalysisEnd;
    }

    /**
     * @return the dtLimitExecutionBegin
     */
    public Date getDtLimitExecutionBegin() {
        return dtLimitExecutionBegin;
    }

    /**
     * @param dtLimitExecutionBegin the dtLimitExecutionBegin to set
     */
    public void setDtLimitExecutionBegin(Date dtLimitExecutionBegin) {
        this.dtLimitExecutionBegin = dtLimitExecutionBegin;
    }

    /**
     * @return the dtLimitExecutionEnd
     */
    public Date getDtLimitExecutionEnd() {
        return dtLimitExecutionEnd;
    }

    /**
     * @param dtLimitExecutionEnd the dtLimitExecutionEnd to set
     */
    public void setDtLimitExecutionEnd(Date dtLimitExecutionEnd) {
        this.dtLimitExecutionEnd = dtLimitExecutionEnd;
    }

}

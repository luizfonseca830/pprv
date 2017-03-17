/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.relatorio.equipamentoscomlaudo;

import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.control.report.equipamentoscomlaudo.ReportEquipamentosComLaudo;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "relEquipamentosComLaudoBean")
@ViewScoped
public class RelEquipamentosComLaudoBean implements Serializable {

    @EJB
    private ReportEquipamentosComLaudo reportEquipamentosComLaudo;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private GerenciaLogic gerenciaLogic;

    private List<Tblaudo> listTblaudos;
    private List<Tbgerencia> listTbgerencias;
    private List<Tbtecnica> listTbtecnica;

    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;

    @PostConstruct
    public void init() {
        listTblaudos = reportEquipamentosComLaudo.findAllTbequipamentosComLaudo();
        listTbgerencias = gerenciaLogic.findAllTbgerencia();
        listTbtecnica = tecnicaLogic.getListTbtecnica();
        printToPdf();
    }

    public void printToPdf() {

        reportEquipamentosComLaudo.setTbgerencia(tbgerencia);
        reportEquipamentosComLaudo.setTbtecnica(tbtecnica);

        if (reportEquipamentosComLaudo.preparaParam()) {
//            reportEquipamentosComLaudo.createPdfReport();
        }
    }

    public void search() {

        listTblaudos = reportEquipamentosComLaudo.findTbequipamentoWithLaudoByTecnicaAndGerencia(tbtecnica, tbgerencia);

        if (listTblaudos == null || listTblaudos.isEmpty()) {
            listTblaudos = null;
            AbstractFacesContextUtils.addMessageWarn("Nenhum resultado encontrado.");
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.relatorio.equipamento;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.control.report.equipamentos.ReportEquipamentos;
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
@ManagedBean(name = "relEquipamentosBean")
@ViewScoped
public class RelEquipamentosBean implements Serializable {

    @EJB
    private ReportEquipamentos reportEquipamentos;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private GerenciaLogic gerenciaLogic;

    private List<Tbequipamento> listTbequipamentosComLaudos;
    private List<Tbequipamento> listTbequipamentosFiltered;
    private List<Tbgerencia> listTbgerencias;
    private List<Tbtecnica> listTbtecnica;

    private Integer condicaoEquipamento;
    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;

    @PostConstruct
    public void init() {
        listTbgerencias = gerenciaLogic.findAllTbgerencia();
        listTbtecnica = tecnicaLogic.getListTbtecnica();
    }

    public void searchEquipamento() {
        if (condicaoEquipamento != null || tbtecnica != null || tbgerencia != null) {
            listTbequipamentosComLaudos = reportEquipamentos.findAllTbequipamentosByCondicaoOrTecnicaOrGerencia(condicaoEquipamento, tbtecnica, tbgerencia);
            if (listTbequipamentosComLaudos == null || listTbequipamentosComLaudos.isEmpty()) {
                AbstractFacesContextUtils.addMessageInfo("Nenhum equipamento encontrado.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma condição selecionada para pesquisar.");
        }
    }

    public void printToPdf() {

        reportEquipamentos.setCondicaoEquipamento(condicaoEquipamento);
        reportEquipamentos.setTbgerencia(tbgerencia);
        reportEquipamentos.setTbtecnica(tbtecnica);

        if (reportEquipamentos.preparaParam()) {
            reportEquipamentos.createPdfReport();
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma opção selecionada para pesquisar.");
        }
    }

    public String getStatusDesc(final Integer condicao) {

        if (condicao != null) {
            switch (condicao) {
                case 1:
                    return "Normal";
                case 2:
                    return "Alerta";
                case 3:
                    return "Critico";
                case 4:
                    return "Executado";
            }
        }else{
            return "não preenchida";
        }

        return "";
    }

    /**
     * @return the listTbequipamentosComLaudos
     */
    public List<Tbequipamento> getListTbequipamentosComLaudos() {
        return listTbequipamentosComLaudos;
    }

    /**
     * @param listTbequipamentosComLaudos the listTbequipamentosComLaudos to set
     */
    public void setListTbequipamentosComLaudos(List<Tbequipamento> listTbequipamentosComLaudos) {
        this.listTbequipamentosComLaudos = listTbequipamentosComLaudos;
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
     * @return the listTbequipamentosFiltered
     */
    public List<Tbequipamento> getListTbequipamentosFiltered() {
        return listTbequipamentosFiltered;
    }

    /**
     * @param listTbequipamentosFiltered the listTbequipamentosFiltered to set
     */
    public void setListTbequipamentosFiltered(List<Tbequipamento> listTbequipamentosFiltered) {
        this.listTbequipamentosFiltered = listTbequipamentosFiltered;
    }

}

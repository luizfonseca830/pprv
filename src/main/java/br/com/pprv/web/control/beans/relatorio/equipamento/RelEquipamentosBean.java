/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.relatorio.equipamento;

import br.com.pprv.model.entities.Tbequipamento;
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

    private List<Tbequipamento> listTbequipamentosComLaudos;

    private Integer condicaoEquipamento;

    @PostConstruct
    public void init() {

    }

    public void searchEquipamento() {
        if (condicaoEquipamento != null) {
            listTbequipamentosComLaudos = reportEquipamentos.findAllTbequipamentosByCondicao(condicaoEquipamento);
            if (listTbequipamentosComLaudos == null || listTbequipamentosComLaudos.isEmpty()) {
                AbstractFacesContextUtils.addMessageInfo("Nenhum equipamento encontrado.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma condição selecionada para pesquisar.");
        }
    }

    public void printToPdf() {

        reportEquipamentos.setCondicaoEquipamento(condicaoEquipamento);

        if (reportEquipamentos.preparaParam()) {
            reportEquipamentos.createPdfReport();
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma condição selecionada para pesquisar.");
        }
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

}

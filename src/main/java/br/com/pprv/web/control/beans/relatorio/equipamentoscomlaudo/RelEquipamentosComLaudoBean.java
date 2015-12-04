/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.relatorio.equipamentoscomlaudo;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.web.control.report.equipamentoscomlaudo.ReportEquipamentosComLaudo;
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

    private List<Tbequipamento> listTbequipamentosComLaudos;

    @PostConstruct
    public void init() {
        listTbequipamentosComLaudos = reportEquipamentosComLaudo.findAllTbequipamentosComLaudos();
    }

    public void printToPdf() {
        if (reportEquipamentosComLaudo.preparaParam()) {
            reportEquipamentosComLaudo.createPdfReport();
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.equipamento;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author JorgeFonseca
 */
@ViewScoped
@ManagedBean
public class EquipamentoBean implements Serializable {

    @EJB
    private EquipamentoLogic equipamentoLogic;
    private String pesquisar;
    private List<Tbequipamento> listTbequipamento;
    private Tbtecnica tbtecnica;
    private Tbequipamento tbequipamento;

    @PostConstruct
    public void init() {

        int idequipamento = AbstractFacesContextUtils.getParamInt("idequipamento");
        if (idequipamento > 0) {
            tbequipamento = equipamentoLogic.find(idequipamento);
        }
    }

    public void search() {
        System.out.println("" + tbtecnica);
        listTbequipamento = equipamentoLogic.findTbequipamentoByTbtecnica(tbtecnica);
    }

    /**
     * @return the pesquisar
     */
    public String getPesquisar() {
        return pesquisar;
    }

    /**
     * @param pesquisar the pesquisar to set
     */
    public void setPesquisar(String pesquisar) {
        this.pesquisar = pesquisar;
    }

    /**
     * @return the listTbequipamento
     */
    public List<Tbequipamento> getListTbequipamento() {
        return listTbequipamento;
    }

    /**
     * @param listTbequipamento the listTbequipamento to set
     */
    public void setListTbequipamento(List<Tbequipamento> listTbequipamento) {
        this.listTbequipamento = listTbequipamento;
    }

}

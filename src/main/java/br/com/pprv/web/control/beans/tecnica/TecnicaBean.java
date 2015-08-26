/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.tecnica;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
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
@ManagedBean(name = "tecnicaBean")
@ViewScoped
public class TecnicaBean implements Serializable {

    @EJB
    private EquipamentoLogic equipamentoLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;

    private Tbtecnica tbtecnica;
    private Tbequipamento tbequipamento;
    private List<Tbtecnica> listTbtecnica;
    private int idtecnica;
    private int idequipamento;
    private List<Tbequipamento> listTbequipamento;

    @PostConstruct
    public void init() {
        listTbtecnica = tecnicaLogic.listallTecnica();
       

        idtecnica = AbstractFacesContextUtils.getParamInt("idtecnica");

        idequipamento = AbstractFacesContextUtils.getParamInt("idequipamento");

        if (idtecnica > 0) {
            tbtecnica = tecnicaLogic.find(idtecnica);
        }

        if (idequipamento > 0) {
            tbequipamento = equipamentoLogic.find(idequipamento);
        }

    }

    public void search() {
        System.out.println("" + tbtecnica);
        listTbequipamento = equipamentoLogic.findTbequipamentoByTbtecnica(tbtecnica);
    }

    /**
     * @return the tecnicaLogic
     */
    public TecnicaLogic getTecnicaLogic() {
        return tecnicaLogic;
    }

    /**
     * @param tecnicaLogic the tecnicaLogic to set
     */
    public void setTecnicaLogic(TecnicaLogic tecnicaLogic) {
        this.tecnicaLogic = tecnicaLogic;
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

    /**
     * @return the idtecnica
     */
    public int getIdtecnica() {
        return idtecnica;
    }

    /**
     * @param idtecnica the idtecnica to set
     */
    public void setIdtecnica(int idtecnica) {
        this.idtecnica = idtecnica;
    }

    /**
     * @return the idequipamento
     */
    public int getIdequipamento() {
        return idequipamento;
    }

    /**
     * @param idequipamento the idequipamento to set
     */
    public void setIdequipamento(int idequipamento) {
        this.idequipamento = idequipamento;
    }

    /**
     * @return the tbequipamento
     */
    public Tbequipamento getTbequipamento() {
        return tbequipamento;
    }

    /**
     * @param tbequipamento the tbequipamento to set
     */
    public void setTbequipamento(Tbequipamento tbequipamento) {
        this.tbequipamento = tbequipamento;
    }
}

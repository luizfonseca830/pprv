/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.fecharlaudo;

import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.model.entities.custom.FecharLaudoModel;
import br.com.pprv.web.control.logic.laudo.LaudoLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.faces.constants.StatusConstants;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "closeLaudoBean")
@ViewScoped
public class CloseLaudoBean implements Serializable {

    @EJB
    private LaudoLogic laudoLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;

    private List<Tblaudo> listTblaudos;
    private List<Tblaudo> listTblaudosFilters;
    private List<Tbtecnica> listTbtecnica;
    private List<Tblaudo> listTblaudoSelection;
    private List<FecharLaudoModel> listFecharLaudoModels;

    private Integer condicaoFilter;
    private Tbtecnica tblaudoFilter;
    private Tblaudo tblaudoSelected;

    @PostConstruct
    public void init() {
        listTblaudos = laudoLogic.findAllTblaudo();
        createFecharLaudoList();
        listTbtecnica = tecnicaLogic.listallTecnica();
    }

    public void search() {
        if (condicaoFilter != null
                && condicaoFilter >= 1) {
            listTblaudos = laudoLogic.findAllTblaudoByCondition(condicaoFilter);
            createFecharLaudoList();
        } else {
            listTblaudos = laudoLogic.findAllTblaudo();
            createFecharLaudoList();            
        }
    }

    public void changeStatusToDone() {

        boolean result = false;

        if (verifyThereIsAtLeastOneSelected()) {
            for (final FecharLaudoModel laudo : listFecharLaudoModels) {
                if (laudo.isExecutar()) {
                    laudo.getTblaudo().setCondicao(StatusConstants.STATUS_LAUDO_EXECUTADO);
                    laudo.getTblaudo().setTmdataexecucao(laudo.getTblaudo().getDtdataexecucao());
                    result = laudoLogic.editTblaudo(laudo.getTblaudo());
                }
            }

            if (result) {
                listFecharLaudoModels = null;
                AbstractFacesContextUtils.addMessageInfo("Laudos executados com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Falha ao execuatr laudos.");
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhum laudo selecionado para executar.");
        }
    }

    private void createFecharLaudoList() {
        listFecharLaudoModels = new ArrayList<>();
        if (listTblaudos != null
                && !listTblaudos.isEmpty()) {
            FecharLaudoModel fecharLaudoModel;
            for (Tblaudo listTblaudo : listTblaudos) {
                fecharLaudoModel = new FecharLaudoModel();
                fecharLaudoModel.setExecutar(false);
                fecharLaudoModel.setTblaudo(listTblaudo);
                listFecharLaudoModels.add(fecharLaudoModel);
            }
        } else {
            AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado.");
        }
    }

    public boolean verifyThereIsAtLeastOneSelected() {

        boolean result = false;

        if (listFecharLaudoModels != null
                && !listFecharLaudoModels.isEmpty()) {
            for (FecharLaudoModel f : listFecharLaudoModels) {
                if (f.isExecutar()) {
                    result = true;
                    break;
                }
            }
        }

        return result;
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
     * @return the listTblaudosFilters
     */
    public List<Tblaudo> getListTblaudosFilters() {
        return listTblaudosFilters;
    }

    /**
     * @param listTblaudosFilters the listTblaudosFilters to set
     */
    public void setListTblaudosFilters(List<Tblaudo> listTblaudosFilters) {
        this.listTblaudosFilters = listTblaudosFilters;
    }

    /**
     * @return the condicaoFilter
     */
    public Integer getCondicaoFilter() {
        return condicaoFilter;
    }

    /**
     * @param condicaoFilter the condicaoFilter to set
     */
    public void setCondicaoFilter(Integer condicaoFilter) {
        this.condicaoFilter = condicaoFilter;
    }

    /**
     * @return the tblaudoFilter
     */
    public Tbtecnica getTblaudoFilter() {
        return tblaudoFilter;
    }

    /**
     * @param tblaudoFilter the tblaudoFilter to set
     */
    public void setTblaudoFilter(Tbtecnica tblaudoFilter) {
        this.tblaudoFilter = tblaudoFilter;
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
     * @return the listTblaudoSelection
     */
    public List<Tblaudo> getListTblaudoSelection() {
        return listTblaudoSelection;
    }

    /**
     * @param listTblaudoSelection the listTblaudoSelection to set
     */
    public void setListTblaudoSelection(List<Tblaudo> listTblaudoSelection) {
        this.listTblaudoSelection = listTblaudoSelection;
    }

    /**
     * @return the tblaudoSelected
     */
    public Tblaudo getTblaudoSelected() {
        return tblaudoSelected;
    }

    /**
     * @param tblaudoSelected the tblaudoSelected to set
     */
    public void setTblaudoSelected(Tblaudo tblaudoSelected) {
        this.tblaudoSelected = tblaudoSelected;
    }

    /**
     * @return the listFecharLaudoModels
     */
    public List<FecharLaudoModel> getListFecharLaudoModels() {
        return listFecharLaudoModels;
    }

    /**
     * @param listFecharLaudoModels the listFecharLaudoModels to set
     */
    public void setListFecharLaudoModels(List<FecharLaudoModel> listFecharLaudoModels) {
        this.listFecharLaudoModels = listFecharLaudoModels;
    }

}

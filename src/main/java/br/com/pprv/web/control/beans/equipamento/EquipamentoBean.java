/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.equipamento;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.control.logic.equipamento_subconjunto.EquipamentoSubconjuntoLogic;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.faces.constants.PagesUrl;
import br.com.pprv.web.faces.constants.Resources;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;

/**
 *
 * @author JorgeFonseca
 */
@ViewScoped
@ManagedBean
public class EquipamentoBean implements Serializable {

    /**
     * logics below.
     */
    @EJB
    private EquipamentoLogic equipamentoLogic;
    @EJB
    private GerenciaLogic gerenciaLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private EquipamentoSubconjuntoLogic equipamentoSubconjuntoLogic;

    /**
     * objects below.
     */
    private Tbtecnica tbtecnicaSearch;
    private Tbgerencia tbgerenciaSearch;
    private Tbequipamento tbequipamento;
    private Tbequipamento tbequipamentoSelected;

    /**
     * list below.
     */
    private List<Tbequipamento> listTbequipamentoResult;
    private List<Tbequipamento> listTbequipamentoFilter;
    private List<Tbgerencia> listTbgerenciasSearch;
    private List<Tbtecnica> listTbtecnicasSearch;
    private List<Tbsubconjunto> listSubConjuntosEquipamento;
    private DualListModel<Tbsubconjunto> pickList;
    private List<Tbsubconjunto> listAllSubConjuntos;

    @PostConstruct
    public void init() {
        tbequipamento = new Tbequipamento();

        listTbgerenciasSearch = gerenciaLogic.findAllTbgerencia();
        listTbtecnicasSearch = tecnicaLogic.getListTbtecnica();
        listTbequipamentoResult = equipamentoLogic.allEquipamentos();

        Integer idEquipamento = AbstractFacesContextUtils.getParamInt("idEquipamento");
        if (idEquipamento != null && idEquipamento > 0) {
            tbequipamento = equipamentoLogic.find(idEquipamento);
            if (tbequipamento != null) {
                listSubConjuntosEquipamento = equipamentoSubconjuntoLogic.getListAllSubconjuntoByIdEquipamento(tbequipamento);
                if (listSubConjuntosEquipamento != null
                        && !listSubConjuntosEquipamento.isEmpty()) {
                    listAllSubConjuntos = equipamentoSubconjuntoLogic.getListAllSubconjuntoNotFromEquipamento(tbequipamento);
                } else {
                    listSubConjuntosEquipamento = new ArrayList<>();
                    listAllSubConjuntos = equipamentoSubconjuntoLogic.getListAllSubconjunto();
                }

                pickList = new DualListModel<>(listAllSubConjuntos, listSubConjuntosEquipamento);
            }
        }
    }

    public void search() {

        if (tbtecnicaSearch == null && tbgerenciaSearch == null) {
            listTbequipamentoResult = equipamentoLogic.allEquipamentos();
        } else {
            listTbequipamentoResult = equipamentoLogic.findTbequipamentoByTbtecnicaAndTbgerencia(tbtecnicaSearch, tbgerenciaSearch);
            if (listTbequipamentoResult == null || listTbequipamentoResult.isEmpty()) {
                listTbequipamentoResult.clear();
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado ancontrado.");
            }
        }
    }

    public void salvar() {

        if (tbequipamento.getNmequipamenta().trim().isEmpty()) {
            AbstractFacesContextUtils.addMessageWarn("Nome do equipamento deve ser preenchido.");
        } else if (tbequipamento.getDescequipamento().trim().isEmpty()) {
            AbstractFacesContextUtils.addMessageWarn("Descrição do equipamento deve ser preenchido.");
        } else if (tbequipamento.getCriticidade() == null) {
            AbstractFacesContextUtils.addMessageWarn("Criticidade do equipamento deve ser preenchido.");
        } else if (tbequipamento.getCondicao() == null) {
            AbstractFacesContextUtils.addMessageWarn("Condição do equipamento deve ser preenchido.");
        } else {
            tbequipamento.setNmequipamenta(tbequipamento.getNmequipamenta().toUpperCase());

            if (equipamentoLogic.create(tbequipamento)) {
                Map map = new HashMap();
                map.put("idEquipamento", tbequipamento.getIdequipamento());
                AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_EQUIPAMENTO, map);
                AbstractFacesContextUtils.addMessageInfo("Equipamento criado com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageInfo("Falha ao criar equipamento.");
            }
        }

    }

    public void editar() {

        if (tbequipamento.getNmequipamenta().trim().isEmpty()) {
            AbstractFacesContextUtils.addMessageWarn("Nome do equipamento deve ser preenchido.");
        } else if (tbequipamento.getDescequipamento().trim().isEmpty()) {
            AbstractFacesContextUtils.addMessageWarn("Descrição do equipamento deve ser preenchido.");
        } else if (tbequipamento.getCriticidade() == null) {
            AbstractFacesContextUtils.addMessageWarn("Criticidade do equipamento deve ser preenchido.");
        } else if (tbequipamento.getCondicao() == null) {
            AbstractFacesContextUtils.addMessageWarn("Condição do equipamento deve ser preenchido.");
        } else {
            tbequipamento.setNmequipamenta(tbequipamento.getNmequipamenta().toUpperCase());

            if (equipamentoLogic.editEquipamento(tbequipamento)) {
                Map map = new HashMap();
                map.put("idEquipamento", tbequipamento.getIdequipamento());
                AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_EQUIPAMENTO, map);
                AbstractFacesContextUtils.addMessageInfo("Equipamento alterado com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageInfo("Falha ao alterar equipamento.");
            }
        }
    }

    public void deleteEquipamento() {
        try {
            if (tbequipamentoSelected != null) {
                if (equipamentoLogic.removeTbequipamento(tbequipamentoSelected)) {
                    listTbequipamentoResult = equipamentoLogic.allEquipamentos();
                    AbstractFacesContextUtils.addMessageInfo("Equipamento removido com sucesso.");
                } else {
                    AbstractFacesContextUtils.addMessageWarn("Falha ao remover equipamento");
                }
            } else {
                System.out.println("delete equipamento: " + tbequipamentoSelected);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void asscobequipamentoSubconjunto() {

        boolean verifica = true;

        if (listSubConjuntosEquipamento != null
                && !listSubConjuntosEquipamento.isEmpty()) {
            equipamentoSubconjuntoLogic.deleteAllSubConjuntoByIdEquipamento(tbequipamento);
        }
        TbequipamentoSubconjunto subconjunto;
        for (Tbsubconjunto subTbsubconjuntoChose : pickList.getTarget()) {
            subconjunto = new TbequipamentoSubconjunto();
            subconjunto.setIdequipamento(tbequipamento);
            subconjunto.setIdsubconjunto(subTbsubconjuntoChose);

            if (equipamentoSubconjuntoLogic.createEquipamentoSubConjunto(subconjunto)) {
                verifica = true;
            } else {
                verifica = false;
            }
        }

        if (verifica) {
            Map map = new HashMap();
            map.put("idEquipamento", tbequipamento.getIdequipamento());
            AbstractFacesContextUtils.redirectPageWithParam(PagesUrl.URL_EQUIPAMENTO, map);
            AbstractFacesContextUtils.addMessageInfo("Lista de subconjunto associada com sucesso.");
        } else {
            AbstractFacesContextUtils.addMessageWarn("Falha ao criar lista de subconjunto.");
        }
    }

    /**
     * @return the tbtecnicaSearch
     */
    public Tbtecnica getTbtecnicaSearch() {
        return tbtecnicaSearch;
    }

    /**
     * @param tbtecnicaSearch the tbtecnicaSearch to set
     */
    public void setTbtecnicaSearch(Tbtecnica tbtecnicaSearch) {
        this.tbtecnicaSearch = tbtecnicaSearch;
    }

    /**
     * @return the tbgerenciaSearch
     */
    public Tbgerencia getTbgerenciaSearch() {
        return tbgerenciaSearch;
    }

    /**
     * @param tbgerenciaSearch the tbgerenciaSearch to set
     */
    public void setTbgerenciaSearch(Tbgerencia tbgerenciaSearch) {
        this.tbgerenciaSearch = tbgerenciaSearch;
    }

    /**
     * @return the listTbequipamentoResult
     */
    public List<Tbequipamento> getListTbequipamentoResult() {
        return listTbequipamentoResult;
    }

    /**
     * @param listTbequipamentoResult the listTbequipamentoResult to set
     */
    public void setListTbequipamentoResult(List<Tbequipamento> listTbequipamentoResult) {
        this.listTbequipamentoResult = listTbequipamentoResult;
    }

    /**
     * @return the listTbgerenciasSearch
     */
    public List<Tbgerencia> getListTbgerenciasSearch() {
        return listTbgerenciasSearch;
    }

    /**
     * @param listTbgerenciasSearch the listTbgerenciasSearch to set
     */
    public void setListTbgerenciasSearch(List<Tbgerencia> listTbgerenciasSearch) {
        this.listTbgerenciasSearch = listTbgerenciasSearch;
    }

    /**
     * @return the listTbtecnicasSearch
     */
    public List<Tbtecnica> getListTbtecnicasSearch() {
        return listTbtecnicasSearch;
    }

    /**
     * @param listTbtecnicasSearch the listTbtecnicasSearch to set
     */
    public void setListTbtecnicasSearch(List<Tbtecnica> listTbtecnicasSearch) {
        this.listTbtecnicasSearch = listTbtecnicasSearch;
    }

    /**
     * @return the listTbequipamentoFilter
     */
    public List<Tbequipamento> getListTbequipamentoFilter() {
        return listTbequipamentoFilter;
    }

    /**
     * @param listTbequipamentoFilter the listTbequipamentoFilter to set
     */
    public void setListTbequipamentoFilter(List<Tbequipamento> listTbequipamentoFilter) {
        this.listTbequipamentoFilter = listTbequipamentoFilter;
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

    /**
     * @return the tbequipamentoSelected
     */
    public Tbequipamento getTbequipamentoSelected() {
        return tbequipamentoSelected;
    }

    /**
     * @param tbequipamentoSelected the tbequipamentoSelected to set
     */
    public void setTbequipamentoSelected(Tbequipamento tbequipamentoSelected) {
        this.tbequipamentoSelected = tbequipamentoSelected;
    }

    /**
     * @return the listSubConjuntosEquipamento
     */
    public List<Tbsubconjunto> getListSubConjuntosEquipamento() {
        return listSubConjuntosEquipamento;
    }

    /**
     * @param listSubConjuntosEquipamento the listSubConjuntosEquipamento to set
     */
    public void setListSubConjuntosEquipamento(List<Tbsubconjunto> listSubConjuntosEquipamento) {
        this.listSubConjuntosEquipamento = listSubConjuntosEquipamento;
    }

    /**
     * @return the pickList
     */
    public DualListModel<Tbsubconjunto> getPickList() {
        return pickList;
    }

    /**
     * @param pickList the pickList to set
     */
    public void setPickList(DualListModel<Tbsubconjunto> pickList) {
        this.pickList = pickList;
    }
}

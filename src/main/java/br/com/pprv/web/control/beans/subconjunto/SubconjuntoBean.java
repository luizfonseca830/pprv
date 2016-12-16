/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.subconjunto;

import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.web.control.logic.subconjunto.SubconjuntoLogic;
import br.com.pprv.web.faces.constants.PagesUrl;
import br.com.pprv.web.faces.constants.Resources;
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
@ManagedBean(name = "subconjuntoBean")
@ViewScoped
public class SubconjuntoBean implements Serializable {

    @EJB
    SubconjuntoLogic subconjuntoLogic;

    private String nmSubconjunto;
    private Tbsubconjunto tbsubconjunto;
    private Tbsubconjunto tbSubconjuntoSelectd;
    private List<Tbsubconjunto> listTbsubconjunto;

    @PostConstruct
    public void init() {
        tbsubconjunto = new Tbsubconjunto();
        nmSubconjunto = "";
        listTbsubconjunto = subconjuntoLogic.findallTbsubconjunto();

        int idSubconjunto = AbstractFacesContextUtils.getParamInt("idSubconjunto");
        System.out.println("result idSelected: " + idSubconjunto);
        if (idSubconjunto > 0) {            
            tbsubconjunto = subconjuntoLogic.findTbsubconjuntoById(idSubconjunto);
            System.out.println("result tbsubconjunto: " + tbsubconjunto);
        } 
    }

    /**
     * metodo para fazer pesquisa por nome
     */
    public void pesquisarSubconjuntoPorNome() {
        if (nmSubconjunto != null && !nmSubconjunto.trim().isEmpty()) {
            listTbsubconjunto = subconjuntoLogic.findallTbsubconjuntoByName(nmSubconjunto);
        } else {
            listTbsubconjunto = subconjuntoLogic.findallTbsubconjunto();
        }

    }

    /**
     * metodo para deletar o subconjunto
     *
     * @param tbsubconjunto
     */
    public void deletarSubconjunto() {
        try {
            if (subconjuntoLogic.deleteSubconjunto(tbSubconjuntoSelectd)) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SUBCONJUNTO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("subconjuntodeletadocomsucesso"));
                listTbsubconjunto = subconjuntoLogic.findallTbsubconjunto();

            }
        } catch (Exception e) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SUBCONJUNTO_LIST);
            AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("erroaoremoversubconjunto"));
        }
    }

    /**
     * metodo para criar o subconjunto
     */
    public void createSubconjunto() {
        if (subconjuntoLogic.validarCamposSubconjunto(getTbsubconjunto())) {
            if (subconjuntoLogic.createSubconjunto(getTbsubconjunto())) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SUBCONJUNTO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("subconjuntoadicionadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageError(Resources.getMessage("erroaorealizarcadastrodosubconjunto"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * metodo para editar o subconjunto
     */
    public void editSubconjunto() {
        if (subconjuntoLogic.validarCamposSubconjunto(getTbsubconjunto())) {
            if (subconjuntoLogic.editSubconjunto(getTbsubconjunto())) {
                AbstractFacesContextUtils.redirectPage(PagesUrl.URL_SUBCONJUNTO_LIST);
                AbstractFacesContextUtils.addMessageInfo(Resources.getMessage("subconjuntoeditadocomsucesso"));
            } else {
                AbstractFacesContextUtils.addMessageError(Resources.getMessage("erroaoeditarsubconjunto"));
            }
        } else {
            AbstractFacesContextUtils.addMessageWarn(Resources.getMessage("porfavorverifiqueseasinformacoesestaopreenchidascorretamente"));
        }
    }

    /**
     * @return the nmSubconjunto
     */
    public String getNmSubconjunto() {
        return nmSubconjunto;
    }

    /**
     * @param nmSubconjunto the nmSubconjunto to set
     */
    public void setNmSubconjunto(String nmSubconjunto) {
        this.nmSubconjunto = nmSubconjunto;
    }

    /**
     * @return the tbsubconjunto
     */
    public Tbsubconjunto getTbsubconjunto() {
        return tbsubconjunto;
    }

    /**
     * @param tbsubconjunto the tbsubconjunto to set
     */
    public void setTbsubconjunto(Tbsubconjunto tbsubconjunto) {
        this.tbsubconjunto = tbsubconjunto;
    }

    /**
     * @return the tbSubconjuntoSelectd
     */
    public Tbsubconjunto getTbSubconjuntoSelectd() {
        return tbSubconjuntoSelectd;
    }

    /**
     * @param tbSubconjuntoSelectd the tbSubconjuntoSelectd to set
     */
    public void setTbSubconjuntoSelectd(Tbsubconjunto tbSubconjuntoSelectd) {
        this.tbSubconjuntoSelectd = tbSubconjuntoSelectd;
    }

    /**
     * @return the listTbsubconjunto
     */
    public List<Tbsubconjunto> getListTbsubconjunto() {
        return listTbsubconjunto;
    }

    /**
     * @param listTbsubconjunto the listTbsubconjunto to set
     */
    public void setListTbsubconjunto(List<Tbsubconjunto> listTbsubconjunto) {
        this.listTbsubconjunto = listTbsubconjunto;
    }
}

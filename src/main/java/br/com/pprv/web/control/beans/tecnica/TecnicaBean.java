/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.tecnica;

import br.com.pprv.model.entities.Tbtecnica;
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
    private TecnicaLogic tecnicaLogic;
    private Tbtecnica tbtecnica;
    private List<Tbtecnica> listTbtecnica;
    private int idtecnica;

    @PostConstruct
    public void init() {
        int idtecnica = AbstractFacesContextUtils.getParamInt("idtecnica");
        if (idtecnica > 0) {
            tbtecnica = tecnicaLogic.find(idtecnica);
        }
        listTbtecnica = tecnicaLogic.listallTecnica();
    }

    public List<Tbtecnica> autoComplete(String query) {
        return tecnicaLogic.getListTbtecnicaByNome(query);
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
}

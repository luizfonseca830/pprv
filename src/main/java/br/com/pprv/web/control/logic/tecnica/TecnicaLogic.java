/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.tecnica;

import br.com.pprv.model.daos.TbtecnicaFacade;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TecnicaLogic extends AbstractModuleCore {

    @EJB
    private TbtecnicaFacade tbtecnicaFacade;

    public List<Tbtecnica> getListTbtecnica() {
        return tbtecnicaFacade.findAll(super.getEM());
    }

    public List<Tbtecnica> getListTbtecnicaByNome(String nome) {
        return tbtecnicaFacade.listTbtecnicaByNome(nome, super.getEM());
    }

    public Tbtecnica findTbtecnicaByNome(String nome) {
        return tbtecnicaFacade.FindTbtecnicaByNome(nome, super.getEM());
    }

    public boolean create(Tbtecnica tbtecnica) {
        return tbtecnicaFacade.create(tbtecnica, super.getEM());
    }

    public boolean edit(Tbtecnica tbtecnica) {
        return tbtecnicaFacade.edit(tbtecnica, super.getEM());
    }

    public Tbtecnica find(int id) {
        return tbtecnicaFacade.find(id, super.getEM());
    }

    public boolean remove(Tbtecnica tbtecnica) {
        return tbtecnicaFacade.remove(tbtecnica, super.getEM());
    }

    public List<Tbtecnica> getListTbperfilByNmtecnica(String nmtecnica) {
        return tbtecnicaFacade.listTbtecnicaBynmtecnica(nmtecnica, super.getEM());
    }

    public List<Tbtecnica> listallTecnica() {
        return tbtecnicaFacade.allTecnica(getEM());
    }
}

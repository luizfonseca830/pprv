/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.equipamento;

import br.com.pprv.model.daos.TbequipamentoFacade;
import br.com.pprv.model.entities.Tbequipamento;
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
public class EquipamentoLogic extends AbstractModuleCore {

    @EJB
    TbequipamentoFacade tbequipamentoFacade;

    public Tbequipamento find(int id) {
        return tbequipamentoFacade.find(id, super.getEM());
    }

    public List<Tbequipamento> findTbequipamentoByTbtecnica(final Tbtecnica tbtecnica) {
        return tbequipamentoFacade.findTbequipamentoByTbtecnica(tbtecnica, super.getEM());
    }
}

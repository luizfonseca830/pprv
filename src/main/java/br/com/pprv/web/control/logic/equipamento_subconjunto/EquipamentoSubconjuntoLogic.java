/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.equipamento_subconjunto;

import br.com.pprv.model.daos.TbequipamentoSubconjuntoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class EquipamentoSubconjuntoLogic extends AbstractModuleCore {

    @EJB
    private TbequipamentoSubconjuntoFacade tbequipamentoSubconjuntoFacade;

    public List<TbequipamentoSubconjunto> listAllTbequipamentoSubconjuntoByIdEquipamento(Tbequipamento tbequipamento) {
        return tbequipamentoSubconjuntoFacade.listAllTbequipamentoSubconjuntoByIdEquipamento(tbequipamento, super.getEM());
    }

    public TbequipamentoSubconjunto find(int id) {
        return tbequipamentoSubconjuntoFacade.find(id, super.getEM());
    }

}

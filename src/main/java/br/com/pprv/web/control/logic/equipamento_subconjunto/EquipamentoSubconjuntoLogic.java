/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.equipamento_subconjunto;

import br.com.pprv.model.daos.TbequipamentosubconjuntoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbequipamentosubconjunto;
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
    private TbequipamentosubconjuntoFacade tbequipamentosubconjuntoFacade;   
    
    public List<Tbequipamentosubconjunto> getListAllTbequipamentoSubconjuntoByIdEquipamento(Tbequipamento tbequipamento) {
        return tbequipamentosubconjuntoFacade.getListAllTbequipamentoSubconjuntoByIdEquipamento(tbequipamento, super.getEM());
    }

    public Tbequipamentosubconjunto find(int id) {
        return tbequipamentosubconjuntoFacade.find(id, super.getEM());
    }

}

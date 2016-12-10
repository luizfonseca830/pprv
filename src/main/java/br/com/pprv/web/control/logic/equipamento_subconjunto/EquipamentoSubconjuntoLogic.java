/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.equipamento_subconjunto;

import br.com.pprv.model.daos.TbequipamentoSubconjuntoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbsubconjunto;
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

    public List<TbequipamentoSubconjunto> getListAllTbequipamentoSubconjuntoByIdEquipamento(Tbequipamento tbequipamento) {
        return tbequipamentoSubconjuntoFacade.getListAllTbequipamentoSubconjuntoByIdEquipamento(tbequipamento, super.getEM());
    }

    public TbequipamentoSubconjunto find(int id) {
        return tbequipamentoSubconjuntoFacade.find(id, super.getEM());
    }

    public List<Tbsubconjunto> getListAllSubconjuntoByIdEquipamento(Tbequipamento tbequipamento) {
        return tbequipamentoSubconjuntoFacade.getListAllSubconjuntoByIdEquipamento(tbequipamento, super.getEM());
    }

    public List<Tbsubconjunto> getListAllSubconjunto() {
        return tbequipamentoSubconjuntoFacade.getListAllSubconjunto(super.getEM());
    }

    public List<Tbsubconjunto> getListAllSubconjuntoNotFromEquipamento(final Tbequipamento tbequipamento) {
        return tbequipamentoSubconjuntoFacade.getListAllSubconjuntoNotFromEquipamento(tbequipamento, super.getEM());
    }

    public Integer deleteAllSubConjuntoByIdEquipamento(final Tbequipamento equipamento) {
        return tbequipamentoSubconjuntoFacade.deleteAllSubConjuntoByIdEquipamento(equipamento, super.getEM());
    }

    public boolean createEquipamentoSubConjunto(final TbequipamentoSubconjunto tbequipamentoSubconjunto) {
        return tbequipamentoSubconjuntoFacade.create(tbequipamentoSubconjunto, super.getEM());
    }
}

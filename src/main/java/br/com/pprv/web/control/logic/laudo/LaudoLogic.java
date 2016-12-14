/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.laudo;

import br.com.pprv.model.daos.TblaudoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tblaudo;
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
public class LaudoLogic extends AbstractModuleCore {

    @EJB
    private TblaudoFacade tblaudoFacade;

    public boolean createTblaudo(final Tblaudo tblaudo) {
        return tblaudoFacade.create(tblaudo, super.getEM());
    }

    public boolean editTblaudo(final Tblaudo tblaudo) {
        return tblaudoFacade.edit(tblaudo, super.getEM());
    }

    public Tblaudo findTblaudoByEquipamentoAndSubConjunto(final Tbequipamento tbequipamento, final Tbsubconjunto tbsubconjunto) {
        return tblaudoFacade.findTblaudoByEquipamentoAndSubConjunto(tbequipamento, tbsubconjunto, super.getEM());
    }

    public List<Tblaudo> findAllTblaudo() {
        return tblaudoFacade.findAllTblaudo(super.getEM());
    }

    public List<Tblaudo> findAllTblaudoByCondition(final Integer condition) {
        return tblaudoFacade.findAllTblaudoByCondition(condition, super.getEM());
    }
}

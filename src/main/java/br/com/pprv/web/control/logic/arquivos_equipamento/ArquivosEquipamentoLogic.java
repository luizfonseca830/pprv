/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.arquivos_equipamento;

import br.com.pprv.model.daos.TbarquivosEquipamentoFacade;
import br.com.pprv.model.entities.TbarquivosEquipamento;
import br.com.pprv.web.control.module.AbstractModuleCore;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ArquivosEquipamentoLogic extends AbstractModuleCore {

    @EJB
    private TbarquivosEquipamentoFacade tbarquivosEquipamentoFacade;

    public boolean createTbarquivosEquipamento(final TbarquivosEquipamento tbarquivosEquipamento) {
        return tbarquivosEquipamentoFacade.create(tbarquivosEquipamento, super.getEM());
    }

    public boolean deleteTbarquivosEquipamento(final TbarquivosEquipamento tbarquivosEquipamento) {
        return tbarquivosEquipamentoFacade.removeTbarquivosEquipamento(tbarquivosEquipamento, super.getEM());
    }
}

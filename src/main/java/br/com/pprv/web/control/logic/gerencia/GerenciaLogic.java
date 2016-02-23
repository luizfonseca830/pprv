/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.gerencia;

import br.com.pprv.model.daos.TbgerenciaFacade;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class GerenciaLogic extends AbstractModuleCore {

    @EJB
    private TbgerenciaFacade tbgerenciaFacade;

    public List<Tbgerencia> findAllTbgerencia() {
        return tbgerenciaFacade.findAllTbgerencia(super.getEM());
    }
}

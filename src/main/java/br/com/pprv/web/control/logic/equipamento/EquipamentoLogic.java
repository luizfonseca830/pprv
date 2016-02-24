/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.equipamento;

import br.com.pprv.model.daos.TbequipamentoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
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

    /**
     * metodo utilizado para encontrar equipamentos por tecnica ou gerencia.
     *
     * @param tbtecnica
     * @param tbgerencia
     * @return List Tbequipamento
     */
    public List<Tbequipamento> findTbequipamentoByTbtecnicaAndTbgerencia(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {

        StringBuilder filtro = new StringBuilder();
        boolean isTbtecnicaNull = true;

        filtro.append("SELECT t FROM Tbequipamento t WHERE ");

        if (tbtecnica != null) {
            filtro.append(" t.idtecnica.idtecnica = ").append(tbtecnica.getIdtecnica());
            isTbtecnicaNull = false;
        }

        if (tbgerencia != null) {
            if (isTbtecnicaNull) {
                filtro.append(" t.idgerencia.idgerencia = ").append(tbgerencia.getIdgerencia());
            } else {
                filtro.append(" AND t.idgerencia.idgerencia = ").append(tbgerencia.getIdgerencia());
            }
        }

        filtro.append(" ORDER BY t.nmequipamenta ");

        return tbequipamentoFacade.findTbequipamentoByTbtecnicaAndTbgerencia(filtro.toString(), super.getEM());
    }

    public List<Tbequipamento> allEquipamentos() {
        return tbequipamentoFacade.allEquipamentos(super.getEM());
    }

    public boolean editEquipamento(final Tbequipamento tbequipamento) {
        return tbequipamentoFacade.edit(tbequipamento, super.getEM());
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbequipamentosubconjunto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbequipamentosubconjuntoFacade extends AbstractFacade<Tbequipamentosubconjunto> {

    public TbequipamentosubconjuntoFacade() {
        super(Tbequipamentosubconjunto.class);
    }

    public List<Tbequipamentosubconjunto> getListAllTbequipamentoSubconjuntoByIdEquipamento(Tbequipamento tbequipamento, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbequipamentosubconjunto t WHERE t.idequipamento =:idEquipamento", Tbequipamentosubconjunto.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();

    }

    public List<Tbequipamentosubconjunto> getListAll(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbequipamentosubconjunto t ", Tbequipamentosubconjunto.class).getResultList();
    }
}

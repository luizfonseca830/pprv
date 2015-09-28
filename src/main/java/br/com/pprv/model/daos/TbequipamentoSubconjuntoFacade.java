/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbequipamentoSubconjuntoFacade extends AbstractFacade<TbequipamentoSubconjunto> {

    public TbequipamentoSubconjuntoFacade() {
        super(TbequipamentoSubconjunto.class);
    }

    public List<TbequipamentoSubconjunto> listAllTbequipamentoSubconjuntoByIdEquipamento(Tbequipamento tbequipamento, EntityManager em) {
        return em.createQuery("SELECT t FROM TbequipamentoSubconjunto t WHERE t.idequipamento = :idEquipamento", TbequipamentoSubconjunto.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();

    }
   
}

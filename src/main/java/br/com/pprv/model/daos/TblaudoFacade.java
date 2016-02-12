/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbsubconjunto;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TblaudoFacade extends AbstractFacade<Tblaudo> {

    public TblaudoFacade() {
        super(Tblaudo.class);
    }

    public Tblaudo findTblaudoByEquipamentoAndSubConjunto(final Tbequipamento tbequipamento, final Tbsubconjunto tbsubconjunto,
            final EntityManager entityManager) {

        Tblaudo tblaudoResult = null;

        try {
            tblaudoResult = entityManager.createQuery("SELECT t FROM Tblaudo t WHERE t.idequipamento = :idEquipamento and t.idsubconjunto = :idSubconjunto ORDER BY t.tmdatalaudo DESC", Tblaudo.class)
                    .setParameter("idEquipamento", tbequipamento)
                    .setParameter("idSubconjunto", tbsubconjunto)
                    .setMaxResults(1)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("ERRO TBLAUDOFACADE : " + e.getMessage());
        }

        return tblaudoResult;
    }
}

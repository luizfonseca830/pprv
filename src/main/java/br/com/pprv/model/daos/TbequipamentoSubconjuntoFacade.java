/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbsubconjunto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbequipamentoSubconjuntoFacade extends AbstractFacade<TbequipamentoSubconjunto> {

    public TbequipamentoSubconjuntoFacade() {
        super(TbequipamentoSubconjunto.class);
    }

    /**
     * method used to list all tbequipamento_subconjunto by tbequipamento.
     *
     * @param tbequipamento
     * @param em
     * @return list of tbequipamentoSubconjunto
     */
    public List<TbequipamentoSubconjunto> getListAllTbequipamentoSubconjuntoByIdEquipamento(final Tbequipamento tbequipamento, EntityManager em) {
        return em.createQuery("SELECT t FROM TbequipamentoSubconjunto t WHERE t.idequipamento =:idEquipamento", TbequipamentoSubconjunto.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();

    }

    /**
     * method used to list all tbequipamento_subconjunto.
     *
     * @param em
     * @return list of all tbequipamento_subconjunto
     */
    public List<TbequipamentoSubconjunto> getListAll(EntityManager em) {
        return em.createQuery("SELECT t FROM TbequipamentoSubconjunto t ", TbequipamentoSubconjunto.class).getResultList();
    }

    /**
     * method used to list all tbequipamento_subconjunto by tbequipamento.
     *
     * @param tbequipamento
     * @param em
     * @return list of tbequipamentoSubconjunto
     */
    public List<Tbsubconjunto> getListAllSubconjuntoByIdEquipamento(final Tbequipamento tbequipamento, EntityManager em) {
        return em.createQuery("SELECT t.idsubconjunto FROM TbequipamentoSubconjunto t WHERE t.idequipamento =:idEquipamento", Tbsubconjunto.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();

    }

    public List<Tbsubconjunto> getListAllSubconjunto(final EntityManager em) {
        return em.createQuery("SELECT t FROM Tbsubconjunto t", Tbsubconjunto.class)
                .getResultList();

    }

    public List<Tbsubconjunto> getListAllSubconjuntoNotFromEquipamento(final Tbequipamento tbequipamento, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbsubconjunto t WHERE t.idsubconjunto NOT IN (SELECT s.idsubconjunto.idsubconjunto FROM TbequipamentoSubconjunto s WHERE s.idequipamento =:idEquipamento)", Tbsubconjunto.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();
    }

    public Integer deleteAllSubConjuntoByIdEquipamento(final Tbequipamento equipamento, final EntityManager em) {
        return em.createQuery("DELETE FROM TbequipamentoSubconjunto t WHERE t.idequipamento =:idEquipamento")
                .setParameter("idEquipamento", equipamento).executeUpdate();
    }
}

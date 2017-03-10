/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbtecnica;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbequipamentoFacade extends AbstractFacade<Tbequipamento> {

    public TbequipamentoFacade() {
        super(Tbequipamento.class);
    }

    public List<Tbequipamento> findTbequipamentoByTbtecnica(Tbtecnica tbtecnica, EntityManager em) {

        return em.createQuery("SELECT t FROM Tbequipamento t WHERE t.idtecnica = :idTecnica GROUP BY t.idequipamento ORDER BY t.nmequipamenta",
                Tbequipamento.class)
                .setParameter("idTecnica", tbtecnica)
                .getResultList();

    }

    public List<Tbequipamento> allEquipamentos(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbequipamento t").getResultList();

    }

    public List<Tbequipamento> findAllTbequipamentosComLaudos(final EntityManager entityManager) {

        StringBuilder sql = new StringBuilder();
        sql.append("select distinct tbequipamento.*")
                .append("from tblaudo inner join tbequipamento on (tblaudo.idequipamento = tbequipamento.idequipamento)");

        return entityManager.createNativeQuery(sql.toString(), Tbequipamento.class).getResultList();
    }

    public List<Tbequipamento> findAllTbequipamentosByCondicaoOrTecnicaOrGerencia(String filtro, final EntityManager entityManager) {
        return entityManager.createQuery(filtro, Tbequipamento.class)
                .getResultList();
    }

    /**
     * metodo utilizado para encontrar equipamentos por tecnica ou gerencia.
     *
     * @param filtro
     * @param em
     * @return List Tbequipamento
     */
    public List<Tbequipamento> findTbequipamentoByTbtecnicaAndTbgerencia(String filtro, final EntityManager em) {
        return em.createQuery(filtro, Tbequipamento.class)
                .getResultList();

    }

    public List<Tbequipamento> findAllTbequipamentoWithLaudoByTecnicaAndGerencia(String filtro, final EntityManager em) {
        StringBuilder sql = new StringBuilder();
        sql.append("select distinct tbequipamento.*")
                .append(" from tblaudo inner join tbequipamento on (tblaudo.idequipamento = tbequipamento.idequipamento)")
                .append(filtro);

        return em.createNativeQuery(sql.toString(), Tbequipamento.class).getResultList();
    }
}

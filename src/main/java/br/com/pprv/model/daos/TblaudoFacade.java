/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.web.faces.converter.ConvData;
import br.com.pprv.web.faces.converter.TimeControl;
import java.util.Date;
import java.util.List;
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

    public List<Object[]> getLaudosPorGerencia(final Date dateInit, final Date dateFinal, final EntityManager em) {
        StringBuilder builder = new StringBuilder();

        builder.append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, count (tbgerencia.idgerencia) as total")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.tmdatalaudo >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND ")
                    .append(" tblaudo.tmdatalaudo <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.tmdatalaudo <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" group by tbgerencia.idgerencia")
                .append(" order by total desc");

        return em.createNativeQuery(builder.toString()).getResultList();
    }

    /**
     * metodo utilizado para pegar todos os laudos do mes atual.
     *
     * @param em
     * @return
     */
    public List<Object[]> getLaudosPorGerenciaMesAtual(final EntityManager em) {
        StringBuilder builder = new StringBuilder();

        builder.append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, count (tbgerencia.idgerencia) as total")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ")
                .append(" WHERE ")
                .append(" tblaudo.tmdatalaudo >= '").append(ConvData.parseToStringIso(TimeControl.getFirstDateOfCurrentMonth())).append("'");

        builder.append(" AND ")
                .append(" tblaudo.tmdatalaudo <= '").append(ConvData.parseToStringIso(TimeControl.getLastDateOfCurrentMonth())).append("'");

        builder.append(" group by tbgerencia.idgerencia")
                .append(" order by total desc");

        return em.createNativeQuery(builder.toString()).getResultList();
    }

    public List<Object[]> getLaudosPorGerenciaByLimiteExecucao(final Date dateInit, final Date dateFinal, final EntityManager em) {
        StringBuilder builder = new StringBuilder();

        builder.append(" select idgerencia, nmgerencia, SUM(inTime) as inTimeTotal, SUM(late) as lateTotal ")
                .append(" from( ")
                .append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, count (tblaudo.idgerencia) as inTime, 0 as late ")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" group by tbgerencia.idgerencia ")
                .append(" union all ")
                .append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, 0 as inTime, count (tblaudo.idgerencia) as late ")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND ")
                    .append(" tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" group by tbgerencia.idgerencia ")
                .append(" order by late desc ")
                .append(" ) as quantidade ")
                .append(" group by idgerencia,nmgerencia ")
                .append(" order by 3,4 desc ");

        return em.createNativeQuery(builder.toString()).getResultList();
    }

    public List<Tblaudo> findAllTblaudo(final EntityManager em) {
        return em.createQuery("SELECT t FROM Tblaudo t", Tblaudo.class)
                .getResultList();
    }

    public List<Tblaudo> findAllTblaudoByCondition(final Integer condition, final EntityManager em) {
        return em.createQuery("SELECT t FROM Tblaudo t WHERE t.condicao =:condition", Tblaudo.class)
                .setParameter("condition", condition)
                .getResultList();
    }
}

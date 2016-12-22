/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.faces.constants.StatusConstants;
import br.com.pprv.web.faces.converter.ConvData;
import br.com.pprv.web.faces.converter.TimeControl;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
            builder.append(" WHERE tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" AND tblaudo.dtdataexecucao is null ")
                .append(" group by tbgerencia.idgerencia ")
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

    public List<Tblaudo> findAllTblaudoByConditionOrTbtecnicaOrTbgerencia(final Integer condition,
            final Tbtecnica tbtecnica, final Tbgerencia tbgerencia, final EntityManager em) {

        StringBuilder filtro = new StringBuilder();
        boolean conditionIsNull = true;
        boolean tbgerenciaIsNull = true;
        boolean tbtecnicaIsNull = true;

        filtro.append(" SELECT t FROM Tblaudo t ");

        if (condition != null
                && condition > 0) {
            filtro.append(" WHERE t.condicao =:condition ");
            conditionIsNull = false;
        }

        if (tbgerencia != null) {
            tbgerenciaIsNull = false;
            if (conditionIsNull) {
                filtro.append(" WHERE t.idgerencia =:idGerencia ");
            } else {
                filtro.append(" AND t.idgerencia =:idGerencia ");
            }
        }

        if (tbtecnica != null) {
            tbtecnicaIsNull = false;
            if (conditionIsNull && tbgerenciaIsNull) {
                filtro.append(" WHERE t.idequipamento.idtecnica=:idTecnica ");
            } else {
                filtro.append(" AND t.idequipamento.idtecnica=:idTecnica ");
            }
        }

        System.out.println("queryResult: " + filtro.toString());

        TypedQuery<Tblaudo> q = em.createQuery(filtro.toString(), Tblaudo.class);
        if (!conditionIsNull) {
            q.setParameter("condition", condition);
        }

        if (!tbgerenciaIsNull) {
            q.setParameter("idGerencia", tbgerencia);
        }

        if (!tbtecnicaIsNull) {
            q.setParameter("idTecnica", tbtecnica);
        }

        return q.getResultList();
    }

    public List<Tblaudo> findAllTblaudoByEquipamentoECondicaoNaoExecutado(final Tbequipamento tbequipamento, final EntityManager em) {
        return em.createQuery("SELECT t FROM Tblaudo t WHERE t.idequipamento=:idEquipamento AND t.condicao !=:condition ORDER BY t.condicao DESC", Tblaudo.class)
                .setParameter("idEquipamento", tbequipamento)
                .setParameter("condition", StatusConstants.STATUS_LAUDO_EXECUTADO)
                .getResultList();
    }

    public List<Tblaudo> findAllTblaudoByEquipamento(final Tbequipamento tbequipamento, final EntityManager em) {
        return em.createQuery("SELECT t FROM Tblaudo t WHERE t.idequipamento=:idEquipamento ORDER BY t.condicao DESC", Tblaudo.class)
                .setParameter("idEquipamento", tbequipamento)
                .getResultList();
    }

    public List<Object[]> getLaudosPorGerenciaECondicao(final Date dateInit, final Date dateFinal, final EntityManager em) {
        StringBuilder builder = new StringBuilder();

        builder.append(" select idgerencia, nmgerencia, SUM(total_alerta) as totalalerta, SUM(total_critico) as totalcritico ")
                .append(" from( ")
                .append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, count (tbgerencia.idgerencia) as total_alerta, 0 as total_critico ")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" AND tblaudo.condicao = ").append(StatusConstants.STATUS_LAUDO_ALERTA)
                .append(" group by tbgerencia.idgerencia ")
                .append(" union all ")
                .append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, 0 as total_alerta, count (tbgerencia.idgerencia) as total_critico ")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND ")
                    .append(" tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" AND tblaudo.condicao = ").append(StatusConstants.STATUS_LAUDO_CRITICO)
                .append(" group by tbgerencia.idgerencia ")
                .append(" ) as quantidade ")
                .append(" group by idgerencia,nmgerencia ")
                .append(" order by 3,4 desc ");

        System.out.println("getLaudosPorGerenciaECondicao: " + builder.toString());

        return em.createNativeQuery(builder.toString()).getResultList();
    }

    public List<Object[]> getLaudosPorGerenciaEmAtrasoECritico(final Date dateInit, final Date dateFinal, final EntityManager em) {
        StringBuilder builder = new StringBuilder();

        builder.append(" select distinct tbgerencia.idgerencia, tbgerencia.nmgerencia, count (tblaudo.idgerencia) as late_critic ")
                .append(" from tblaudo inner join tbgerencia on (tblaudo.idgerencia = tbgerencia.idgerencia) ");

        if (dateFinal != null) {
            builder.append(" WHERE tblaudo.limiteexecucao >= '").append(ConvData.parseToStringIso(dateInit)).append("'")
                    .append(" AND tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateFinal)).append("'");
        } else {
            builder.append(" WHERE tblaudo.limiteexecucao <= '").append(ConvData.parseToStringIso(dateInit)).append("'");
        }

        builder.append(" AND tblaudo.condicao = ").append(StatusConstants.STATUS_LAUDO_CRITICO)
                .append(" and tblaudo.dtdataexecucao is null ")
                .append(" group by tbgerencia.idgerencia ")
                .append(" order by late_critic desc ");

        System.out.println("getLaudosPorGerenciaEmAtrasoECritico: " + builder.toString());

        return em.createNativeQuery(builder.toString()).getResultList();
    }
}

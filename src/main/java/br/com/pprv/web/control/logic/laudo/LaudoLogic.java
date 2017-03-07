/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.laudo;

import br.com.pprv.model.daos.TblaudoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class LaudoLogic extends AbstractModuleCore {

    @EJB
    private TblaudoFacade tblaudoFacade;

    public boolean createTblaudo(final Tblaudo tblaudo) {
        return tblaudoFacade.create(tblaudo, super.getEM());
    }

    public boolean editTblaudo(final Tblaudo tblaudo) {
        return tblaudoFacade.edit(tblaudo, super.getEM());
    }

    public Tblaudo findTblaudoByEquipamentoAndSubConjunto(final Tbequipamento tbequipamento, final Tbsubconjunto tbsubconjunto) {
        return tblaudoFacade.findTblaudoByEquipamentoAndSubConjunto(tbequipamento, tbsubconjunto, super.getEM());
    }

    public List<Tblaudo> findAllTblaudo() {
        return tblaudoFacade.findAllTblaudo(super.getEM());
    }

    public List<Tblaudo> findAllTblaudoByCondition(final Integer condition) {
        return tblaudoFacade.findAllTblaudoByCondition(condition, super.getEM());
    }

    public List<Tblaudo> findAllTblaudoByConditionOrTbtecnicaOrTbgerencia(final Integer condition, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.findAllTblaudoByConditionOrTbtecnicaOrTbgerencia(condition, tbtecnica, tbgerencia, super.getEM());
    }

    public List<Tblaudo> findAllTblaudoByEquipamentoECondicaoNaoExecutado(final Tbequipamento tbequipamento) {
        return tblaudoFacade.findAllTblaudoByEquipamentoECondicaoNaoExecutado(tbequipamento, super.getEM());
    }

    public List<Tblaudo> findAllTblaudoByEquipamento(final Tbequipamento tbequipamento) {
        return tblaudoFacade.findAllTblaudoByEquipamento(tbequipamento, super.getEM());
    }

    public List<Tblaudo> getHistoricLaudo(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia,
            final Integer condition, final Date dtAnalysisBegin, final Date dtAnalysisEnd, final EntityManager em) {

        StringBuilder filtro = new StringBuilder();
        filtro.append("Select t from Tblaudo t ");
        boolean isTbtecnicaNull = true;
        boolean isTbgerenciaNull = true;
        boolean isConditionNull = true;

        if (tbtecnica != null) {
            filtro.append(" WHERE t.idequipamento.idtecnica = ").append(tbtecnica.getIdtecnica());
            isTbtecnicaNull = false;
        }

        if (tbgerencia != null) {
            isTbgerenciaNull = false;
            if (isTbtecnicaNull) {
                filtro.append(" WHERE t.idgerencia = ").append(tbgerencia.getIdgerencia());
            } else {
                filtro.append(" AND t.idgerencia = ").append(tbgerencia.getIdgerencia());
            }
        }

        if (condition != null && condition > 0 && condition <= 4) {
            isConditionNull = false;
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE t.condicao = ").append(condition);
            } else {
                filtro.append(" AND t.condicao = ").append(condition);
            }
        }

        if (condition != null && condition == 5) {
            isConditionNull = false;
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE t.condicao in (2,3) ");
            } else {
                filtro.append(" AND t.condicao in (2,3) ");
            }
        }

        if (dtAnalysisBegin != null && dtAnalysisEnd != null) {
            if (isTbtecnicaNull && isTbgerenciaNull && isConditionNull) {
                filtro.append(" WHERE t.dtdatalaudo between ").append("'").append(dtAnalysisBegin).append("'").append(" and '").append(dtAnalysisEnd).append("'");
            } else {
                filtro.append(" AND t.dtdatalaudo between ").append("'").append(dtAnalysisBegin).append("'").append(" and '").append(dtAnalysisEnd).append("'");
            }
        }

        System.out.println("filtroSQL " + filtro.toString());

        TypedQuery t = em.createQuery(filtro.toString(), Tblaudo.class);

        return t.getResultList();
    }
}

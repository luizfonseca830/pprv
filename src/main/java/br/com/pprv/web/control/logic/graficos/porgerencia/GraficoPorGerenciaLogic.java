/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.graficos.porgerencia;

import br.com.pprv.model.daos.TblaudoFacade;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author ioliveira
 */
@Stateless
public class GraficoPorGerenciaLogic extends AbstractModuleCore {

    @EJB
    private TblaudoFacade tblaudoFacade;

    public List<Object[]> getLaudosPorGerencia(final Date dateInit, final Date dateFinal, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.getLaudosPorGerencia(dateInit, dateFinal, tbtecnica, tbgerencia, super.getEM());
    }

    public List<Object[]> getLaudosPorGerenciaMesAtual(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.getLaudosPorGerenciaMesAtual(tbtecnica, tbgerencia, super.getEM());
    }

    public List<Object[]> getLaudosPorGerenciaByLimiteExecucao(final Date dateInit, final Date dateFinal, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.getLaudosPorGerenciaByLimiteExecucao(dateInit, dateFinal, tbtecnica, tbgerencia, super.getEM());
    }

    public List<Object[]> getLaudosPorGerenciaECondicao(final Date dateInit, final Date dateFinal, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.getLaudosPorGerenciaECondicao(dateInit, dateFinal, tbtecnica, tbgerencia, super.getEM());
    }

    public List<Object[]> getLaudosPorGerenciaEmAtrasoECritico(final Date dateInit, final Date dateFinal, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return tblaudoFacade.getLaudosPorGerenciaEmAtrasoECritico(dateInit, dateFinal, tbtecnica, tbgerencia, super.getEM());
    }
}

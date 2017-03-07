/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report.historicolaudo;

import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.laudo.LaudoLogic;
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
public class ReportHistoricoLaudo extends AbstractModuleCore {

    @EJB
    private LaudoLogic laudoLogic;

    public List<Tblaudo> getHistoricLaudo(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia,
            final Integer condition, final Date dtAnalysisBegin, final Date dtAnalysisEnd) {

        return laudoLogic.getHistoricLaudo(tbtecnica, tbgerencia, condition, dtAnalysisBegin, dtAnalysisEnd, super.getEM());
    }
}

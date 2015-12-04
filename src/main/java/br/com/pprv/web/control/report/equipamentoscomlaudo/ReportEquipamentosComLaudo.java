/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report.equipamentoscomlaudo;

import br.com.pprv.model.daos.TbequipamentoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.web.control.report.AbstractReportActions;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ReportEquipamentosComLaudo extends AbstractReportActions {

    @EJB
    private TbequipamentoFacade tbequipamentoFacade;

    public List<Tbequipamento> findAllTbequipamentosComLaudos() {
        return tbequipamentoFacade.findAllTbequipamentosComLaudos(super.getEM());
    }

    @Override
    protected Map<String, Object> getParams() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("SUBREPORT_DIR", getSubReportPath());

        return map;
    }

    @Override
    protected String getReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/relEquipamentosComLaudos/equipamentosLaudos.jasper");
    }

    @Override
    protected String getReportName() {
        return "Relatorio-equipamentos-com-laudo";
    }

    @Override
    public boolean preparaParam() {
        return true;
    }

    public String getSubReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images") + "/";
    }
}

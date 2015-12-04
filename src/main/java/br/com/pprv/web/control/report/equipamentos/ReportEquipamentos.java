/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report.equipamentos;

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
public class ReportEquipamentos extends AbstractReportActions {

    @EJB
    private TbequipamentoFacade tbequipamentoFacade;

    private Integer condicaoEquipamento;

    public List<Tbequipamento> findAllTbequipamentosByCondicao(final Integer condicao) {
        return tbequipamentoFacade.findAllTbequipamentosByCondicao(condicao, super.getEM());
    }

    @Override

    protected Map<String, Object> getParams() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("SUBREPORT_DIR", getSubReportPath());
        map.put("CONDICAO", condicaoEquipamento);

        return map;
    }

    @Override
    protected String getReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/relEquipamentos/equipamentos.jasper");
    }

    @Override
    protected String getReportName() {
        return "Relatorio-de-equipamentos";
    }

    @Override
    public boolean preparaParam() {
        boolean result = false;

        if (condicaoEquipamento != null) {
            result = true;
        }

        return result;
    }

    public String getSubReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images") + "/";
    }

    /**
     * @return the condicaoEquipamento
     */
    public Integer getCondicaoEquipamento() {
        return condicaoEquipamento;
    }

    /**
     * @param condicaoEquipamento the condicaoEquipamento to set
     */
    public void setCondicaoEquipamento(Integer condicaoEquipamento) {
        this.condicaoEquipamento = condicaoEquipamento;
    }

}

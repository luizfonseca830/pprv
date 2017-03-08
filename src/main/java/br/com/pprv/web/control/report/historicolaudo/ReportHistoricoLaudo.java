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
import br.com.pprv.web.control.report.AbstractReportActions;
import br.com.pprv.web.faces.converter.ConvData;
import java.util.Date;
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
public class ReportHistoricoLaudo extends AbstractReportActions {

    @EJB
    private LaudoLogic laudoLogic;

    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;
    private Integer condicaoEquipamento;
    private Date dtAnalysisBegin;
    private Date dtAnalysisEnd;
    private StringBuilder filtro;
    private StringBuilder params;
    private StringBuilder paramsDate;

    public List<Tblaudo> getHistoricLaudo(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia,
            final Integer condition, final Date dtAnalysisBegin, final Date dtAnalysisEnd) {

        return laudoLogic.getHistoricLaudo(tbtecnica, tbgerencia, condition, dtAnalysisBegin, dtAnalysisEnd, super.getEM());
    }

    @Override
    protected Map<String, Object> getParams() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("SUBREPORT_DIR", getSubReportPath());
        map.put("FILTRO", filtro.toString());
        map.put("PARAMS", params.toString());
        map.put("PARAM_DATE", paramsDate.toString());

        return map;
    }

    @Override
    protected String getReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reports/relHistoricoLaudo/historicoLaudo.jasper");
    }

    @Override
    protected String getReportName() {
        return "Historico-de-Laudos";
    }

    @Override
    public boolean preparaParam() {

        filtro = new StringBuilder();
        params = new StringBuilder();
        paramsDate = new StringBuilder();

        boolean isTbtecnicaNull = true;
        boolean isTbgerenciaNull = true;
        boolean isConditionNull = true;

        if (tbtecnica != null) {
            filtro.append(" WHERE tbequipamento.idtecnica = ").append(tbtecnica.getIdtecnica());
            params.append("Técnica: ").append(tbtecnica.getNmtecnica()).append("            ");
            isTbtecnicaNull = false;
        }

        if (tbgerencia != null) {
            isTbgerenciaNull = false;
            if (isTbtecnicaNull) {
                filtro.append(" WHERE tblaudo.idgerencia = ").append(tbgerencia.getIdgerencia());
            } else {
                filtro.append(" AND tblaudo.idgerencia = ").append(tbgerencia.getIdgerencia());
            }
            params.append("Gerência: ").append(tbgerencia.getNmgerencia());
        }

        if (condicaoEquipamento != null && condicaoEquipamento > 0 && condicaoEquipamento <= 4) {
            isConditionNull = false;
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE tblaudo.condicao = ").append(condicaoEquipamento);
            } else {
                filtro.append(" AND tblaudo.condicao = ").append(condicaoEquipamento);
            }
        }

        if (condicaoEquipamento != null && condicaoEquipamento == 5) {
            isConditionNull = false;
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE tblaudo.condicao in (2,3) ");
            } else {
                filtro.append(" AND tblaudo.condicao in (2,3) ");
            }
        }

        if (dtAnalysisBegin != null && dtAnalysisEnd != null) {
            if (isTbtecnicaNull && isTbgerenciaNull && isConditionNull) {
                filtro.append(" WHERE tblaudo.dtdatalaudo between ").append("'").append(dtAnalysisBegin).append("'").append(" and '").append(dtAnalysisEnd).append("'");
            } else {
                filtro.append(" AND tblaudo.dtdatalaudo between ").append("'").append(dtAnalysisBegin).append("'").append(" and '").append(dtAnalysisEnd).append("'");
            }
            paramsDate.append("Data análise:  ").append(ConvData.formatDtBr(dtAnalysisBegin)).append("  a  ").append(ConvData.formatDtBr(dtAnalysisEnd));
        }

        return true;
    }

    public String getSubReportPath() {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath("/resources/images") + "/";
    }

    /**
     * @return the tbtecnica
     */
    public Tbtecnica getTbtecnica() {
        return tbtecnica;
    }

    /**
     * @param tbtecnica the tbtecnica to set
     */
    public void setTbtecnica(Tbtecnica tbtecnica) {
        this.tbtecnica = tbtecnica;
    }

    /**
     * @return the tbgerencia
     */
    public Tbgerencia getTbgerencia() {
        return tbgerencia;
    }

    /**
     * @param tbgerencia the tbgerencia to set
     */
    public void setTbgerencia(Tbgerencia tbgerencia) {
        this.tbgerencia = tbgerencia;
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

    /**
     * @return the dtAnalysisBegin
     */
    public Date getDtAnalysisBegin() {
        return dtAnalysisBegin;
    }

    /**
     * @param dtAnalysisBegin the dtAnalysisBegin to set
     */
    public void setDtAnalysisBegin(Date dtAnalysisBegin) {
        this.dtAnalysisBegin = dtAnalysisBegin;
    }

    /**
     * @return the dtAnalysisEnd
     */
    public Date getDtAnalysisEnd() {
        return dtAnalysisEnd;
    }

    /**
     * @param dtAnalysisEnd the dtAnalysisEnd to set
     */
    public void setDtAnalysisEnd(Date dtAnalysisEnd) {
        this.dtAnalysisEnd = dtAnalysisEnd;
    }
}

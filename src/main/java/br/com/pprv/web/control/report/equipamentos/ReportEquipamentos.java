/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report.equipamentos;

import br.com.pprv.model.daos.TbequipamentoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbtecnica;
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
    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;
    private StringBuilder filtro;
    private StringBuilder params;

    public List<Tbequipamento> findAllTbequipamentosByCondicaoOrTecnicaOrGerencia(final Integer condicao, final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {

        StringBuilder filtro = new StringBuilder();
        boolean isTbtecnicaNull = true;
        boolean isTbgerenciaNull = true;

        filtro.append("SELECT t FROM Tbequipamento t ");

        if (tbtecnica != null) {
            filtro.append(" WHERE t.idtecnica = ").append(tbtecnica.getIdtecnica());
            isTbtecnicaNull = false;
        }

        if (tbgerencia != null) {
            if (isTbtecnicaNull) {
                filtro.append(" WHERE t.idgerencia = ").append(tbgerencia.getIdgerencia());
            } else {
                filtro.append(" AND t.idgerencia = ").append(tbgerencia.getIdgerencia());
            }
        }

        if (condicao != null && condicao > 0 && condicao <= 4) {
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE t.condicao = ").append(condicao);
            } else {
                filtro.append(" AND t.condicao = ").append(condicao);
            }
        }

        if (condicao != null && condicao == 5) {
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE t.condicao in (2,3) ");
            } else {
                filtro.append(" AND t.condicao in (2,3) ");
            }
        }

        filtro.append(" ORDER BY t.nmequipamenta ");
        System.out.println("filtro: " + filtro.toString());

        return tbequipamentoFacade.findAllTbequipamentosByCondicaoOrTecnicaOrGerencia(filtro.toString(), super.getEM());
    }

    @Override

    protected Map<String, Object> getParams() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("SUBREPORT_DIR", getSubReportPath());
        map.put("FILTRO", filtro.toString());
        map.put("PARAMS", params.toString());

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

        boolean isTbtecnicaNull = true;
        boolean isTbgerenciaNull = true;

        params = new StringBuilder();
        filtro = new StringBuilder();

        if (tbtecnica != null) {
            filtro.append(" WHERE tbequipamento.idtecnica = ").append(tbtecnica.getIdtecnica());
            params.append("Técnica: ").append(tbtecnica.getNmtecnica()).append("            ");
            isTbtecnicaNull = false;
        }

        if (tbgerencia != null) {
            isTbgerenciaNull = false;
            if (isTbtecnicaNull) {
                filtro.append(" WHERE tbequipamento.idgerencia = ").append(tbgerencia.getIdgerencia());
            } else {
                filtro.append(" AND tbequipamento.idgerencia = ").append(tbgerencia.getIdgerencia());
            }
            params.append("Gerência: ").append(tbgerencia.getNmgerencia()).append("            ");
        }

        if (condicaoEquipamento != null && condicaoEquipamento > 0 && condicaoEquipamento <= 4) {
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE tbequipamento.condicao = ").append(condicaoEquipamento);
            } else {
                filtro.append(" AND tbequipamento.condicao = ").append(condicaoEquipamento);
            }
        }

        if (condicaoEquipamento != null && condicaoEquipamento == 5) {
            if (isTbtecnicaNull && isTbgerenciaNull) {
                filtro.append(" WHERE tbequipamento.condicao in (2,3) ");
            } else {
                filtro.append(" AND tbequipamento.condicao in (2,3) ");
            }
        }

        return true;
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

}

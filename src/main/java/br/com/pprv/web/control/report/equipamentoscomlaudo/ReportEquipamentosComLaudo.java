/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report.equipamentoscomlaudo;

import br.com.pprv.model.daos.TbequipamentoFacade;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.control.logic.laudo.LaudoLogic;
import br.com.pprv.web.control.report.AbstractReportActions;
import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author ioliveira
 */
@Stateless
public class ReportEquipamentosComLaudo extends AbstractReportActions {

    @EJB
    private EquipamentoLogic equipamentoLogic;
    @EJB
    private LaudoLogic laudoLogic;
    @EJB
    private TbequipamentoFacade tbequipamentoFacade;

    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;
    private StringBuilder filtro;
    private Object tes;

    @Override
    protected Map<String, Object> getParams() {
        HashMap<String, Object> map = new HashMap<>();

        map.put("FILTRO", filtro.toString());
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

        boolean isTbgerenciaNull = true;

        filtro = new StringBuilder();

        if (tbtecnica == null && tbgerencia == null) {
            filtro.append("");
        } else {
            if (tbtecnica != null) {
                filtro.append(" WHERE tbtecnica.idtecnica = ").append(tbtecnica.getIdtecnica());
                isTbgerenciaNull = false;
            }

            if (tbgerencia != null) {
                if (isTbgerenciaNull) {
                    filtro.append(" WHERE tbequipamento.idgerencia = ").append(tbgerencia.getIdgerencia());
                } else {
                    filtro.append(" AND tbequipamento.idgerencia = ").append(tbgerencia.getIdgerencia());
                }
            }
        }

        return true;
    }

    /**
     * metodo utilizado para encontrar equipamentos por tecnica ou gerencia.
     *
     * @param tbtecnica
     * @param tbgerencia
     * @return List Tbequipamento
     */
    public List<Tbequipamento> findAllTbequipamentoWithLaudoByTecnicaAndGerencia(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return equipamentoLogic.findAllTbequipamentoWithLaudoByTecnicaAndGerencia(tbtecnica, tbgerencia);
    }

    public List<Tbequipamento> findAllTbequipamentosComLaudos() {
        return tbequipamentoFacade.findAllTbequipamentosComLaudos(super.getEM());
    }

    public List<Tblaudo> findAllTbequipamentosComLaudo() {
        return laudoLogic.findAllTbequipamentosComLaudos();
    }

    public List<Tblaudo> findTbequipamentoWithLaudoByTecnicaAndGerencia(final Tbtecnica tbtecnica, final Tbgerencia tbgerencia) {
        return laudoLogic.findAllTbequipamentoWithLaudoByTecnicaAndGerencia(tbtecnica, tbgerencia);
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
}

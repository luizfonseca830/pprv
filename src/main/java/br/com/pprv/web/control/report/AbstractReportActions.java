/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.report;

import br.com.pprv.web.control.module.AbstractModuleCore;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRPrintPage;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

/**
 *
 * @author Marco Santarelle
 */
public abstract class AbstractReportActions extends AbstractModuleCore {

    public List<JasperPrint> listRelatorios = new ArrayList<>();
    public JasperPrint valoresImpressao = null;
    public Connection connection = null;

    public void createPdfReport() {

        try {

            connection = getConnection();

            // obtem o contexto da aplicação
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            // informa qual o tipo do documento para o response
            response.setContentType("application/pdf");

            // adiciona informações ao header como o nome do arquivo
            response.addHeader("Content-disposition", "filename=" + getReportName() + ".pdf");

            // Preenche o relatório com os parametros e e a conexão
            JasperPrint jasperPrint = JasperFillManager.fillReport(getReportPath(), getParams(), connection);

            // Exporta o relatório para pdf
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            // Salva o estado da aplicação no contexto do JSF
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            context.responseComplete();
        } catch (SQLException | JRException | IOException e) {
            AbstractFacesContextUtils.addMessageError(e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
    }

    public void createPdfReportByJRDataSource(JRDataSource DS) {

        try {
            // obtem o contexto da aplicação
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            // informa qual o tipo do documento para o response
            response.setContentType("application/pdf");

            // adiciona informações ao header como o nome do arquivo
            response.addHeader("Content-disposition", "filename=" + getReportName() + ".pdf");

            // Preenche o relatório com os parametros e e a conexão
            JasperPrint jasperPrint = JasperFillManager.fillReport(getReportPath(), getParams(), DS);

            // Exporta o relatório para pdf
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

            // Salva o estado da aplicação no contexto do JSF
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            context.responseComplete();
        } catch (JRException | IOException e) {
            AbstractFacesContextUtils.addMessageError(e.getMessage());
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
    }

    public void createPdfReportEmLote() {

        int count = 0;

        for (JasperPrint jasper : listRelatorios) {

            if (count == 0) {
                valoresImpressao = jasper;
                count++;
            } else {
                for (JRPrintPage jRPrintPage : jasper.getPages()) {
                    valoresImpressao.addPage(jRPrintPage);
                }
            }
        }

        try {
            // obtem o contexto da aplicação
            FacesContext context = FacesContext.getCurrentInstance();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();

            // informa qual o tipo do documento para o response
            response.setContentType("application/pdf");

            // adiciona informações ao header como o nome do arquivo
            response.addHeader("Content-disposition", "filename=" + getReportName() + ".pdf");

            // Exporta o relatório para pdf
            JasperExportManager.exportReportToPdfStream(valoresImpressao, response.getOutputStream());

            // Salva o estado da aplicação no contexto do JSF
            context.getApplication().getStateManager().saveView(context);

            // Fecha o stream do response
            context.responseComplete();
        } catch (JRException e) {
            AbstractFacesContextUtils.addMessageError(e.getMessage());
            e.printStackTrace(System.err);
        } catch (IOException ex) {
            Logger.getLogger(AbstractReportActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected JasperPrint makeListJasperPrint() {

        JasperPrint jasperPrint = null;

        try {

            connection = getConnection();

            jasperPrint = JasperFillManager.fillReport(getReportPath(), getParams(), connection);

        } catch (SQLException e) {
            System.out.println("SQLException ReportRomaneioEmLote.java -> makeListJasperPrint()");
            e.printStackTrace(System.err);
        } catch (JRException e) {
            jasperPrint = null;
        } finally {
            closeConnection();
        }

        return jasperPrint;
    }

    private void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException ex) {
            System.out.println("SQLException AbstractReportActionsErro ao fechar a conexão dos reports::::");
            Logger.getLogger(AbstractReportActions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected abstract Map<String, Object> getParams();

    protected abstract String getReportPath();

    protected abstract String getReportName();

    public abstract boolean preparaParam();
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.tecnica;

import br.com.pprv.model.entities.TbarquivosEquipamento;
import br.com.pprv.model.entities.TbarquivosEquipamentoPK;
import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.model.entities.Tbusuario;
import br.com.pprv.model.entities.custom.EquipamentoModel;
import br.com.pprv.model.entities.custom.LaudoMdl;
import br.com.pprv.web.control.logic.arquivos_equipamento.ArquivosEquipamentoLogic;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.control.logic.equipamento_subconjunto.EquipamentoSubconjuntoLogic;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.laudo.LaudoLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.faces.constants.StatusConstants;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import br.com.pprv.web.faces.utils.Shareds;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.text.Normalizer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author JorgeFonseca
 */
@ManagedBean(name = "tecnicaBean")
@ViewScoped
public class TecnicaBean implements Serializable {

    private static final boolean FLAG = true;
    private static final String CAMINHO_LINUX = "/upload/arquivos/";
    private static final String CAMINHO_WINDOWS = "C:\\upload\\arquivos";
    private static final String CAMINHO = FLAG ? CAMINHO_LINUX : CAMINHO_WINDOWS;

    @EJB
    private EquipamentoLogic equipamentoLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private EquipamentoSubconjuntoLogic equimentoSubconjuntoLogic;
    @EJB
    private LaudoLogic laudoLogic;
    @EJB
    private ArquivosEquipamentoLogic arquivosEquipamentoLogic;
    @EJB
    private GerenciaLogic gerenciaLogic;

    private Tbtecnica tbtecnica;
    private TbequipamentoSubconjunto tbequipamentoSubconjunto;
    private Tbequipamento tbequipamento;
    private List<TbequipamentoSubconjunto> listTbequipamentoSubconjuntos;
    private List<Tbtecnica> listTbtecnica;
    private int idtecnica;
    private int idequipamento;
    private int idequipamentoSubconjunto;
    private List<Tbequipamento> listTbequipamento;
    private List<Tbequipamento> filterequipamento;
    private List<EquipamentoModel> listEquipamentos;
    private Tbequipamento tbequipamentoSelected;
    private List<LaudoMdl> listLaudoMdl;
    private TbarquivosEquipamento tbarquivosEquipamentoSelected;
    private Tbgerencia tbgerencia;
    private List<Tbgerencia> listTbgerencias;

    private UploadedFile uploadedFile;

    @PostConstruct
    public void init() {
        listTbtecnica = tecnicaLogic.listallTecnica();
        listEquipamentos = new ArrayList<>();
        listTbgerencias = gerenciaLogic.findAllTbgerencia();

        idtecnica = AbstractFacesContextUtils.getParamInt("idtecnica");
        idequipamento = AbstractFacesContextUtils.getParamInt("idequipamento");
        idequipamentoSubconjunto = AbstractFacesContextUtils.getParamInt("idequipamentosubconjunto");

        if (idtecnica > 0) {
            tbtecnica = tecnicaLogic.find(idtecnica);
        }

        if (idequipamento > 0) {
            tbequipamento = equipamentoLogic.find(idequipamento);
        }
        if (idequipamentoSubconjunto > 0) {
            tbequipamentoSubconjunto = equimentoSubconjuntoLogic.find(idequipamentoSubconjunto);
        }

    }

    public void search() {

        if (tbtecnica == null && tbgerencia == null) {
            listTbequipamento = equipamentoLogic.allEquipamentos();
        } else {
            listTbequipamento = equipamentoLogic.findTbequipamentoByTbtecnicaAndTbgerencia(tbtecnica, tbgerencia);
            if (listTbequipamento == null || listTbequipamento.isEmpty()) {
                listTbequipamento = null;
                AbstractFacesContextUtils.addMessageWarn("Nenhum resultado encontrado.");
            }
        }
    }

    /**
     * metodo utilizado para criar os laudos.
     */
    public void createLaudos() {

        boolean result = false;
        final Tbusuario user = Shareds.getUser();

        if (user == null
                || user.getIdperfil() == null
                || user.getIdperfil().getNivelacessoperfil() != 1
                || validaCondicaoExecutado()) {
            AbstractFacesContextUtils.addMessageWarn("É necessário ter o perfil de Operador nível 1 para este tipo de operação.");
        } else {
            for (LaudoMdl laudoMdl : listLaudoMdl) {

                if (laudoMdl.getNmDiagnostico() != null
                        && !laudoMdl.getNmDiagnostico().trim().isEmpty()
                        && laudoMdl.getNmRecomendacao() != null
                        && !laudoMdl.getNmRecomendacao().trim().isEmpty()) {

                    Tblaudo tblaudo = new Tblaudo();
                    tblaudo.setNmdiagnostico(laudoMdl.getNmDiagnostico());
                    tblaudo.setNmrecomendacao(laudoMdl.getNmRecomendacao());
                    tblaudo.setNmobservacao(laudoMdl.getNmObservacao());
                    tblaudo.setNmrisco(laudoMdl.getNmRisco());
                    tblaudo.setIdgerencia(laudoMdl.getTbgerencia());
                    tblaudo.setLimiteexecucao(laudoMdl.getDtDatalimiteExecucao());
                    tblaudo.setDtdatacadastro(laudoMdl.getDtDataCadastro());
                    tblaudo.setBoolomsap(laudoMdl.isNaoPreencherOmSap());
                    tblaudo.setPrazoexecucao(laudoMdl.getPrazoExecucao());
                    tblaudo.setCondicao(laudoMdl.getSituation());

                    if (!laudoMdl.isNaoPreencherOmSap()) {
                        tblaudo.setOmsap(laudoMdl.getStrOmSap());
                    }

                    tblaudo.setIdsubconjunto(laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto());
                    tblaudo.setIdequipamento(laudoMdl.getTbequipamentoSubconjunto().getIdequipamento());
                    tblaudo.setDtdatalaudo(new Date());
                    tblaudo.setTmdatalaudo(new Date());

                    if (laudoMdl.getIdLaudo() == null) {
                        if (laudoLogic.createTblaudo(tblaudo)) {
                            final Integer condition = tblaudo.getCondicao();
                            tbequipamentoSelected = laudoMdl.getTbequipamentoSubconjunto().getIdequipamento();
                            tbequipamentoSelected.setCondicao(condition);
                            equipamentoLogic.editEquipamento(tbequipamentoSelected);
                            result = true;
                        }
                    } else {                        
                        tblaudo.setIdlaudo(laudoMdl.getIdLaudo());
                        if (laudoLogic.editTblaudo(tblaudo)) {
                            tbequipamentoSelected = laudoMdl.getTbequipamentoSubconjunto().getIdequipamento();
                            final List<Tblaudo> listTblaudosByEquipamento = laudoLogic.findAllTblaudoByEquipamento(tbequipamentoSelected);

                            if (listTblaudosByEquipamento != null
                                    && !listTblaudosByEquipamento.isEmpty()) {
                                tbequipamentoSelected.setCondicao(listTblaudosByEquipamento.get(0).getCondicao());
                            } else {
                                tbequipamentoSelected.setCondicao(StatusConstants.STATUS_LAUDO_NORMAL);
                            }
                            equipamentoLogic.editEquipamento(tbequipamentoSelected);
                            result = true;
                        }
                    }
                }
            }

            if (result) {
                if (uploadedFile != null && uploadedFile.getFileName() != null && !uploadedFile.getFileName().isEmpty()) {
                    doUpload(uploadedFile);
                    equipamentoLogic.editEquipamento(tbequipamentoSelected);
                }
                filterequipamento = null;
                listTbequipamento = null;
                AbstractFacesContextUtils.addMessageInfo("Laudos criado com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Falha ao criar laudos.");
            }
        }

    }

    /**
     * metodo utilizado para criar novos laudos.
     */
    public void gerarNovosLaudos() {

        boolean result = false;

        for (LaudoMdl laudoMdl : listLaudoMdl) {

            if (laudoMdl.getNmDiagnostico() != null
                    && !laudoMdl.getNmDiagnostico().trim().isEmpty()
                    && laudoMdl.getNmRecomendacao() != null
                    && !laudoMdl.getNmRecomendacao().trim().isEmpty()) {

                Tblaudo tblaudo = new Tblaudo();
                tblaudo.setNmdiagnostico(laudoMdl.getNmDiagnostico());
                tblaudo.setNmrecomendacao(laudoMdl.getNmRecomendacao());
                tblaudo.setNmobservacao(laudoMdl.getNmObservacao());
                tblaudo.setNmrisco(laudoMdl.getNmRisco());
                tblaudo.setIdgerencia(laudoMdl.getTbgerencia());
                tblaudo.setLimiteexecucao(laudoMdl.getDtDatalimiteExecucao());
                tblaudo.setDtdatacadastro(laudoMdl.getDtDataCadastro());
                tblaudo.setBoolomsap(laudoMdl.isNaoPreencherOmSap());
                tblaudo.setPrazoexecucao(laudoMdl.getPrazoExecucao());
                tblaudo.setCondicao(laudoMdl.getSituation());

                if (!laudoMdl.isNaoPreencherOmSap()) {
                    tblaudo.setOmsap(laudoMdl.getStrOmSap());
                }

                tblaudo.setIdsubconjunto(laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto());
                tblaudo.setIdequipamento(laudoMdl.getTbequipamentoSubconjunto().getIdequipamento());
                tblaudo.setDtdatalaudo(new Date());
                tblaudo.setTmdatalaudo(new Date());

                if (laudoLogic.createTblaudo(tblaudo)) {
                    final Integer condition = tblaudo.getCondicao();
                    tbequipamentoSelected = laudoMdl.getTbequipamentoSubconjunto().getIdequipamento();
                    tbequipamentoSelected.setCondicao(condition);
                    equipamentoLogic.editEquipamento(tbequipamentoSelected);
                    result = true;
                }
            }
        }

        if (result) {
            if (uploadedFile != null && uploadedFile.getFileName() != null && !uploadedFile.getFileName().isEmpty()) {
                doUpload(uploadedFile);
                equipamentoLogic.editEquipamento(tbequipamentoSelected);
            }
            filterequipamento = null;
            listTbequipamento = null;
            AbstractFacesContextUtils.addMessageInfo("Laudos criado com sucesso.");
        } else {
            AbstractFacesContextUtils.addMessageWarn("Falha ao criar laudos.");
        }

    }

    /**
     * metodo utilizado para fazer o upload dos arquivos.
     *
     * @param uploadedFile
     * @return
     */
    public boolean doUpload(UploadedFile uploadedFile) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String fileExtension = uploadedFile.getFileName();

        String nmDescTemplate = Normalizer.normalize(tbequipamentoSelected.getNmequipamenta(), Normalizer.Form.NFD);
        nmDescTemplate = nmDescTemplate.replaceAll("[^\\p{ASCII}]", "").replace("\"", "");
        String fileName = nmDescTemplate.toUpperCase() + "_" + sdf.format(new Date()) + fileExtension.substring(fileExtension.lastIndexOf('.'), fileExtension.length());

        File file = new File(CAMINHO, fileName);

        final TbarquivosEquipamentoPK tbarquivosEquipamentoPK = new TbarquivosEquipamentoPK();
        tbarquivosEquipamentoPK.setIdequipamento(tbequipamentoSelected.getIdequipamento());
        tbarquivosEquipamentoPK.setTmdataupload(new Date());

        final TbarquivosEquipamento tbarquivosEquipamento = new TbarquivosEquipamento();
        tbarquivosEquipamento.setIdusuario(Shareds.getUser());
        tbarquivosEquipamento.setNmarquivo(fileName);
        tbarquivosEquipamento.setTbarquivosEquipamentoPK(tbarquivosEquipamentoPK);
        tbarquivosEquipamento.setTbequipamento(tbequipamentoSelected);

        try {
            try (FileOutputStream fileOutput = new FileOutputStream(file)) {
                fileOutput.write(IOUtils.toByteArray(uploadedFile.getInputstream()));
                fileOutput.flush();
                fileOutput.close();
            }
            result = true;
            if (arquivosEquipamentoLogic.createTbarquivosEquipamento(tbarquivosEquipamento)) {
                tbequipamentoSelected.getTbarquivosEquipamentoList().add(tbarquivosEquipamento);
            }
        } catch (FileNotFoundException ex) {
            AbstractFacesContextUtils.addMessageError("Falha ao encontrar o arquivo.");
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            AbstractFacesContextUtils.addMessageError("Falha ao abrir o arquivo.");
            ex.printStackTrace(System.err);
        }
        return result;
    }

    public void deleteTbarquivosEquipamento() {

        if (tbarquivosEquipamentoSelected != null) {

            if (tbequipamentoSelected.getTbarquivosEquipamentoList().remove(tbarquivosEquipamentoSelected)) {
                arquivosEquipamentoLogic.deleteTbarquivosEquipamento(tbarquivosEquipamentoSelected);
                AbstractFacesContextUtils.addMessageInfo("Arquivo removido com sucesso.");
            } else {
                AbstractFacesContextUtils.addMessageWarn("Erro ao remover arquivo.");
            }
        }
    }

    private boolean validaCondicaoExecutado() {

        boolean result = false;

        for (LaudoMdl laudoMdl : listLaudoMdl) {

            if (laudoMdl.getIdLaudo() == null
                    && laudoMdl.getNmDiagnostico() != null
                    && !laudoMdl.getNmDiagnostico().trim().isEmpty()
                    && laudoMdl.getNmRecomendacao() != null
                    && !laudoMdl.getNmRecomendacao().trim().isEmpty()
                    && laudoMdl.getSituation() == StatusConstants.STATUS_LAUDO_EXECUTADO) {
                result = true;
                break;
            }
        }

        return result;
    }

    /**
     * @return the tecnicaLogic
     */
    public TecnicaLogic getTecnicaLogic() {
        return tecnicaLogic;
    }

    /**
     * @param tecnicaLogic the tecnicaLogic to set
     */
    public void setTecnicaLogic(TecnicaLogic tecnicaLogic) {
        this.tecnicaLogic = tecnicaLogic;
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
     * @return the listTbtecnica
     */
    public List<Tbtecnica> getListTbtecnica() {
        return listTbtecnica;
    }

    /**
     * @param listTbtecnica the listTbtecnica to set
     */
    public void setListTbtecnica(List<Tbtecnica> listTbtecnica) {
        this.listTbtecnica = listTbtecnica;
    }

    /**
     * @return the listTbequipamento
     */
    public List<Tbequipamento> getListTbequipamento() {
        return listTbequipamento;
    }

    /**
     * @param listTbequipamento the listTbequipamento to set
     */
    public void setListTbequipamento(List<Tbequipamento> listTbequipamento) {
        this.listTbequipamento = listTbequipamento;
    }

    /**
     * @return the idtecnica
     */
    public int getIdtecnica() {
        return idtecnica;
    }

    /**
     * @param idtecnica the idtecnica to set
     */
    public void setIdtecnica(int idtecnica) {
        this.idtecnica = idtecnica;
    }

    /**
     * @return the idequipamento
     */
    public int getIdequipamento() {
        return idequipamento;
    }

    /**
     * @param idequipamento the idequipamento to set
     */
    public void setIdequipamento(int idequipamento) {
        this.idequipamento = idequipamento;
    }

    /**
     * @return the tbequipamento
     */
    public Tbequipamento getTbequipamento() {
        return tbequipamento;
    }

    /**
     * @param tbequipamento the tbequipamento to set
     */
    public void setTbequipamento(Tbequipamento tbequipamento) {
        this.tbequipamento = tbequipamento;
    }

    /**
     * @return the filterequipamento
     */
    public List<Tbequipamento> getFilterequipamento() {
        return filterequipamento;
    }

    /**
     * @param filterequipamento the filterequipamento to set
     */
    public void setFilterequipamento(List<Tbequipamento> filterequipamento) {
        this.filterequipamento = filterequipamento;
    }

    /**
     * @return the listEquipamentos
     */
    public List<EquipamentoModel> getListEquipamentos() {
        return listEquipamentos;
    }

    /**
     * @param listEquipamentos the listEquipamentos to set
     */
    public void setListEquipamentos(List<EquipamentoModel> listEquipamentos) {
        this.listEquipamentos = listEquipamentos;
    }

    /**
     * @return the tbequipamentoSubconjunto
     */
    public TbequipamentoSubconjunto getTbequipamentoSubconjunto() {
        return tbequipamentoSubconjunto;
    }

    /**
     * @param tbequipamentoSubconjunto the tbequipamentoSubconjunto to set
     */
    public void setTbequipamentoSubconjunto(TbequipamentoSubconjunto tbequipamentoSubconjunto) {
        this.tbequipamentoSubconjunto = tbequipamentoSubconjunto;
    }

    /**
     * @return the listTbequipamentoSubconjuntos
     */
    public List<TbequipamentoSubconjunto> getListTbequipamentoSubconjuntos() {
        return listTbequipamentoSubconjuntos;
    }

    /**
     * @param listTbequipamentoSubconjuntos the listTbequipamentoSubconjuntos to
     * set
     */
    public void setListTbequipamentoSubconjuntos(List<TbequipamentoSubconjunto> listTbequipamentoSubconjuntos) {
        this.listTbequipamentoSubconjuntos = listTbequipamentoSubconjuntos;
    }

    /**
     * @return the idequipamentoSubconjunto
     */
    public int getIdequipamentoSubconjunto() {
        return idequipamentoSubconjunto;
    }

    /**
     * @param idequipamentoSubconjunto the idequipamentoSubconjunto to set
     */
    public void setIdequipamentoSubconjunto(int idequipamentoSubconjunto) {
        this.idequipamentoSubconjunto = idequipamentoSubconjunto;
    }

    /**
     * @return the tbequipamentoSelected
     */
    public Tbequipamento getTbequipamentoSelected() {
        return tbequipamentoSelected;
    }

    /**
     * @param tbequipamentoSelected the tbequipamentoSelected to set
     */
    public void setTbequipamentoSelected(Tbequipamento tbequipamentoSelected) {
        this.tbequipamentoSelected = tbequipamentoSelected;
    }

    /**
     * @return the uploadedFile
     */
    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    /**
     * @param uploadedFile the uploadedFile to set
     */
    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
    }

    /**
     * @return the listLaudoMdl
     */
    public List<LaudoMdl> getListLaudoMdl() {

        listLaudoMdl = new ArrayList<>();
        List<TbequipamentoSubconjunto> listResult = equimentoSubconjuntoLogic.getListAllTbequipamentoSubconjuntoByIdEquipamento(tbequipamentoSelected);

        if (listResult != null) {
            for (TbequipamentoSubconjunto equipamentoSubconjunto : listResult) {
                LaudoMdl laudoMdl = new LaudoMdl();
                laudoMdl.setTbequipamentoSubconjunto(equipamentoSubconjunto);
                laudoMdl.setIdEquipamentoSubconjunto(equipamentoSubconjunto.getIdequipamentoSubconjunto());

                Tblaudo tblaudo = laudoLogic.findTblaudoByEquipamentoAndSubConjunto(equipamentoSubconjunto.getIdequipamento(), equipamentoSubconjunto.getIdsubconjunto());

                if (tblaudo != null) {

                    laudoMdl.setNmDiagnostico(tblaudo.getNmdiagnostico());
                    laudoMdl.setNmRecomendacao(tblaudo.getNmrecomendacao());
                    laudoMdl.setNmObservacao(tblaudo.getNmobservacao());
                    laudoMdl.setNmRisco(tblaudo.getNmrisco());
                    laudoMdl.setDtDatalimiteExecucao(tblaudo.getLimiteexecucao());
                    laudoMdl.setDtDataCadastro(tblaudo.getDtdatacadastro());
                    laudoMdl.setNaoPreencherOmSap(tblaudo.getBoolomsap());
                    laudoMdl.setPrazoExecucao(tblaudo.getPrazoexecucao());
                    laudoMdl.setTbgerencia(tblaudo.getIdgerencia());
                    laudoMdl.setSituation(tblaudo.getCondicao());
                    laudoMdl.setIdLaudo(tblaudo.getIdlaudo());

                    if (!tblaudo.getBoolomsap()) {
                        laudoMdl.setStrOmSap(tblaudo.getOmsap());
                    }
                }

                listLaudoMdl.add(laudoMdl);
            }
        }

        return listLaudoMdl;
    }

    /**
     * @param listLaudoMdl the listLaudoMdl to set
     */
    public void setListLaudoMdl(List<LaudoMdl> listLaudoMdl) {
        this.listLaudoMdl = listLaudoMdl;
    }

    /**
     * @return the tbarquivosEquipamentoSelected
     */
    public TbarquivosEquipamento getTbarquivosEquipamentoSelected() {
        return tbarquivosEquipamentoSelected;
    }

    /**
     * @param tbarquivosEquipamentoSelected the tbarquivosEquipamentoSelected to
     * set
     */
    public void setTbarquivosEquipamentoSelected(TbarquivosEquipamento tbarquivosEquipamentoSelected) {
        this.tbarquivosEquipamentoSelected = tbarquivosEquipamentoSelected;
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
     * @return the listTbgerencias
     */
    public List<Tbgerencia> getListTbgerencias() {
        return listTbgerencias;
    }

    /**
     * @param listTbgerencias the listTbgerencias to set
     */
    public void setListTbgerencias(List<Tbgerencia> listTbgerencias) {
        this.listTbgerencias = listTbgerencias;
    }
}

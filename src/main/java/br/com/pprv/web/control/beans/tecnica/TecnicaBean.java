/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.tecnica;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.TbequipamentoSubconjunto;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.model.entities.custom.EquipamentoModel;
import br.com.pprv.model.entities.custom.LaudoMdl;
import br.com.pprv.web.control.logic.equipamento.EquipamentoLogic;
import br.com.pprv.web.control.logic.equipamento_subconjunto.EquipamentoSubconjuntoLogic;
import br.com.pprv.web.control.logic.laudo.LaudoLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
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

    private static final String CAMINHO = "/upload/arquivos/";

    @EJB
    private EquipamentoLogic equipamentoLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private EquipamentoSubconjuntoLogic equimentoSubconjuntoLogic;
    @EJB
    private LaudoLogic laudoLogic;

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

    private UploadedFile uploadedFile;

    @PostConstruct
    public void init() {
        listTbtecnica = tecnicaLogic.listallTecnica();
        listEquipamentos = new ArrayList<>();

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
        if (tbtecnica != null) {
            listTbequipamento = equipamentoLogic.findTbequipamentoByTbtecnica(tbtecnica);
        } else {
            AbstractFacesContextUtils.addMessageWarn("Nenhuma tecnica selecionada.");
        }
    }

    public void updateStatusCondicaoEquipamento() {

        if (tbequipamentoSelected != null) {

            switch (tbequipamentoSelected.getCondicao()) {
                case 1:
                    tbequipamentoSelected.setCondicao(2);
                    equipamentoLogic.editEquipamento(tbequipamentoSelected);
                    break;
                case 2:
                    tbequipamentoSelected.setCondicao(3);
                    equipamentoLogic.editEquipamento(tbequipamentoSelected);
                    break;
                case 3:
                    tbequipamentoSelected.setCondicao(1);
                    equipamentoLogic.editEquipamento(tbequipamentoSelected);
                    break;
            }
        }
    }

    /**
     * metodo utilizado para criar os laudos.
     */
    public void createLaudos() {

        for (LaudoMdl laudoMdl : listLaudoMdl) {

            Tblaudo tblaudo = new Tblaudo();
            tblaudo.setNmdiagnostico(laudoMdl.getNmDiagnostico());
            tblaudo.setNmrecomendacao(laudoMdl.getNmRecomendacao());
            tblaudo.setIdgerencia(new Tbgerencia(1));
            tblaudo.setLimiteexecucao(laudoMdl.getDtDatalimiteExecucao());
            tblaudo.setDataanalise(laudoMdl.getDtDataAnalise());
            tblaudo.setDatacadastro(laudoMdl.getDtDataCadastro());
            tblaudo.setBoolos(laudoMdl.isNaoPreencherOs());

            if (!laudoMdl.isNaoPreencherOs()) {
                tblaudo.setOsmaximo(laudoMdl.getIntOsMaximo());
            }

            tblaudo.setIdsubconjunto(laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto());
            tblaudo.setIdequipamento(laudoMdl.getTbequipamentoSubconjunto().getIdequipamento());
            tblaudo.setDtdatalaudo(new Date());
            tblaudo.setTmdatalaudo(new Date());

            if (laudoMdl.getTbequipamentoSubconjunto().getIdequipamento().getCondicao() != 1) {
                /**
                 * se for amarelo ou vermelho, criar um novo laudo.
                 */
                laudoLogic.createTblaudo(tblaudo);
            } else {
                /**
                 * se for verde, editar o laudo.
                 */

                Tblaudo laudo = laudoLogic.findTblaudoByEquipamentoAndSubConjunto(laudoMdl.getTbequipamentoSubconjunto().getIdequipamento(), laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto());

                if (laudo != null) {
                    laudo.setNmdiagnostico(laudoMdl.getNmDiagnostico());
                    laudo.setNmrecomendacao(laudoMdl.getNmRecomendacao());
                    laudo.setIdgerencia(new Tbgerencia(1));
                    laudo.setLimiteexecucao(laudoMdl.getDtDatalimiteExecucao());
                    laudo.setDataanalise(laudoMdl.getDtDataAnalise());
                    laudo.setDatacadastro(laudoMdl.getDtDataCadastro());
                    laudo.setBoolos(laudoMdl.isNaoPreencherOs());

                    if (!laudoMdl.isNaoPreencherOs()) {
                        laudo.setOsmaximo(laudoMdl.getIntOsMaximo());
                    }

                    laudo.setIdsubconjunto(laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto());
                    laudo.setIdequipamento(laudoMdl.getTbequipamentoSubconjunto().getIdequipamento());
                    laudo.setDtdatalaudo(new Date());
                    laudo.setTmdatalaudo(new Date());
                    laudoLogic.editTblaudo(laudo);
                } else {
                    laudoLogic.createTblaudo(tblaudo);
                }
            }

//            equipamento = laudoMdl.getTbequipamentoSubconjunto().getIdequipamento();
//            System.out.println(" equip: " + laudoMdl.getTbequipamentoSubconjunto().getIdequipamento().getNmequipamenta());
//            System.out.println(" sub: " + laudoMdl.getTbequipamentoSubconjunto().getIdsubconjunto().getNmsubconjunto());
        }

        if (uploadedFile != null) {
            doUpload(tbequipamentoSelected, uploadedFile);
            equipamentoLogic.editEquipamento(tbequipamentoSelected);
        }

        AbstractFacesContextUtils.addMessageInfo("Laudos criado com sucesso.");
    }

    /**
     * metodo utilizado para fazer o upload dos arquivos.
     *
     * @param tbequipamento
     * @param uploadedFile
     * @return
     */
    public boolean doUpload(Tbequipamento tbequipamento, UploadedFile uploadedFile) {
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");

        String fileExtension = uploadedFile.getFileName();

        String nmDescTemplate = Normalizer.normalize(tbequipamento.getNmequipamenta(), Normalizer.Form.NFD);
        nmDescTemplate = nmDescTemplate.replaceAll("[^\\p{ASCII}]", "").replace("\"", "");
        String fileName = nmDescTemplate.toUpperCase() + "_" + sdf.format(new Date()) + fileExtension.substring(fileExtension.lastIndexOf('.'), fileExtension.length());

        File file = new File(CAMINHO, fileName);

        tbequipamento.setArquivoEquipamento(fileName);

        try {
            try (FileOutputStream fileOutput = new FileOutputStream(file)) {
                fileOutput.write(IOUtils.toByteArray(uploadedFile.getInputstream()));
                fileOutput.flush();
                fileOutput.close();
            }
            result = true;
        } catch (FileNotFoundException ex) {
            AbstractFacesContextUtils.addMessageError("Falha ao encontrar o arquivo.");
            ex.printStackTrace(System.err);
        } catch (IOException ex) {
            AbstractFacesContextUtils.addMessageError("Falha ao abrir o arquivo.");
            ex.printStackTrace(System.err);
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

        for (TbequipamentoSubconjunto equipamentoSubconjunto : equimentoSubconjuntoLogic.listAllTbequipamentoSubconjuntoByIdEquipamento(tbequipamentoSelected)) {
            LaudoMdl laudoMdl = new LaudoMdl();
            laudoMdl.setTbequipamentoSubconjunto(equipamentoSubconjunto);
            laudoMdl.setIdEquipamentoSubconjunto(equipamentoSubconjunto.getIdequipamentoSubconjunto());

            Tblaudo tblaudo = laudoLogic.findTblaudoByEquipamentoAndSubConjunto(equipamentoSubconjunto.getIdequipamento(), equipamentoSubconjunto.getIdsubconjunto());

            if (tblaudo != null) {

                laudoMdl.setNmDiagnostico(tblaudo.getNmdiagnostico());
                laudoMdl.setNmRecomendacao(tblaudo.getNmrecomendacao());
                laudoMdl.setDtDatalimiteExecucao(tblaudo.getLimiteexecucao());
                laudoMdl.setDtDataAnalise(tblaudo.getDataanalise());
                laudoMdl.setDtDataCadastro(tblaudo.getDatacadastro());
                laudoMdl.setNaoPreencherOs(tblaudo.getBoolos());

                if (!tblaudo.getBoolos()) {
                    laudoMdl.setIntOsMaximo(tblaudo.getOsmaximo());
                }
            }

            listLaudoMdl.add(laudoMdl);
        }

        return listLaudoMdl;
    }

    /**
     * @param listLaudoMdl the listLaudoMdl to set
     */
    public void setListLaudoMdl(List<LaudoMdl> listLaudoMdl) {
        this.listLaudoMdl = listLaudoMdl;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.graficos.laudosporgerencia;

import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbtecnica;
import br.com.pprv.web.control.logic.gerencia.GerenciaLogic;
import br.com.pprv.web.control.logic.graficos.porgerencia.GraficoPorGerenciaLogic;
import br.com.pprv.web.control.logic.tecnica.TecnicaLogic;
import br.com.pprv.web.faces.converter.ConvData;
import br.com.pprv.web.faces.converter.TimeControl;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "graficoPorGerenciaBean")
@SessionScoped
public class GraficoPorGerenciaBean implements Serializable {

    @EJB
    private GraficoPorGerenciaLogic gerenciaLogic;
    @EJB
    private TecnicaLogic tecnicaLogic;
    @EJB
    private GerenciaLogic managerLogic;

    private Date dataInicial;
    private Date dataFinal;
    private Integer optionSelected;
    private String jsonContent;
    private List<Object[]> listObjects;
    private String jsonContentSecond;
    private List<Object[]> listObjectsSecond;
    private String jsonContentThird;
    private String jsonContenThirdPie;
    private List<Object[]> listObjectsThird;
    private String jsonContenFourth;
    private List<Object[]> listObjectsFourth;
    private String jsonContenFifth;
    private List<Object[]> listObjectsFifth;

    private List<Tbgerencia> listTbgerencias;
    private List<Tbtecnica> listTbtecnica;

    private Tbtecnica tbtecnica;
    private Tbgerencia tbgerencia;

    @PostConstruct
    public void init() {

        dataInicial = new Date();

        listTbgerencias = managerLogic.findAllTbgerencia();
        listTbtecnica = tecnicaLogic.getListTbtecnica();
    }

    public void geraGrafico() {

        if (dataInicial != null) {
            listObjects = gerenciaLogic.getLaudosPorGerencia(dataInicial, dataFinal, tbtecnica, tbgerencia);
            listObjectsSecond = gerenciaLogic.getLaudosPorGerenciaMesAtual(tbtecnica, tbgerencia);
            listObjectsThird = gerenciaLogic.getLaudosPorGerenciaByLimiteExecucao(dataInicial, dataFinal, tbtecnica, tbgerencia);
            listObjectsFourth = gerenciaLogic.getLaudosPorGerenciaECondicao(dataInicial, dataFinal, tbtecnica, tbgerencia);
            listObjectsFifth = gerenciaLogic.getLaudosPorGerenciaEmAtrasoECritico(dataInicial, dataFinal, tbtecnica, tbgerencia);

            if (listObjectsFifth != null
                    && !listObjectsFifth.isEmpty()) {
                jsonContenFifth = getJSONLaudosPorGerencia(listObjectsFifth);
            }else{
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado laudos críticos em atraso.");
            }

            if (listObjectsFourth != null
                    && !listObjectsFourth.isEmpty()) {
                jsonContenFourth = getJSONColumnLaudosPorGerenciaLimiteExecucao(listObjectsFourth);
            }else{
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado laudos por condição.");
            }

            if (listObjectsThird != null
                    && !listObjectsThird.isEmpty()) {
                jsonContentThird = getJSONColumnLaudosPorGerenciaLimiteExecucao(listObjectsThird);
                jsonContenThirdPie = getJSONPieLaudosPorGerenciaLimiteExecucao(listObjectsThird);
            }else{
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado laudos por prazo.");
            }

            if (listObjectsSecond != null
                    && !listObjectsSecond.isEmpty()) {
                jsonContentSecond = getJSONLaudosPorGerencia(listObjectsSecond);
            }else{
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado laudos mês atual.");
            }

            if (listObjects != null
                    && !listObjects.isEmpty()) {
                jsonContent = getJSONLaudosPorGerencia(listObjects);
            } else {
                AbstractFacesContextUtils.addMessageInfo("Nenhum resultado encontrado.");
            }
        } else {
            AbstractFacesContextUtils.addMessageInfo("É necessário informar pelo menos a data inicial.");
        }
    }

    private String getJSONLaudosPorGerencia(final List<Object[]> listObjects) {

        StringBuilder builder = new StringBuilder();

        if (listObjects != null && !listObjects.isEmpty()) {
            builder.append("[");

            for (int position = 0; position < listObjects.size(); position++) {

                builder.append("{");
                builder.append("\"country\": \"").append(listObjects.get(position)[1].toString().trim()).append("\"").append(", ");
                builder.append("\"visits\": ").append(listObjects.get(position)[2].toString());
                builder.append("}");

                builder.append(",");
            }

            builder.setLength(builder.length() - 1);

            builder.append("];");
        }

        return builder.toString();
    }

    private String getJSONColumnLaudosPorGerenciaLimiteExecucao(final List<Object[]> listObjects) {

        StringBuilder builder = new StringBuilder();

        if (listObjects != null && !listObjects.isEmpty()) {
            builder.append("[");

            for (int position = 0; position < listObjects.size(); position++) {

                builder.append("{");
                builder.append("\"year\": \"").append(listObjects.get(position)[1].toString().trim()).append("\"").append(", ");
                builder.append("\"income\": ").append(listObjects.get(position)[2].toString()).append(", ");
                builder.append("\"expenses\": ").append(listObjects.get(position)[3].toString());
                builder.append("}");

                builder.append(",");
            }

            builder.setLength(builder.length() - 1);

            builder.append("];");
        }

        return builder.toString();
    }

    private String getJSONPieLaudosPorGerenciaLimiteExecucao(final List<Object[]> listObjects) {

        StringBuilder builder = new StringBuilder();
        Integer sumInTime = 0;
        Integer sumLate = 0;

        if (listObjects != null && !listObjects.isEmpty()) {

            for (Object[] listObject : listObjects) {
                sumInTime = sumInTime + Integer.parseInt(listObject[2].toString());
                sumLate = sumLate + Integer.parseInt(listObject[3].toString());
            }

            builder.append("[")
                    .append("{")
                    .append("\"country\": \"").append("NO PRAZO").append("\"").append(", ")
                    .append("\"litres\": ").append(sumInTime)
                    .append("}")
                    .append(",")
                    .append("{")
                    .append("\"country\": \"").append("EM ATRASO").append("\"").append(", ")
                    .append("\"litres\": ").append(sumLate)
                    .append("}")
                    .append("];");
        }

        return builder.toString();
    }

    public void carregaDataPorOpcao() {
        System.out.println("entrei no carregaDataPorOpcao");
        switch (optionSelected) {
            case 1:
                carregaSemanaAnterior();
                listObjects = null;
                break;
            case 2:
                carregaDiaAnterior();
                listObjects = null;
                break;
            case 3:
                carregaDataPorPeriodo();
                listObjects = null;
                break;
        }
    }

    public boolean getResult() {

        if (listObjects != null
                && !listObjects.isEmpty()) {
            return true;
        }

        if (listObjectsSecond != null
                && !listObjectsSecond.isEmpty()) {
            return true;
        }

        if (listObjectsThird != null
                && !listObjectsThird.isEmpty()) {
            return true;
        }

        return false;
    }

    private void carregaDiaAnterior() {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        calendar.set(Calendar.DAY_OF_MONTH, calendar.get(Calendar.DAY_OF_MONTH) - 1);
        dataInicial = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();

        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 0);

        calendar2.set(Calendar.DAY_OF_MONTH, calendar2.get(Calendar.DAY_OF_MONTH) - 1);
        dataFinal = calendar2.getTime();
    }

    private void carregaSemanaAnterior() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.WEEK_OF_YEAR, calendar.get(Calendar.WEEK_OF_YEAR) - 1);

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
        dataInicial = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.WEEK_OF_YEAR, calendar2.get(Calendar.WEEK_OF_YEAR) - 1);

        if (calendar2.getTime().before(calendar.getTime())) {
            calendar2 = Calendar.getInstance();
        }

        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        dataFinal = calendar2.getTime();
    }

    private void carregaDataPorPeriodo() {
        dataInicial = TimeControl.getDateIni();
        dataFinal = null;
    }

    public String getTitle() {
        return "Quantidade de Laudos por Gerência" + ConvData.parseDatatoIsoEn(dataInicial);
    }

    /**
     * @return the dataInicial
     */
    public Date getDataInicial() {
        return dataInicial;
    }

    /**
     * @param dataInicial the dataInicial to set
     */
    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    /**
     * @return the dataFinal
     */
    public Date getDataFinal() {
        return dataFinal;
    }

    /**
     * @param dataFinal the dataFinal to set
     */
    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    /**
     * @return the optionSelected
     */
    public Integer getOptionSelected() {
        return optionSelected;
    }

    /**
     * @param optionSelected the optionSelected to set
     */
    public void setOptionSelected(Integer optionSelected) {
        this.optionSelected = optionSelected;
    }

    /**
     * @return the jsonContent
     */
    public String getJsonContent() {
        return jsonContent;
    }

    /**
     * @param jsonContent the jsonContent to set
     */
    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    /**
     * @return the listObjects
     */
    public List<Object[]> getListObjects() {
        return listObjects;
    }

    /**
     * @param listObjects the listObjects to set
     */
    public void setListObjects(List<Object[]> listObjects) {
        this.listObjects = listObjects;
    }

    /**
     * @return the jsonContentSecond
     */
    public String getJsonContentSecond() {
        return jsonContentSecond;
    }

    /**
     * @param jsonContentSecond the jsonContentSecond to set
     */
    public void setJsonContentSecond(String jsonContentSecond) {
        this.jsonContentSecond = jsonContentSecond;
    }

    /**
     * @return the listObjectsSecond
     */
    public List<Object[]> getListObjectsSecond() {
        return listObjectsSecond;
    }

    /**
     * @param listObjectsSecond the listObjectsSecond to set
     */
    public void setListObjectsSecond(List<Object[]> listObjectsSecond) {
        this.listObjectsSecond = listObjectsSecond;
    }

    /**
     * @return the jsonContentThird
     */
    public String getJsonContentThird() {
        return jsonContentThird;
    }

    /**
     * @param jsonContentThird the jsonContentThird to set
     */
    public void setJsonContentThird(String jsonContentThird) {
        this.jsonContentThird = jsonContentThird;
    }

    /**
     * @return the listObjectsThird
     */
    public List<Object[]> getListObjectsThird() {
        return listObjectsThird;
    }

    /**
     * @param listObjectsThird the listObjectsThird to set
     */
    public void setListObjectsThird(List<Object[]> listObjectsThird) {
        this.listObjectsThird = listObjectsThird;
    }

    /**
     * @return the jsonContenThirdPie
     */
    public String getJsonContenThirdPie() {
        return jsonContenThirdPie;
    }

    /**
     * @param jsonContenThirdPie the jsonContenThirdPie to set
     */
    public void setJsonContenThirdPie(String jsonContenThirdPie) {
        this.jsonContenThirdPie = jsonContenThirdPie;
    }

    /**
     * @return the jsonContenFourth
     */
    public String getJsonContenFourth() {
        return jsonContenFourth;
    }

    /**
     * @param jsonContenFourth the jsonContenFourth to set
     */
    public void setJsonContenFourth(String jsonContenFourth) {
        this.jsonContenFourth = jsonContenFourth;
    }

    /**
     * @return the listObjectsFourth
     */
    public List<Object[]> getListObjectsFourth() {
        return listObjectsFourth;
    }

    /**
     * @param listObjectsFourth the listObjectsFourth to set
     */
    public void setListObjectsFourth(List<Object[]> listObjectsFourth) {
        this.listObjectsFourth = listObjectsFourth;
    }

    /**
     * @return the jsonContenFifth
     */
    public String getJsonContenFifth() {
        return jsonContenFifth;
    }

    /**
     * @param jsonContenFifth the jsonContenFifth to set
     */
    public void setJsonContenFifth(String jsonContenFifth) {
        this.jsonContenFifth = jsonContenFifth;
    }

    /**
     * @return the listObjectsFifth
     */
    public List<Object[]> getListObjectsFifth() {
        return listObjectsFifth;
    }

    /**
     * @param listObjectsFifth the listObjectsFifth to set
     */
    public void setListObjectsFifth(List<Object[]> listObjectsFifth) {
        this.listObjectsFifth = listObjectsFifth;
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

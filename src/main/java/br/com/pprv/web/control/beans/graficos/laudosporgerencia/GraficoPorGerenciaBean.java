/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.graficos.laudosporgerencia;

import br.com.pprv.web.faces.converter.TimeControl;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author ioliveira
 */
@ManagedBean(name = "graficoPorGerenciaBean")
@SessionScoped
public class GraficoPorGerenciaBean implements Serializable {

    private Date dataInicial;
    private Date dataFinal;
    private Integer optionSelected;

    @PostConstruct
    public void init() {
        optionSelected = 2;
        if (dataInicial == null || dataFinal == null) {
            carregaDataPorOpcao();
        }
    }

    public void geraGrafico() {

    }

    public void carregaDataPorOpcao() {
        System.out.println("entrei no carregaDataPorOpcao");
        switch (optionSelected) {
            case 1:
                carregaSemanaAnterior();
                break;
            case 2:
                carregaDiaAnterior();
                break;
            case 3:
                carregaDataPorPeriodo();
                break;
        }
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
        
        if (calendar2.getTime().before(calendar.getTime())){
            calendar2.set(Calendar.WEEK_OF_YEAR, calendar2.get(Calendar.WEEK_OF_YEAR) + 1);
        }

        calendar2.set(Calendar.HOUR_OF_DAY, 23);
        calendar2.set(Calendar.MINUTE, 59);
        calendar2.set(Calendar.SECOND, 0);
        calendar2.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
        dataFinal = calendar2.getTime();
    }

    private void carregaDataPorPeriodo() {
        dataInicial = TimeControl.getDateIni();
        dataFinal = TimeControl.getDateFim();
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

}

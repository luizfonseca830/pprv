/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities.custom;

import br.com.pprv.model.entities.Tblaudo;
import java.io.Serializable;

/**
 *
 * @author ioliveira
 */
public class FecharLaudoModel implements Serializable{
    
    private Tblaudo tblaudo;
    private boolean executar;        

    /**
     * @return the tblaudo
     */
    public Tblaudo getTblaudo() {
        return tblaudo;
    }

    /**
     * @param tblaudo the tblaudo to set
     */
    public void setTblaudo(Tblaudo tblaudo) {
        this.tblaudo = tblaudo;
    }

    /**
     * @return the executar
     */
    public boolean isExecutar() {
        return executar;
    }

    /**
     * @param executar the executar to set
     */
    public void setExecutar(boolean executar) {
        this.executar = executar;
    }
    
    
}

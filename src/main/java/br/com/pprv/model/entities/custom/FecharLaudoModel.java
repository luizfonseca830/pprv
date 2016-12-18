/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities.custom;

import br.com.pprv.model.entities.Tblaudo;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author ioliveira
 */
public class FecharLaudoModel implements Serializable {

    private Integer idFecharLaudo;
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

    /**
     * @return the idFecharLaudo
     */
    public Integer getIdFecharLaudo() {
        return idFecharLaudo;
    }

    /**
     * @param idFecharLaudo the idFecharLaudo to set
     */
    public void setIdFecharLaudo(Integer idFecharLaudo) {
        this.idFecharLaudo = idFecharLaudo;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.idFecharLaudo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FecharLaudoModel other = (FecharLaudoModel) obj;
        if (!Objects.equals(this.idFecharLaudo, other.idFecharLaudo)) {
            return false;
        }
        return true;
    }

}

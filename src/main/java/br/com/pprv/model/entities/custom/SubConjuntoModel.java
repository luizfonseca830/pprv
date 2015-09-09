/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.entities.custom;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbsubconjunto;
import java.io.Serializable;

/**
 *
 * @author JorgeFonseca
 */
public class SubConjuntoModel implements Serializable{
    
    private Tbsubconjunto tbsubconjunto;
    private Tbequipamento tbequipamento;

    /**
     * @return the tbsubconjunto
     */
    public Tbsubconjunto getTbsubconjunto() {
        return tbsubconjunto;
    }

    /**
     * @param tbsubconjunto the tbsubconjunto to set
     */
    public void setTbsubconjunto(Tbsubconjunto tbsubconjunto) {
        this.tbsubconjunto = tbsubconjunto;
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
    
    
}

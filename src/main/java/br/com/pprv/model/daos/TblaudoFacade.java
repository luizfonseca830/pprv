/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tblaudo;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TblaudoFacade extends AbstractFacade<Tblaudo> {
  

    public TblaudoFacade() {
        super(Tblaudo.class);
    }
    
}

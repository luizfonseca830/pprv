/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbinspecao;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbinspecaoFacade extends AbstractFacade<Tbinspecao> {
   

    public TbinspecaoFacade() {
        super(Tbinspecao.class);
    }
    
}

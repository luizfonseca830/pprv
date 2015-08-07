/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbgerencia;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbgerenciaFacade extends AbstractFacade<Tbgerencia> {
   

    public TbgerenciaFacade() {
        super(Tbgerencia.class);
    }
    
}

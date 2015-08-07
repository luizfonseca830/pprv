/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.util;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeFonseca
 */
@Singleton
public class SelectDB {
    
    @PersistenceContext(unitName = "pprvPU")
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }
}

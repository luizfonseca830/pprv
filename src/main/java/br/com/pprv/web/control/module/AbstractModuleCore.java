/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.module;

import br.com.pprv.model.util.SelectDB;
import javax.ejb.EJB;
import javax.persistence.EntityManager;

/**
 *
 * @author JorgeFonseca
 */
public abstract class AbstractModuleCore {

    @EJB
    private SelectDB selectDB;

    public EntityManager getEM() {
        return selectDB.getEntityManager();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.TbarquivosEquipamento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;

/**
 *
 * @author ioliveira
 */
@Stateless
public class TbarquivosEquipamentoFacade extends AbstractFacade<TbarquivosEquipamento> {

    public TbarquivosEquipamentoFacade() {
        super(TbarquivosEquipamento.class);
    }

    public boolean removeTbarquivosEquipamento(final TbarquivosEquipamento tbarquivosEquipamento, final EntityManager entityManager) {
        return entityManager.createQuery("DELETE FROM TbarquivosEquipamento t WHERE t.tbarquivosEquipamentoPK = :tbarquivoEquipamentoPK")
                .setParameter("tbarquivoEquipamentoPK", tbarquivosEquipamento.getTbarquivosEquipamentoPK())
                .executeUpdate() > 1;
    }
}

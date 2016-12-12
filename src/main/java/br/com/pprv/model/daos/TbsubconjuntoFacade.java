/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;

import br.com.pprv.model.entities.Tbsubconjunto;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbsubconjuntoFacade extends AbstractFacade<Tbsubconjunto> {

    public TbsubconjuntoFacade() {
        super(Tbsubconjunto.class);
    }

    /**
     * Metodo carrega todos os subconjuntos cadastros
     *
     * @param em
     * @return List Tbsubconjunto
     */
    public List<Tbsubconjunto> findAllSubconjunto(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbsubconjunto t").getResultList();
    }

    /**
     * metodo que recupera o subconjunto por nome
     *
     * @param name
     * @param em
     * @return List TbsuconjuntoByName
     */
    public List<Tbsubconjunto> findallTbconjuntoByName(String name, EntityManager em) {
  
            return em.createQuery("SELECT t FROM Tbsubconjunto t WHERE UPPER (t.nmsubconjunto) LIKE (:nmSubconjunto) ", Tbsubconjunto.class)
                    .setParameter("nmsubconjunto", name.toUpperCase() + "%")
                    .getResultList();
      
    }
}

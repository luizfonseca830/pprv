/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.model.daos;


import br.com.pprv.model.entities.Tbtecnica;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class TbtecnicaFacade extends AbstractFacade<Tbtecnica> {

    public TbtecnicaFacade() {
        super(Tbtecnica.class);
    }
    public List<Tbtecnica> findAll(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbtecnica t").getResultList();
    }

    public List<Tbtecnica> listTbtecnicaByNome(String nome, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbtecnica t WHERE UPPER(t.nmtecnica) LIKE (:nome)")
                .setParameter("nome", nome.toUpperCase() + "%")
                .getResultList();
    }

    public Tbtecnica FindTbtecnicaByNome(String nome, EntityManager em) {
        Tbtecnica result;
        try {
            result = em.createQuery("SELECT t FROM Tbtecnica t WHERE t.nmtecnica = :nome", Tbtecnica.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            result = null;
        } catch (Exception e) {
            result = null;
        }
        return result;
    }

    public List<Tbtecnica> listTbtecnicaBynmtecnica(String nmtecnica, EntityManager em) {
        return em.createQuery("SELECT t FROM Tbtecnica t WHERE (t.nmtecnica) LIKE (:nmtecnica)", Tbtecnica.class)
                .setParameter("nmtecnica", nmtecnica + "%").getResultList();
    }
    
       public List<Tbtecnica> allTecnica(EntityManager em) {
        return em.createQuery("SELECT t FROM Tbtecnica t ORDER BY t.nmtecnica").getResultList();
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.logic.subconjunto;

import br.com.pprv.model.daos.TbsubconjuntoFacade;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class SubconjuntoLogic extends AbstractModuleCore {

    @EJB
    private TbsubconjuntoFacade tbsubconjuntoFacade;

    /**
     * metodo de carrega todos os subconjuntos cadastrados.
     *
     * @return
     */
    public List<Tbsubconjunto> findallTbsubconjunto() {
        return tbsubconjuntoFacade.findAllSubconjunto(super.getEM());
    }

    /**
     * metodo de carrregar todos os subconjuntos pelo nome
     *
     * @param name
     * @return
     */
    public List<Tbsubconjunto> findallTbsubconjuntoByName(String name) {
        return tbsubconjuntoFacade.findallTbconjuntoByName(name, super.getEM());
    }

    /**
     * Metodo para carregar os subconjuntos pelo id
     *
     * @param idSubconjunto
     * @return
     */
    public Tbsubconjunto findTbsubconjuntoById(final Integer idSubconjunto) {
        return tbsubconjuntoFacade.find(idSubconjunto, super.getEM());
    }

    /**
     * Metodo para deletar um subconjunto
     *
     * @param tbsubconjunto
     * @return
     */
    public boolean deleteSubconjunto(Tbsubconjunto tbsubconjunto) {
        return tbsubconjuntoFacade.remove(tbsubconjunto, super.getEM());
    }

    /**
     * Metodo para criar um subconjunto
     *
     * @param tbsubconjunto
     * @return
     */
    public boolean createSubconjunto(final Tbsubconjunto tbsubconjunto) {
        return tbsubconjuntoFacade.create(tbsubconjunto, super.getEM());
    }

    /**
     * Metodo ara editar um subconjunto
     *
     * @param tbsubconjunto
     * @return
     */
    public boolean editSubconjunto(final Tbsubconjunto tbsubconjunto) {
        return tbsubconjuntoFacade.edit(tbsubconjunto, super.getEM());
    }

    public boolean validarCamposSubconjunto(final Tbsubconjunto tbsubconjunto) {
        boolean result = false;

        if (tbsubconjunto != null) {

            if ((tbsubconjunto.getNmsubconjunto() != null && !tbsubconjunto.getNmsubconjunto().trim().isEmpty())) {
                result = true;
            }

        }
        return result;

    }

}

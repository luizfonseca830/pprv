/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.pprv.web.control.logic.menu;

import br.com.pprv.model.daos.TbmenuFacade;
import br.com.pprv.model.entities.Tbmenu;
import br.com.pprv.web.control.module.AbstractModuleCore;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author JorgeFonseca
 */
@Stateless
public class MenuLogic extends AbstractModuleCore {

    @EJB
    private TbmenuFacade tbmenuFacade;

    public boolean editMenu(Tbmenu tbmenu) {
        return tbmenuFacade.edit(tbmenu, getEM());
    }

    public boolean deleteMenu(Tbmenu tbmenu) {
        return tbmenuFacade.remove(tbmenu, getEM());
    }

    public boolean createMenu(Tbmenu tbmenu) {
        return tbmenuFacade.create(tbmenu, getEM());
    }

    public List<Tbmenu> allMenus() {
        return tbmenuFacade.listAllMenu(getEM());
    }

    public Tbmenu findMenuById(int idMenu) {
        return tbmenuFacade.findMenuByIdMenu(idMenu, getEM());
    }

    public List<Tbmenu> listMenusNotUserAccess(int IdUser) {
        return tbmenuFacade.listTbmenuUserNotAccess(IdUser, getEM());
    }

    public Tbmenu findTbmenuByTxurl(String txurl) {
        Tbmenu tbmenu = null;
        try {
            tbmenu = new Tbmenu();
            tbmenu.setTxurl(txurl);
            tbmenu = tbmenuFacade.findTbmenuByTxurl(txurl, getEM());
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }
        return tbmenu;
    }
}

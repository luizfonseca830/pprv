/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.faces.utils;

import br.com.pprv.model.entities.Tbusuario;
import br.com.pprv.web.faces.constants.PagesUrl;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JorgeFonseca
 */
@ManagedBean
@RequestScoped
public class Shareds {

    public final static String VERSION = "1.0.0";
    public final static String USER_TAG = "userSession";
    public final static String PROJECT_DIRECTORY = "/pprv";

    public String getVersion() {
        return VERSION;
    }

    public String getProjectDirectory() {
        return PROJECT_DIRECTORY;
    }

    /**
     * @return the tbusuario
     */
    public static Tbusuario getUser() {
        Tbusuario tbusuario = null;
        try {
            FacesContext ctx = FacesContext.getCurrentInstance();
            ExternalContext ec = ctx.getExternalContext();
            HttpSession session = (HttpSession) ec.getSession(true);
            tbusuario = (Tbusuario) session.getAttribute(USER_TAG);
        } catch (NullPointerException n) {
        }
        return tbusuario;
    }

    public static void setUser(Tbusuario tbusuario) {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.setAttribute(USER_TAG, tbusuario);
    }

    public void logout() {
        Shareds.setUser(null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.invalidate();
        AbstractFacesContextUtils.redirectPage(PagesUrl.URL_LOGIN);
    }

}

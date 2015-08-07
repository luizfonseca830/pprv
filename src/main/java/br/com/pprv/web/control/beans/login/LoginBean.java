/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.beans.login;

import br.com.pprv.model.entities.Tbusuario;
import br.com.pprv.web.control.logic.user.UserLogic;
import br.com.pprv.web.faces.constants.PagesUrl;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import br.com.pprv.web.faces.utils.Shareds;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ioliveira
 */
@ManagedBean
@ViewScoped
public class LoginBean implements Serializable{

    @EJB
    private UserLogic userLogic;

    public String pagina = "";
    private Tbusuario tbusuario;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
        tbusuario = new Tbusuario();
    }

    @PostConstruct
    public void init() {
        if (Shareds.getUser() != null) {
            AbstractFacesContextUtils.redirectPage(PagesUrl.URL_HOME);
        }

        HttpServletRequest origRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String currentPage = origRequest.getRequestURL().toString();

        if (!currentPage.contains(PagesUrl.URL_LOGIN)) {
            try {
                pagina = currentPage.substring(currentPage.indexOf("/faces"));
            } catch (Exception e) {
            }
        }
    }

    public void makeLogin() {
        if (tbusuario.getNmloginusuario().trim().isEmpty() || tbusuario.getNmsenhausuario().trim().isEmpty()) {
            //AbstractFacesContextUtils.addMessageError(Resources.getMessage("usuarioousenhainvalidos"));
            AbstractFacesContextUtils.addMessageError("usuario ou senha invalidos");
        } else {
            tbusuario = userLogic.findTbusuarioByLoginSenha(tbusuario);
            if (tbusuario == null) {
                AbstractFacesContextUtils.addMessageError("usuario ou senha invalidos");
                tbusuario = new Tbusuario();
            } else if (tbusuario.getBolativo()) {
                tbusuario.setTmdataultimoacesso(new Date(System.currentTimeMillis()));
                userLogic.editUsuario(tbusuario);
                Shareds.setUser(tbusuario);

                if (!pagina.isEmpty()) {
                    AbstractFacesContextUtils.redirectPage(pagina);
                } else {
                    AbstractFacesContextUtils.redirectPage(PagesUrl.URL_HOME);
                }
            }
        }
    }

    public void logout() {
        Shareds.setUser(null);
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) ctx.getExternalContext().getSession(false);
        session.invalidate();
        AbstractFacesContextUtils.redirectPage(PagesUrl.URL_LOGIN);
    }

    /**
     * @return the tbusuario
     */
    public Tbusuario getTbusuario() {
        return tbusuario;
    }

    /**
     * @param tbusuario the tbusuario to set
     */
    public void setTbusuario(Tbusuario tbusuario) {
        this.tbusuario = tbusuario;
    }
}

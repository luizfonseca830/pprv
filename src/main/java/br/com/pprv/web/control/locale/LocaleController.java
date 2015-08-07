/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.control.locale;

import br.com.pprv.web.control.beans.menu.MenuBean;
import br.com.pprv.web.faces.utils.AbstractFacesContextUtils;
import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author JorgeFonseca
 */
@ManagedBean
@SessionScoped
public class LocaleController implements Serializable {

    private Locale locale = new Locale("pt", "BR");
    private final static String COOKIE_LOCALE = "COOKIELOCALE";

    public void englishLocale() {
        final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        locale = Locale.US;

        if (viewRoot != null) {
            viewRoot.setLocale(locale);
            AbstractFacesContextUtils.setCookie(LocaleController.COOKIE_LOCALE, "en");
        }

    }

    public void portugueseLocale() {
        UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        locale = new Locale("pt", "BR");
        
        if (viewRoot != null){
            viewRoot.setLocale(locale);
            AbstractFacesContextUtils.setCookie(LocaleController.COOKIE_LOCALE, "pt");
        }
        
    }

    public void changeLanguage(int lang) {
        switch (lang) {
            case 1:
                portugueseLocale();
                break;
            case 2:
                englishLocale();
                break;
            default:
                englishLocale();
                break;
        }

        MenuBean menuBean = (MenuBean) AbstractFacesContextUtils.getSessionValue("menuBean");
        if (menuBean != null) {
            menuBean.getModel().clear();
            menuBean.init();
        }
    }

    public Locale getCurrentLocale() {
        return locale;
    }
}

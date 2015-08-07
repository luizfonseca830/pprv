/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.faces.constants;

import java.util.ResourceBundle;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 *
 * @author ioliveira
 */
public final class Resources {

    private Resources() {
    }
    
    public static final String SMILE_VERDE = "/pprv/javax.faces.resource/images/SmileVerde.gif.jsf";
    public static final String SMILE_VERMELHO = "/pprv/javax.faces.resource/images/SmileVermelho.gif.jsf";
    public static final String SETTINGS_VERDE = "/pprv/javax.faces.resource/images/SettingsVerde.gif.jsf";
    public static final String SETTINGS_VERMELHO = "/pprv/javax.faces.resource/images/SettingsVermelho.gif.jsf";
    public static final String FUNDO_VERMELHO = "/pprv/javax.faces.resource/images/FundoVermelho.png.jsf";
    public static final String FUNDO_VERDE = "/pprv/javax.faces.resource/images/FundoVerde.png.jsf";

    public static final String LOGO_DEFAULT = "/pprv/javax.faces.resource/images/logos/valelogo.png.jsf";

    public static String getField(final String value) {
        String result = "Field not found";
        final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        final ResourceBundle bundle = ResourceBundle.getBundle("/bundle/field", viewRoot.getLocale());
        try {
            result = bundle.getString(value);
        } catch (Exception e) {
        }
        return result;
    }

    public static String getMessage(final String value) {
        String result = "Message not found.";
        final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        final ResourceBundle bundle = ResourceBundle.getBundle("/bundle/message", viewRoot.getLocale());
        try {
            result = bundle.getString(value);
        } catch (Exception e) {
        }
        return result;
    }

    public static ResourceBundle getResourceBundleReport() {
        final UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
        return ResourceBundle.getBundle("/bundle/report", viewRoot.getLocale());
    }
}

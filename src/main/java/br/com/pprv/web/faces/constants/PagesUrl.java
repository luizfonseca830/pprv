/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pprv.web.faces.constants;

/**
 *
 * @author JorgeFonseca
 */
public final class PagesUrl {

    private PagesUrl() {
    }
    public static final String EXT_URL = ".jsf";
    public static final String URL_HOME = "/faces/index" + EXT_URL;
    public static final String URL_LOGIN = "/faces/login" + EXT_URL;
    public static final String URL_USUARIO_LIST = "/faces/cadastrosadministrativos/usuario/usuarioList" + EXT_URL;
    public static final String URL_CONSULTAR_USER = "/faces/cadastrosadministrativos/usuario/consultarUser" + EXT_URL;
    public static final String URL_EDITAR_USER = "/faces/cadastrosadministrativos/usuario/editarUser" + EXT_URL;
    public static final String URL_MENU_LIST = "/faces/cadastrosadministrativos/funcionalidade/menuList" + EXT_URL;
    public static final String URL_MENU_CONSULTA = "/faces/cadastrosadministrativos/funcionalidade/menuConsulta" + EXT_URL;
    public static final String URL_USER_NOT_AUTHORIZED = "/public/error/errorNotAuthorized" + EXT_URL;
    public static final String URL_USER_PADRAO = "/faces/cadastrosadministrativos/usuario/meusDados" + EXT_URL;
    public static final String URL_MODULO_LIST = "/faces/cadastrosadministrativos/modulo/ModuloList" + EXT_URL;
    public static final String URL_PERFIL_LIST = "/faces/cadastrosadministrativos/perfil/PerfilList" + EXT_URL;
}

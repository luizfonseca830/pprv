package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbacesso;
import br.com.pprv.model.entities.Tbmodulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-11T11:13:45")
@StaticMetamodel(Tbmenu.class)
public class Tbmenu_ { 

    public static volatile SingularAttribute<Tbmenu, String> nmmenu;
    public static volatile SingularAttribute<Tbmenu, String> nmmenuEnUs;
    public static volatile SingularAttribute<Tbmenu, Integer> idmenu;
    public static volatile SingularAttribute<Tbmenu, String> txurl;
    public static volatile SingularAttribute<Tbmenu, String> txrole;
    public static volatile SingularAttribute<Tbmenu, Short> insequencia;
    public static volatile ListAttribute<Tbmenu, Tbacesso> tbacessoList;
    public static volatile SingularAttribute<Tbmenu, Tbmodulo> idmodulo;

}
package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbfuncionalidade;
import br.com.pprv.model.entities.Tbmenu;
import br.com.pprv.model.entities.Tbmodulo;
import br.com.pprv.model.entities.Tbusuario;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-10T05:00:40")
@StaticMetamodel(Tbacesso.class)
public class Tbacesso_ { 

    public static volatile SingularAttribute<Tbacesso, Integer> idacesso;
    public static volatile SingularAttribute<Tbacesso, Tbmenu> idmenu;
    public static volatile SingularAttribute<Tbacesso, Tbusuario> idusuario;
    public static volatile SingularAttribute<Tbacesso, Tbfuncionalidade> idfuncionalidade;
    public static volatile SingularAttribute<Tbacesso, Tbmodulo> idmodulo;

}
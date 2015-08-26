package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbacesso;
import br.com.pprv.model.entities.Tbfuncionalidade;
import br.com.pprv.model.entities.Tbmenu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-26T11:48:12")
@StaticMetamodel(Tbmodulo.class)
public class Tbmodulo_ { 

    public static volatile SingularAttribute<Tbmodulo, String> txdescmodulo;
    public static volatile ListAttribute<Tbmodulo, Tbfuncionalidade> tbfuncionalidadeList;
    public static volatile ListAttribute<Tbmodulo, Tbacesso> tbacessoList;
    public static volatile SingularAttribute<Tbmodulo, Integer> idmodulo;
    public static volatile SingularAttribute<Tbmodulo, String> nmmoduloEnUs;
    public static volatile ListAttribute<Tbmodulo, Tbmenu> tbmenuList;
    public static volatile SingularAttribute<Tbmodulo, String> nmmodulo;
    public static volatile SingularAttribute<Tbmodulo, Integer> intipo;

}
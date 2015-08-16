package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbacesso;
import br.com.pprv.model.entities.Tbmodulo;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-16T14:49:14")
@StaticMetamodel(Tbfuncionalidade.class)
public class Tbfuncionalidade_ { 

    public static volatile SingularAttribute<Tbfuncionalidade, String> nmfuncionalidade;
    public static volatile SingularAttribute<Tbfuncionalidade, String> txurl;
    public static volatile SingularAttribute<Tbfuncionalidade, String> txrole;
    public static volatile SingularAttribute<Tbfuncionalidade, Short> insequencia;
    public static volatile ListAttribute<Tbfuncionalidade, Tbacesso> tbacessoList;
    public static volatile SingularAttribute<Tbfuncionalidade, Integer> idfuncionalidade;
    public static volatile SingularAttribute<Tbfuncionalidade, Tbmodulo> idmodulo;

}
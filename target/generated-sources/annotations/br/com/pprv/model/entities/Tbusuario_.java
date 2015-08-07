package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbacesso;
import br.com.pprv.model.entities.Tbperfil;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-07T02:00:54")
@StaticMetamodel(Tbusuario.class)
public class Tbusuario_ { 

    public static volatile SingularAttribute<Tbusuario, String> nmnomeusuario;
    public static volatile SingularAttribute<Tbusuario, Integer> idusuario;
    public static volatile SingularAttribute<Tbusuario, String> nmsenhausuario;
    public static volatile SingularAttribute<Tbusuario, Short> inorigeminfo;
    public static volatile SingularAttribute<Tbusuario, String> nmcracha;
    public static volatile SingularAttribute<Tbusuario, String> nmloginusuario;
    public static volatile ListAttribute<Tbusuario, Tbacesso> tbacessoList;
    public static volatile SingularAttribute<Tbusuario, Date> tmdataultimoacesso;
    public static volatile SingularAttribute<Tbusuario, Boolean> bolativo;
    public static volatile SingularAttribute<Tbusuario, Tbperfil> idperfil;
    public static volatile SingularAttribute<Tbusuario, Short> inenviadaspo;

}
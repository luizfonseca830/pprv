package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbinspecao;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-07T02:00:54")
@StaticMetamodel(Tbgerencia.class)
public class Tbgerencia_ { 

    public static volatile ListAttribute<Tbgerencia, Tblaudo> tblaudoList;
    public static volatile ListAttribute<Tbgerencia, Tbtecnica> tbtecnicaList;
    public static volatile SingularAttribute<Tbgerencia, Tbinspecao> idinspecao;
    public static volatile SingularAttribute<Tbgerencia, Integer> idgerencia;
    public static volatile SingularAttribute<Tbgerencia, String> nmgerencia;

}
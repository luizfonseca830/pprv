package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tblaudo;
import br.com.pprv.model.entities.Tbtecnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-26T11:48:12")
@StaticMetamodel(Tbsubconjunto.class)
public class Tbsubconjunto_ { 

    public static volatile ListAttribute<Tbsubconjunto, Tblaudo> tblaudoList;
    public static volatile ListAttribute<Tbsubconjunto, Tbtecnica> tbtecnicaList;
    public static volatile SingularAttribute<Tbsubconjunto, String> nmsubconjunto;
    public static volatile SingularAttribute<Tbsubconjunto, Integer> idsubconjunto;
    public static volatile ListAttribute<Tbsubconjunto, Tbequipamento> tbequipamentoList;
    public static volatile SingularAttribute<Tbsubconjunto, Integer> condicao;
    public static volatile SingularAttribute<Tbsubconjunto, Tbequipamento> idequipamento;

}
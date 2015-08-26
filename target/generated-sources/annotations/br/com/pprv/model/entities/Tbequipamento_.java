package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbinspecao;
import br.com.pprv.model.entities.Tbsubconjunto;
import br.com.pprv.model.entities.Tbtecnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-26T11:48:12")
@StaticMetamodel(Tbequipamento.class)
public class Tbequipamento_ { 

    public static volatile SingularAttribute<Tbequipamento, Integer> criticidade;
    public static volatile SingularAttribute<Tbequipamento, String> nmequipamenta;
    public static volatile SingularAttribute<Tbequipamento, Tbinspecao> idinspecao;
    public static volatile SingularAttribute<Tbequipamento, String> descequipamento;
    public static volatile SingularAttribute<Tbequipamento, Tbsubconjunto> idsubconjunto;
    public static volatile SingularAttribute<Tbequipamento, Integer> condicao;
    public static volatile SingularAttribute<Tbequipamento, Integer> idequipamento;
    public static volatile SingularAttribute<Tbequipamento, Tbtecnica> idtecnica;

}
package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbinspecao;
import br.com.pprv.model.entities.Tbsubconjunto;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-16T14:49:14")
@StaticMetamodel(Tbtecnica.class)
public class Tbtecnica_ { 

    public static volatile SingularAttribute<Tbtecnica, String> nmtecnica;
    public static volatile SingularAttribute<Tbtecnica, Tbinspecao> idinspecao;
    public static volatile SingularAttribute<Tbtecnica, Tbsubconjunto> idsubconjunto;
    public static volatile SingularAttribute<Tbtecnica, Tbgerencia> idgerencia;
    public static volatile SingularAttribute<Tbtecnica, Tbequipamento> idequipamento;
    public static volatile SingularAttribute<Tbtecnica, Integer> idtecnica;

}
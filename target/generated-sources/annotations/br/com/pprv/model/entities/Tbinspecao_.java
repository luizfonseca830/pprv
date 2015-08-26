package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbtecnica;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-26T14:02:24")
@StaticMetamodel(Tbinspecao.class)
public class Tbinspecao_ { 

    public static volatile ListAttribute<Tbinspecao, Tbtecnica> tbtecnicaList;
    public static volatile SingularAttribute<Tbinspecao, Integer> idinspecao;
    public static volatile ListAttribute<Tbinspecao, Tbequipamento> tbequipamentoList;
    public static volatile SingularAttribute<Tbinspecao, String> nminspecao;
    public static volatile ListAttribute<Tbinspecao, Tbgerencia> tbgerenciaList;

}
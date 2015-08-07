package br.com.pprv.model.entities;

import br.com.pprv.model.entities.Tbequipamento;
import br.com.pprv.model.entities.Tbgerencia;
import br.com.pprv.model.entities.Tbsubconjunto;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-08-07T02:00:54")
@StaticMetamodel(Tblaudo.class)
public class Tblaudo_ { 

    public static volatile SingularAttribute<Tblaudo, String> ordemmanutencao;
    public static volatile SingularAttribute<Tblaudo, String> status;
    public static volatile SingularAttribute<Tblaudo, Date> limiteexecucao;
    public static volatile SingularAttribute<Tblaudo, Tbsubconjunto> idsubconjunto;
    public static volatile SingularAttribute<Tblaudo, Tbgerencia> idgerencia;
    public static volatile SingularAttribute<Tblaudo, String> diagnostico;
    public static volatile SingularAttribute<Tblaudo, Date> datacadastro;
    public static volatile SingularAttribute<Tblaudo, Tbequipamento> idequipamento;
    public static volatile SingularAttribute<Tblaudo, Date> dataanalise;
    public static volatile SingularAttribute<Tblaudo, Integer> idlaudo;

}
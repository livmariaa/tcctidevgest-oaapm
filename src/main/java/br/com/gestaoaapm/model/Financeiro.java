package br.com.gestaoaapm.model;


import br.com.gestaoaapm.enums.EnumFormaPagamento;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "tipoMovimentacao",
        length = 1,
        discriminatorType = DiscriminatorType.STRING
)
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Basic
    @Temporal(TemporalType.DATE)
    private Date dataPagamento;

    private String observacao;


    @ManyToOne
    private Produto produto;

    @ManyToOne
    private Pessoa nomePagou;

    private String nomeRecebeu;

    private EnumFormaPagamento formaPagamento;



}
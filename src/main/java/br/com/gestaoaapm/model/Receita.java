package br.com.gestaoaapm.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id"
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "C")
public class Receita extends Financeiro{


    @ManyToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    private Double valorReceita;



    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "produto_receita",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "receita_id")
    )
    private List<Produto> produtos = new ArrayList<>();

    // método para adicionar um produto na lista de produtos
    public void addProduto(Produto produto){
        this.produtos.add(produto);
    }

    // método para remover um produto da lista de produtos
    public void removeProduto(Produto produto){
        this.produtos.remove(produto);
    }

    // método para calcular o valor da receita pelo somatório dos valores dos produtos
    public String calcularValorReceita(){

        double valorReceita = 0;
        for (Produto produto : this.produtos){
            valorReceita += produto.getPreco();
        }

        // retorna no formato de duas casas decimais
        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(valorReceita);
    }

}
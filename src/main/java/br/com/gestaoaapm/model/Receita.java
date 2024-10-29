package br.com.gestaoaapm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@DiscriminatorValue(value = "C")
public class Receita extends Financeiro{


    @ManyToOne(cascade = CascadeType.ALL)
    private Pessoa pessoa;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "receitas")
    private List<Produto> produtos = new ArrayList<>();

    // método para adicionar um produto na lista de produtos
    public void addProduto(Produto produto){
        this.produtos.add(produto);
    }

    // método para remover um produto da lista de produtos
    public void removeProduto(Produto produto){
        this.produtos.remove(produto);
    }

}
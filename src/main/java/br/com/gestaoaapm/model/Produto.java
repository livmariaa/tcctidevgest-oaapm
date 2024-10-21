package br.com.gestaoaapm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O nome deve ser preenchido")
    private String nome;
    @NotNull(message = "O preço deve ser preenchido")
    private Double preco;
    @NotEmpty(message = "O status deve ser preenchido")
    private String status;

    // relação manytomany com o model Receita
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "produto_receita",
            joinColumns = @JoinColumn(name = "produto_id"),
            inverseJoinColumns = @JoinColumn(name = "receita_id")
    )
    private List<Receita> receitas;

}

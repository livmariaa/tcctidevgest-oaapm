package br.com.gestaoaapm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @NotEmpty(message = "O nome deve ser preenchido")
    private String nome;

    private String descricao;
}

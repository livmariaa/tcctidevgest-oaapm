package br.com.gestaoaapm.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;




@Entity
@DiscriminatorValue(value = "A")
public class Aluno extends Pessoa{
    private String turma;
}
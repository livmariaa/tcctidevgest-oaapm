package br.com.gestaoaapm.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("D")
public class Despesa extends Financeiro {
}

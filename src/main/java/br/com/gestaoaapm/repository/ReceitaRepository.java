package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {
}

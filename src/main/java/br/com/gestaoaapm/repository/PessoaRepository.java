package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}

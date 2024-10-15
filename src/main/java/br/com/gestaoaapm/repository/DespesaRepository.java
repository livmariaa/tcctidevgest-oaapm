package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}

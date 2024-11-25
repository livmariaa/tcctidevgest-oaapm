package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
}

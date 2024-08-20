package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}

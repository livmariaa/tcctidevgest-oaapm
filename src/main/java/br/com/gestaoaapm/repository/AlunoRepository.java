package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    List<Aluno> findByNomeContainingIgnoreCase(String nome);

    Aluno findByEmail(String email);

    Aluno findByCpf(String cpf);

    Optional<Aluno> findByEmailOrCpf(String email, String cpf);


    // Pesquisa o email e o id seja diferente
    // do id que está sendo alterado
    Aluno findByEmailAndIdNot(String email, Long id);

    // Pesquisa o cpf e o id seja diferente
    // do id que está sendo alterado
    Aluno findByCpfAndIdNot(String cpf, Long id);


    /*//Pesquisa todos os alunos e retona o id, nome, matricula e cpf
    @Query("select id, nome, cpf, email, enderecoPessoa.id from Aluno")
    List<Object[]> buscarAlunosParaApp();*/
}


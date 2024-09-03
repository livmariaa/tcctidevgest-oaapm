package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Aluno;
import br.com.gestaoaapm.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
    List<Funcionario> findByNomeContainingIgnoreCase(String nome);

    Funcionario findByEmail(String email);

    Funcionario findByCpf(String cpf);

    Optional<Funcionario> findByEmailOrCpf(String email, String cpf);


    // Pesquisa o email e o id seja diferente
    // do id que está sendo alterado
    Funcionario findByEmailAndIdNot(String email, Long id);

    // Pesquisa o cpf e o id seja diferente
    // do id que está sendo alterado
    Funcionario findByCpfAndIdNot(String cpf, Long id);


    /*//Pesquisa todos os Funcionarios e retona o id, nome, matricula e cpf
    @Query("select id, nome, cpf, email, enderecoPessoa.id from Funcionarios")
    List<Object[]> buscarFuncionariosParaApp();*/
}


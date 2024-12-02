package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query(value = "SELECT SUM(p.preco) FROM produto_receita pr INNER JOIN produto p ON pr.produto_id = p.id", nativeQuery = true)
    Double somaValorReceitas();

    // Native Query para somar os dos produtos de uma receita
    @Query(value = "SELECT SUM(p.preco) FROM produto_receita pr INNER JOIN produto p ON pr.produto_id = p.id WHERE pr.receita_id = ?1", nativeQuery = true)
    Double somaValorReceitas(Long id);

    @Query(value = "CALL SomarReceitasPorMes()", nativeQuery = true)
    List<Object[]> somarReceitasPorMes();

   //usado no FinanceiroController
    List<Receita> findByDataPagamentoBetween(Date dataInicial, Date dataFinal);

}

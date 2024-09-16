package br.com.gestaoaapm.repository;

import br.com.gestaoaapm.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}

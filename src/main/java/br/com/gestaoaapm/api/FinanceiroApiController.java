package br.com.gestaoaapm.api;

import br.com.gestaoaapm.model.Receita;
import br.com.gestaoaapm.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/financeiro")
public class FinanceiroApiController {


    @Autowired
    private ReceitaRepository receitaRepository;

    @GetMapping("/receita/somar-receitas-por-mes")
    public List<Object[]> somarReceitasPorMes(){
        return receitaRepository.somarReceitasPorMes();
    }

    @GetMapping("/receita/listar")
    public List<Receita> listarReceitas(){
        return receitaRepository.findAll();
    }

    @PostMapping("/receita/inserir")
    public void inserirReceita(@RequestBody Receita receita){
        receitaRepository.save(receita);
    }

    @PostMapping("/receita/inserir-varios")
    public void inserirVarios(@RequestBody List<Receita> receitas) {
        receitaRepository.saveAll(receitas);
    }

}

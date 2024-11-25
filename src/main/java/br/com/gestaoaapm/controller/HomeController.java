package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Produto;
import br.com.gestaoaapm.model.Receita;
import br.com.gestaoaapm.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    ReceitaRepository receitaRepository;

    @GetMapping
    public String home(Model model){

        // Quantidade de receitas cadastradas
        long qtdReceitas = receitaRepository.count();
        model.addAttribute("qtdReceitas", qtdReceitas);

        // Valor de todas as receitas cadastradas
        model.addAttribute("valorTotalReceitas", somaValorReceitas());

        model.addAttribute("receitas", receitaRepository.findAll());

        return "home/index";
    }

    @GetMapping("/creditos")
    public String creditos(){
        return "home/creditos";
    }

    // somar o valor de todas as receitas cadastradas
    public String somaValorReceitas(){
        List<Receita> receitas = receitaRepository.findAll();
        double soma = 0;
        for (Receita receita : receitas) {

            // percorre os produtos da receita
            for (Produto produto : receita.getProdutos()) {
                soma += produto.getPreco();
            }

        }

        Locale locale = new Locale("pt", "BR");
        return NumberFormat.getCurrencyInstance(locale).format(soma);
    }

}
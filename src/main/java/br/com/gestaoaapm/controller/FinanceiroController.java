package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Despesa;
import br.com.gestaoaapm.model.Produto;
import br.com.gestaoaapm.model.Receita;
import br.com.gestaoaapm.repository.DespesaRepository;
import br.com.gestaoaapm.repository.PessoaRepository;
import br.com.gestaoaapm.repository.ProdutoRepository;
import br.com.gestaoaapm.repository.ReceitaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/financeiro")
public class FinanceiroController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ReceitaRepository receitaRepository;

    @Autowired
    private DespesaRepository despesaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;



    @GetMapping("/receita")
    public String receita(Model model) {
        model.addAttribute("receitas", receitaRepository.findAll());
        return "financeiro/receita/index";
    }

    @GetMapping("/despesa")
    public String despesa(Model model) {
        model.addAttribute("despesas", despesaRepository.findAll());
        return "financeiro/despesa/index";
    }


    // Carrega o form-inserir.html
    @GetMapping("/receita/form-inserir")
    public String receitaFormInserir(Model model) {
        model.addAttribute("pessoas", pessoaRepository.findAll());
        model.addAttribute("receita", new Receita());
        return "financeiro/receita/form-inserir";
    }

    @GetMapping("/despesa/form-inserir")
    public String despesaFormInserir(Model model) {
        model.addAttribute("despesa", new Despesa());
        return "financeiro/despesa/form-inserir";
    }

    // despesa/form-alterar.html
    @GetMapping("/receita/form-alterar/{id}")
    public String receitaFormAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Receita receita = receitaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("receita", receita);

        model.addAttribute("pessoas", pessoaRepository.findAll());

        // Adiciona a lista de produtos no objeto model
        model.addAttribute("produtosbd", produtoRepository.findAll());

        // Retorna o template aluno/alterar.html
        return "financeiro/receita/form-alterar";
    }

    // despesa/form-alterar.html
    @GetMapping("/despesa/form-alterar/{id}")
    public String despesaFormAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Despesa despesa = despesaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("despesa", despesa);

        // Retorna o template aluno/alterar.html
        return "financeiro/despesa/form-alterar";
    }


    // Salvar a receita
    @PostMapping("/receita/salvar")
    public String receitaSalvar(
            @Valid Receita receita,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "financeiro/receita/form-inserir";
        }



        int i = 0;
        // Verifica se os produtos foram selecionados
        if (!receita.getProdutos().isEmpty()) {
            // Percorre a lista de produtos selecionados
            for (Produto produto : receita.getProdutos()) {
                // Busca o produto no banco de dados
                Produto produtoBanco = produtoRepository.findById(produto.getId()).orElseThrow(()
                        -> new IllegalArgumentException("ID inválido"));
                // Adiciona o produto na receita
                produtoBanco.addReceita(receita);

                // Adiciona o produto na lista de produtos da receita
                receita.getProdutos().set(i, produtoBanco);
                i++;
            }
        }

        receitaRepository.save(receita);


        redirectAttributes.addFlashAttribute("mensagem", "Receita salva com sucesso!");
        return "redirect:/financeiro/receita";
    }

    // Salvar a despesa
    @PostMapping("/despesa/salvar")
    public String despesaSalvar(
            @Valid Despesa despesa,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "financeiro/despesa/form-inserir";
        }

        despesaRepository.save(despesa);

        redirectAttributes.addFlashAttribute("mensagem", "Despesa salva com sucesso!");
        return "redirect:/financeiro/despesa";
    }


    // Excluir a receita
    @GetMapping("/receita/excluir/{id}")
    public String receitaExcluir(@PathVariable("id") Long id,
                                 RedirectAttributes attributes) {

        // Busca o aluno no banco de dados
        Receita receita = receitaRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o aluno do banco de dados
        receitaRepository.delete(receita);

        return "redirect:/financeiro/receita";
    }

    // Excluir a despesa
    @GetMapping("/despesa/excluir/{id}")
    public String despesaExcluir(@PathVariable("id") Long id,
                                 RedirectAttributes attributes) {

        // Busca o aluno no banco de dados
        Despesa despesa = despesaRepository.findById(id).orElseThrow(()
                -> new IllegalArgumentException("ID inválido"));

        // Exclui o aluno do banco de dados
        despesaRepository.delete(despesa);

        return "redirect:/financeiro/despesa";
    }

    // método para adicionar produtos em uma receita
    @PostMapping("/receita/add-produto")
    public String addProduto(Receita receita, Model model) {

        model.addAttribute("produtosbd", produtoRepository.findAll());
        receita.addProduto(new Produto());

        return "financeiro/receita/form-inserir :: produtos";
    }

    // método para remover produtos em uma receita
    @PostMapping("/receita/remove-produto")
    public String removeProduto(Receita receita, @RequestParam("removeDynamicRowProduto") Integer produtoIndex, Model model) {

        model.addAttribute("produtosbd", produtoRepository.findAll());

        receita.getProdutos().remove(produtoIndex.intValue());

        return "financeiro/receita/form-inserir :: produtos";
    }

    /*
    $(document).on('change', '.select-produtos', function () {
        let idProduto = $(this).val();
        let linha = $(this).closest('tr');
        let valorUnitario = linha.find('#valorUnitario');
        let totalLinha = linha.find('#totalLinha');
        $.ajax({
            url: '/financeiro/receita/valor-unitario',
            type: 'GET',
            data: {idProduto: idProduto},
            success: function (data) {
                valorUnitario.text(data);
                totalLinha.text(data);
            }
        });
    });



     */

    @GetMapping("/receita/valor-unitario")
    @ResponseBody
    public Double valorUnitario(@RequestParam("idProduto") Long idProduto) {
        Produto produto = produtoRepository.findById(idProduto).orElseThrow(() -> new IllegalArgumentException("ID inválido"));
        return produto.getPreco();
    }
}
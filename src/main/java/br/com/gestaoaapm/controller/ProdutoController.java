package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Aluno;
import br.com.gestaoaapm.model.Produto;
import br.com.gestaoaapm.repository.AlunoRepository;
import br.com.gestaoaapm.repository.ProdutoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public String index(Model model) {
        List<Produto> listaProdutos = produtoRepository.findAll();
        model.addAttribute("produtos", listaProdutos);
        return "produto/index";
    }

    // Carrega o form-inserir.html
    @GetMapping("/form-inserir")
    public String formInserir(Model model) {

        model.addAttribute("produto", new Produto());
        return "produto/form-inserir";
    }

    // Método para salvar o produto
    @PostMapping("/salvar")
    public String salvar(
            @Valid Produto produto,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "produto/form-inserir";
        }

        produtoRepository.save(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto salvo com sucesso!");
        return "redirect:/produto";
    }

    //Formulário de alteração de produto
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("produto", produto);

        // Retorna o template aluno/alterar.html
        return "produto/form-alterar";
    }


    //Método que é invocado ao clicar no botão "Salvar" do template aluno/form-alterar.html
    @PostMapping("/alterarSalvar")
    public String alterar( @Valid Produto produto,
                           BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template produto/form-alterar.html
        if (result.hasErrors()) {
            return "produto/form-alterar";
        }

        produtoRepository.save(produto);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Produto atualizado com sucesso!");

        // Redireciona para a página de listagem de produtos
        return "redirect:/produto";
    }

    // Método para excluir o produto
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Produto inválido: " + id));
        produtoRepository.delete(produto);
        redirectAttributes.addFlashAttribute("mensagem", "Produto excluído com sucesso!");
        return "redirect:/produto";
    }



}

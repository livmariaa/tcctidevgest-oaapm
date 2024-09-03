package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Aluno;
import br.com.gestaoaapm.model.Funcionario;
import br.com.gestaoaapm.repository.AlunoRepository;
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
@RequestMapping("/aluno")
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    @GetMapping
    public String index(Model model) {
        List<Aluno> listaAlunos = alunoRepository.findAll();
        model.addAttribute("alunos", listaAlunos);
        return "aluno/index";
    }


    // Carrega o form-inserir.html
    @GetMapping("/form-inserir")
    public String formInserir(Model model) {

        model.addAttribute("aluno", new Aluno());
        return "aluno/form-inserir";
    }

    // Método para salvar o jogador
    @PostMapping("/salvar")
    public String salvar(
            @Valid Aluno aluno,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "aluno/form-inserir";
        }

        alunoRepository.save(aluno);
        redirectAttributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
        return "redirect:/aluno";
    }


    //Formulário de alteração de aluno
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("aluno", aluno);

        // Retorna o template aluno/alterar.html
        return "aluno/form-alterar";
    }


    //Método que é invocado ao clicar no botão "Salvar" do template aluno/form-alterar.html
    @PostMapping("/alterarSalvar")
    public String alterar( @Valid Aluno aluno,
                           BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template aluno/form-alterar.html
        if (result.hasErrors()) {
            return "aluno/form-alterar";
        }

        alunoRepository.save(aluno);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Aluno atualizado com sucesso!");

        // Redireciona para a página de listagem de alunos
        return "redirect:/aluno";
    }


    // Método para excluir o jogador
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno inválido: " + id));
        alunoRepository.delete(aluno);
        redirectAttributes.addFlashAttribute("mensagem", "Aluno excluído com sucesso!");
        return "redirect:/aluno";
    }

}




package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Aluno;
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


    // Formulário de Alteração dos alunos
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model){

        Aluno aluno = alunoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Aluno inválido: " + id));

        model.addAttribute("aluno", aluno);
        return "aluno/form-alterar";
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




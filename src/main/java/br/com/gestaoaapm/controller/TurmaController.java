package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Produto;
import br.com.gestaoaapm.model.Turma;
import br.com.gestaoaapm.repository.TurmaRepository;
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
@RequestMapping("/turma")
public class TurmaController {

    @Autowired
    private TurmaRepository turmaRepository;

    @GetMapping
    public String index(Model model) {
        List<Turma> listaTurmas = turmaRepository.findAll();
        model.addAttribute("turmas", listaTurmas);
        return "turma/index";
    }

    // Carrega o form-inserir.html
    @GetMapping("/form-inserir")
    public String formInserir(Model model) {

        model.addAttribute("turma", new Turma());
        return "turma/form-inserir";
    }

    // Método para salvar o turma
    @PostMapping("/salvar")
    public String salvar(
            @Valid Turma turma,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "turma/form-inserir";
        }

        turmaRepository.save(turma);
        redirectAttributes.addFlashAttribute("mensagem", "Turma salva com sucesso!");
        return "redirect:/turma";
    }

    //Formulário de alteração de turma
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("turma", turma);

        // Retorna o template aluno/alterar.html
        return "turma/form-alterar";
    }


    //Método que é invocado ao clicar no botão "Salvar" do template aluno/form-alterar.html
    @PostMapping("/alterarSalvar")
    public String alterar( @Valid Turma turma,
                           BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template produto/form-alterar.html
        if (result.hasErrors()) {
            return "turma/form-alterar";
        }

        turmaRepository.save(turma);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Turma atualizada com sucesso!");

        // Redireciona para a página de listagem de produtos
        return "redirect:/turma";
    }

    // Método para excluir a turma
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Turma turma = turmaRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Turma inválida: " + id));
        turmaRepository.delete(turma);
        redirectAttributes.addFlashAttribute("mensagem", "Turma excluída com sucesso!");
        return "redirect:/turma";
    }





}

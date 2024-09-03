package br.com.gestaoaapm.controller;

import br.com.gestaoaapm.model.Funcionario;
import br.com.gestaoaapm.repository.FuncionarioRepository;
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
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @GetMapping
    public String index(Model model) {
        List<Funcionario> listaFuncionarios = funcionarioRepository.findAll();
        model.addAttribute("funcionarios", listaFuncionarios);
        return "funcionario/index";
    }

    // Carrega o form-inserir.html
    @GetMapping("/form-inserir")
    public String formInserir(Model model) {

        model.addAttribute("funcionario", new Funcionario());
        return "funcionario/form-inserir";
    }

    // Método para salvar o funcionario
    @PostMapping("/salvar")
    public String salvar(
            @Valid Funcionario funcionario,
            BindingResult result,
            RedirectAttributes redirectAttributes) {

        // Verifica se há erros de validação
        if (result.hasErrors()) {
            return "funcionario/form-inserir";
        }

        funcionarioRepository.save(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário salvo com sucesso!");
        return "redirect:/funcionario";
    }

    //Formulário de alteração de aluno
    @GetMapping("/form-alterar/{id}")
    public String formAlterar(@PathVariable("id") Long id, Model model) {

        // Busca o aluno no banco de dados
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("ID inválido"));

        // Adiciona o aluno no objeto model para ser alterado
        model.addAttribute("funcionario", funcionario);

        // Retorna o template aluno/alterar.html
        return "funcionario/form-alterar";
    }


     //Método que é invocado ao clicar no botão "Salvar" do template funcionario/form-alterar.html
    @PostMapping("/alterarSalvar")
    public String alterar( @Valid Funcionario funcionario,
                          BindingResult result, RedirectAttributes attributes) {

        // Se houver erro de validação, retorna para o template funcionario/form-alterar.html
        if (result.hasErrors()) {
            return "funcionario/form-alterar";
        }

        funcionarioRepository.save(funcionario);

        // Adiciona uma mensagem que será exibida no template
        attributes.addFlashAttribute("mensagem",
                "Funcionário atualizado com sucesso!");

        // Redireciona para a página de listagem de funcionários
        return "redirect:/funcionario";
    }

    // Método para excluir o funcionario
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id, RedirectAttributes redirectAttributes){
        Funcionario funcionario = funcionarioRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Funcionário inválido: " + id));
        funcionarioRepository.delete(funcionario);
        redirectAttributes.addFlashAttribute("mensagem", "Funcionário excluído com sucesso!");
        return "redirect:/funcionario";
    }
}

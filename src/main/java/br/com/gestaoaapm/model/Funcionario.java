package br.com.gestaoaapm.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = "O nome deve ser preenchido")
    private String nome;
    private String telefone;
    @NotEmpty(message = "O email deve ser preenchido")
    @Email(message = "O email deve ser valido")
    private String email;

    @CPF(message = "O cpf deve ser valido")
    private String cpf;
    private String endereco;
    private String cargo;


    //getters and setters

    /*public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotEmpty(message = "O nome deve ser preenchido") String getNome() {
        return nome;
    }

    public void setNome(@NotEmpty(message = "O nome deve ser preenchido") String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public @NotEmpty(message = "O email deve ser preenchido") @Email(message = "O email deve ser valido") String getEmail() {
        return email;
    }

    public void setEmail(@NotEmpty(message = "O email deve ser preenchido") @Email(message = "O email deve ser valido") String email) {
        this.email = email;
    }

    public @CPF(message = "O cpf deve ser valido") String getCpf() {
        return cpf;
    }

    public void setCpf(@CPF(message = "O cpf deve ser valido") String cpf) {
        this.cpf = cpf;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }*/
}

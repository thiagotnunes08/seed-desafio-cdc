package br.com.deveficiente.novoautor.autor;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoAutorRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    private String email;
    @Length(max = 400)
    @NotBlank
    private String descricao;


    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    public Autor toModel() {
        return new Autor(nome,email,descricao);
    }
}

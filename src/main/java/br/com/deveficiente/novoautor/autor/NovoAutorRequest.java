package br.com.deveficiente.novoautor.autor;

import br.com.deveficiente.novoautor.compartilhado.CampoUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class NovoAutorRequest {

    @NotBlank
    private String nome;
    @NotBlank
    @Email
    @CampoUnico(fieldName = "email",domainClass = Autor.class,message = "campo email deve ser único")
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
        return new Autor(nome, email, descricao);
    }

    public NovoAutorRequest(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }
}

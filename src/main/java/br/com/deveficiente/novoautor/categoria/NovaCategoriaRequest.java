package br.com.deveficiente.novoautor.categoria;

import br.com.deveficiente.novoautor.compartilhado.CampoUnico;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

public class NovaCategoriaRequest {

    @NotBlank
    @CampoUnico(domainClass = Categoria.class,fieldName = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public Categoria toModel() {
        return new Categoria(nome);
    }

    public NovaCategoriaRequest(String nome) {
        this.nome = nome;
    }

    /**
     * construtor vazio exclusivo do jackson
     */
    public NovaCategoriaRequest() {
    }
}

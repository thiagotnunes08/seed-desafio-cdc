package br.com.deveficiente.novoautor.pais;

import br.com.deveficiente.novoautor.compartilhado.CampoUnico;

import javax.validation.constraints.NotBlank;

public class NovoPaisRequest {
    @NotBlank
    @CampoUnico(message = "País já existente no sistema!",domainClass = Pais.class,fieldName = "nome")
    private String nome;

    public String getNome() {
        return nome;
    }

    public Pais toModel() {
        return new Pais(nome);
    }

    public NovoPaisRequest(String nome) {
        this.nome = nome;
    }

    /**
     * @Deprecated: uso exclusivo do jackson
     */
    @Deprecated
    public NovoPaisRequest() {
    }
}

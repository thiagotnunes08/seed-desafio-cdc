package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.Cliente;

public class ClienteRequest {

    private String nome;
    private String email;
    private String endereco;

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    public Cliente toModel () {
        return new Cliente(nome,email,endereco);
    }
}

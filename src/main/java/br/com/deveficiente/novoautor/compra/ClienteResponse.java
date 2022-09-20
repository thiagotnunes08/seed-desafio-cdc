package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.Cliente;

public class ClienteResponse {

    private String nome;
    private String email;
    private String endereco;

    public ClienteResponse(Cliente cliente) {
        this.nome = cliente.getNome();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }
}

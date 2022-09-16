package br.com.deveficiente.novoautor.autor;

public class AutorResponse {

    private String nome;
    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public AutorResponse(Autor autor) {
        this.nome = autor.getNome();
        this.descricao = autor.getDescricao();


    }
}

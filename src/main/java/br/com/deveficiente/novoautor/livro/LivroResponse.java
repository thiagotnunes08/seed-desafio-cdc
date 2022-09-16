package br.com.deveficiente.novoautor.livro;

public class LivroResponse {

    private Long id;
    private String nome;

    public LivroResponse(Livro livro) {
        this.id = livro.getId();
        this.nome = livro.getTitulo();
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}

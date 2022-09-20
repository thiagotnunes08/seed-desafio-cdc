package br.com.deveficiente.novoautor.compra;

public class ItensResponse {

    private Long idLivro;
    private Integer quantidade;

    public ItensResponse(Item item) {
        this.idLivro = item.getIdLivro();
        this.quantidade = item.getQuantidade();
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}

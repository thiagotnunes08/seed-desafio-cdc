package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.livro.Livro;
import br.com.deveficiente.novoautor.livro.LivroResponse;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;

public class ItemResponse {

    private LivroResponse livro;
    private int quantidade;
    private BigDecimal precoMomento;

    public ItemResponse(Item item) {
        this.livro = new LivroResponse(item.getLivro());
        this.quantidade = item.getQuantidade();
        this.precoMomento = item.getPrecoMomento();
    }

    public LivroResponse getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoMomento() {
        return precoMomento;
    }
}

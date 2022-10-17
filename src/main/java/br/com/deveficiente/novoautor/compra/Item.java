package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.livro.Livro;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class Item {

    @ManyToOne(optional = false)
    private Livro livro;
    @Positive
    private int quantidade;
    @Positive
    private  BigDecimal precoMomento;

    public Item(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    @Deprecated
    public Item() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return livro.equals(item.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }

    public BigDecimal total(){
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    @Override
    public String toString() {
        return "Item{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }

    public Livro getLivro() {
        return livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public BigDecimal getPrecoMomento() {
        return precoMomento;
    }


}

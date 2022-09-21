package br.com.deveficiente.novoautor.compra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long livroId;
    private int quantidade;

    public Item(Long livroId, int quantidade) {
        this.livroId = livroId;
        this.quantidade = quantidade;
    }

    @Deprecated
    public Item() {
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", livroId=" + livroId +
                ", quantidade=" + quantidade +
                '}';
    }
}

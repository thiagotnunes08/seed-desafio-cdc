package br.com.deveficiente.novoautor.compra;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal total;
    @OneToMany
    private List<Item> itens;

    public Pedido(BigDecimal total, List<Item> itens) {
        this.total = total;
        this.itens = itens;
    }

    @Deprecated
    public Pedido() {
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", total=" + total +
                ", itens=" + itens +
                '}';
    }
}

package br.com.deveficiente.novoautor.compra;

import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Compra compra;

    @ElementCollection
    private Set<Item> itens = new HashSet<>();

    public Pedido(Compra compra, Set<Item> itens) {
        this.compra = compra;
        Assert.isTrue(!itens.isEmpty(),"Todo pedido deve ter pelo menos um item!");
        this.itens.addAll(itens);
    }

    @Deprecated
    public Pedido() {
    }

    public boolean totalIgual(BigDecimal total) {

      BigDecimal totalPedido =  itens
              .stream()
              .map(Item::total)
              .reduce(BigDecimal.ZERO, BigDecimal::add)
              .setScale(0, RoundingMode.UP);


      return totalPedido.equals(total);

    }

    @Override
    public String toString() {
        return "Pedido{" +
                "id=" + id +
                ", itens=" + itens +
                '}';
    }

    public Long getId() {
        return id;
    }

    public Set<Item> getItens() {
        return itens;
    }
}

package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.Cliente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "compra_id")
    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE})
    private List<Item> itens = new ArrayList<>();
    @ManyToOne
    private Cliente cliente;

    public Compra(List<Item> itens, Cliente cliente) {
        this.itens = itens;
        this.cliente = cliente;
    }

    @Deprecated // uso hibernate!
    public Compra() {
    }

    public List<Item> getItens() {
        return itens;
    }

    public Cliente getCliente() {
        return cliente;
    }
}

package br.com.deveficiente.novoautor.compra;

import javax.persistence.ElementCollection;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class PedidoResponse {


    private Long id;

    private Set<ItemResponse> itens = new HashSet<>();

    public PedidoResponse(Pedido pedido) {

        this.id = pedido.getId();
        this.itens = pedido.getItens().stream().map(ItemResponse::new).collect(Collectors.toSet());
    }

    public Long getId() {
        return id;
    }

    public Set<ItemResponse> getItens() {
        return itens;
    }
}

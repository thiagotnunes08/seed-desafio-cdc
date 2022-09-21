package br.com.deveficiente.novoautor.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

public class NovoPedidoRequest {
    @NotNull
    @Positive
    private BigDecimal total;
    @Size(min = 1)
    private List<ItensRequest> itens;

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItensRequest> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        return "NovoPedidoRequest{" +
                "total=" + total +
                ", itens=" + itens +
                '}';
    }

    public Pedido toModel() {

        List<Item> listItens = this.itens
                .stream()
                .map(itensRequest -> new Item(itensRequest.getIdLivro(), itensRequest.getQuantidade())).toList();

        return new Pedido(total,listItens);
    }
}

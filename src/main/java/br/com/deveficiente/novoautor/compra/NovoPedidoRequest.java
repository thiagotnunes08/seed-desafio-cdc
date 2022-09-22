package br.com.deveficiente.novoautor.compra;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoPedidoRequest {
    @NotNull
    @Positive
    private BigDecimal total;
    @Size(min = 1)
    private List<ItensRequest> itens;

    public Function<Compra, Pedido> toModel(EntityManager manager) {

        Set<Item> itensCalculados = this.itens
                .stream()
                .map(itensRequest -> itensRequest.toModel(manager))
                .collect(Collectors.toSet());

        return (compra) -> {

            Pedido pedido = new Pedido(compra, itensCalculados);

            Assert.isTrue(pedido.totalIgual(total), "o total enviado não corresponde ao total real!");
            return pedido;

        };

    }

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


}

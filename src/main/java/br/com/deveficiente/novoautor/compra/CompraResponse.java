package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.Cliente;

import java.math.BigDecimal;
import java.util.List;

public class CompraResponse {

    private ClienteResponse cliente;
    private BigDecimal total;
    private List<ItensResponse> itens;


    public ClienteResponse getCliente() {
        return cliente;
    }

    public CompraResponse(Compra compra) {
        this.total = compra.getItens().get(0).calculaTotal();
        this.itens = compra.getItens().stream().map(ItensResponse::new).toList();
        this.cliente = new ClienteResponse(compra.getCliente());


    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<ItensResponse> getItens() {
        return itens;
    }

}

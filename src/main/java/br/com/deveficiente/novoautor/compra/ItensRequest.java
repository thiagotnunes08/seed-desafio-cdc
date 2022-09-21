package br.com.deveficiente.novoautor.compra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class ItensRequest {

    @NotNull
    private Long idLivro;
    @Positive
    private int quantidade;

    public Long getIdLivro() {
        return idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "ItensRequest{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }
}

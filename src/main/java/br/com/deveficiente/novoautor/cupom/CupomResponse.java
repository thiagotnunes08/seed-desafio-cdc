package br.com.deveficiente.novoautor.cupom;

import java.math.BigDecimal;
import java.time.LocalDate;

public class CupomResponse {

    private BigDecimal percentualAtual;
    private LocalDate validadeAtual;
    public CupomResponse(CupomAplicado cupomAplicado) {
        this.percentualAtual = cupomAplicado.getPercentualAtual();
        this.validadeAtual = cupomAplicado.getValidadeAtual();


    }

    public CupomResponse(LocalDate validadeAtual, BigDecimal percentualAtual) {
        this.percentualAtual = percentualAtual;
        this.validadeAtual = validadeAtual;
    }

    public BigDecimal getPercentualAtual() {
        return percentualAtual;
    }

    public LocalDate getValidadeAtual() {
        return validadeAtual;
    }
}

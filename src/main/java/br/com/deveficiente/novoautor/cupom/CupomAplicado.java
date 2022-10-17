package br.com.deveficiente.novoautor.cupom;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.concurrent.CyclicBarrier;

@Embeddable
public class CupomAplicado   {

    @ManyToOne
    private Cupom cupom;
    private BigDecimal percentualAtual;
    private LocalDate validadeAtual;
    public CupomAplicado(Cupom cupom) {

        this.cupom = cupom;
        this.percentualAtual = cupom.getPercentual();
        this.validadeAtual = cupom.getValidoAte();
    }

    @Deprecated // contrutor padrao do hibernate
    public CupomAplicado() {
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentualAtual=" + percentualAtual +
                ", validadeAtual=" + validadeAtual +
                '}';
    }

    public BigDecimal getPercentualAtual() {
        return percentualAtual;
    }

    public LocalDate getValidadeAtual() {
        return validadeAtual;
    }


}

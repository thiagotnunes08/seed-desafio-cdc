package br.com.deveficiente.novoautor.cupom;

import br.com.deveficiente.novoautor.compartilhado.CampoUnico;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoCupomRequest {

    @NotBlank
    @CampoUnico(domainClass = Cupom.class,fieldName = "codigo")
    private String codigo;
    @Positive
    @NotNull
    @DecimalMax(value = "100")
    private BigDecimal percentual;
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate validoAte;

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    @Override
    public String toString() {
        return "NovoCupomRequest{" +
                "codigo='" + codigo + '\'' +
                ", percentual=" + percentual +
                ", validoAte=" + validoAte +
                '}';
    }

    public Cupom toModel() {
        return new Cupom(codigo,percentual,validoAte);
    }
}

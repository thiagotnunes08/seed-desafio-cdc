package br.com.deveficiente.novoautor.cupom;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Cupom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String codigo;
    @Column(nullable = false)
    private BigDecimal percentual;
    @Column(nullable = false)
    private LocalDate validoAte;

    @Embedded
    private CupomAplicado cupomAplicado;

    public Cupom(String codigo, BigDecimal percentual, LocalDate validoAte) {
        this.codigo = codigo;
        this.percentual = percentual;
        this.validoAte = validoAte;
    }

    @Deprecated
    public Cupom() {
    }

    @Override
    public String toString() {
        return "Cupom{" +
                "id=" + id +
                ", codigo='" + codigo + '\'' +
                ", percentual=" + percentual +
                ", validoAte=" + validoAte +
                '}';
    }

    public boolean cupomValido(){

    return LocalDate.now().compareTo(this.validoAte) <= 0;

    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentual() {
        return percentual;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }
}

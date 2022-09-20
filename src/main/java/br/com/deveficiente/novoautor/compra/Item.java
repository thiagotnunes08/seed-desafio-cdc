package br.com.deveficiente.novoautor.compra;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idLivro;
    private Integer quantidade;
    
    private BigDecimal valorUnitario;
    private BigDecimal total;

    public Item(Long idLivro, Integer quantidade,BigDecimal valorUnitario) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.total = calculaTotal();
        
    }

    @Deprecated // HINERNATE!
    public Item() {
    }


    public BigDecimal calculaTotal(){

        return new BigDecimal(String.valueOf(valorUnitario)).multiply(new BigDecimal(quantidade));
    }

    public Long getIdLivro() {
        return idLivro;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}

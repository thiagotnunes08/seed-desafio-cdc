package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.compartilhado.CampoUnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {
    @NotBlank
    @CampoUnico(fieldName = "titulo",domainClass = Livro.class)
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String resumo;
    private String sumario;
    @NotNull
    @DecimalMin(value = "20")
    private BigDecimal preco;
    @Max(100)
    @NotNull
    private Integer numeroDePaginas;
    @NotBlank
    @CampoUnico(fieldName = "isbn",domainClass = Livro.class)
    private String isbn;
    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPublicacao;

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getNumeroDePaginas() {
        return numeroDePaginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public Livro toModel(Categoria categoria,Autor autor) {
        return new Livro(titulo,resumo,sumario,preco,numeroDePaginas,isbn,dataPublicacao,categoria,autor);

    }


}

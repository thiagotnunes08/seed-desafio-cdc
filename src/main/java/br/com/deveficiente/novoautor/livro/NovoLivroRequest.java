package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.compartilhado.CampoExistente;
import br.com.deveficiente.novoautor.compartilhado.CampoUnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {

    @CampoExistente(message = "autor nao existente!",fieldName = "autorId",domainClass = Autor.class)
    private Long autorId;

    @CampoExistente(message = "categoria nao existente!",fieldName = "categoriaId",domainClass = Categoria.class)
    private Long categoriaId;
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

    public Long getAutorId() {
        return autorId;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

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

    public NovoLivroRequest(Long autorId, Long categoriaId, String titulo, String resumo, String sumario, BigDecimal preco, Integer numeroDePaginas, String isbn, LocalDate dataPublicacao) {
        this.autorId = autorId;
        this.categoriaId = categoriaId;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.numeroDePaginas = numeroDePaginas;
        this.isbn = isbn;
        this.dataPublicacao = dataPublicacao;
    }

    public Livro toModel(EntityManager manager) {

        Autor autor = manager.find(Autor.class, autorId);

        Categoria categoria = manager.find(Categoria.class, categoriaId);

        Assert.state(autor != null,"autor nao deveria ser nulo!");

        Assert.state(categoria != null,"categoria nao deveria ser nulo!");

        return new Livro(titulo,resumo,sumario,preco,numeroDePaginas,isbn,dataPublicacao,categoria,autor);

    }




}

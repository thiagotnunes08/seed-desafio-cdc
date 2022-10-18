package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.livro.Livro;
import org.hibernate.loader.entity.NaturalIdEntityJoinWalker;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Optional;

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

    public Item toModel(EntityManager manager) {

        Optional<Livro> possivelLivro = Optional.ofNullable(manager.find(Livro.class, idLivro));

        if (possivelLivro.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro não é válido ou existente");
        }

        Livro livro = possivelLivro.get();

       return new Item(livro, quantidade);
    }

    public ItensRequest(Long idLivro, int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }
}

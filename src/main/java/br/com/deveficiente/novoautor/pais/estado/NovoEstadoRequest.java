package br.com.deveficiente.novoautor.pais.estado;

import br.com.deveficiente.novoautor.compartilhado.CampoUnico;
import br.com.deveficiente.novoautor.pais.Pais;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NovoEstadoRequest {

    @NotBlank
    @CampoUnico(message = "Estado já existente no sistema!",domainClass = Estado.class,fieldName = "nome")
    private String nome;
    @NotNull
    private Long pais;

    public String getNome() {
        return nome;
    }

    public Long getPais() {
        return pais;
    }

    public Estado toModel(EntityManager manager) {

        Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, pais));

        if (possivelPais.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Pais não entrado!");
        }

        Pais novoPais = possivelPais.get();

        return new Estado(nome,novoPais);
    }

    public NovoEstadoRequest(String nome, Long pais) {
        this.nome = nome;
        this.pais = pais;
    }
}

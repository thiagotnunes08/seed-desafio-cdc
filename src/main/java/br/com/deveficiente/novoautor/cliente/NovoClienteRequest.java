package br.com.deveficiente.novoautor.cliente;

import br.com.deveficiente.novoautor.compartilhado.CPForCNPJ;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;

public class NovoClienteRequest {

    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @Email
    @NotBlank
    private String email;
    @CPForCNPJ
    @NotBlank
    private String documento;
    @NotBlank
    private String telefone;
    @NotBlank
    private String endereco;

    @NotBlank
    private String cidade;

    @NotBlank
    private String complemento;

    @NotBlank
    private String cep;

    @NotNull
    private Long idPais;

    @NotNull
    private Long idEstado;


    public Cliente toModel(EntityManager manager) {

        Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, idPais));


        Optional<Estado> possivelEstado = Optional.ofNullable(manager.find(Estado.class, idEstado));

        if (possivelPais.isEmpty() || possivelEstado.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"País ou Estado informado incorretamente!");
        }

        Pais pais = possivelPais.get();

        Estado estado = possivelEstado.get();

        return new Cliente(nome,sobrenome,email,documento,telefone,endereco,complemento,cidade,cep,pais,estado);
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCidade() {
        return cidade;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public String getCep() {
        return cep;
    }
}

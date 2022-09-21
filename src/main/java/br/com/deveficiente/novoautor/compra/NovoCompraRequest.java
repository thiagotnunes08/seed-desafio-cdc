package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.compartilhado.CPForCNPJ;
import br.com.deveficiente.novoautor.livro.Livro;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class NovoCompraRequest {

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


    private Long idEstado;

    @Valid
    @NotNull
    private NovoPedidoRequest pedido;


    public Compra toModel(EntityManager manager) {


        Function<Compra,Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, idPais));

        Optional<Livro> possivelLivro = Optional
                .ofNullable(manager.find(Livro.class,pedido.getItens().get(0).getIdLivro()));


        if (possivelLivro.isEmpty() || possivelPais.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Livro ou país inválidos!");
        }

        Pais pais = possivelPais.get();

        Compra compra = new Compra(nome, sobrenome, email, documento, telefone, endereco, complemento, cidade, cep, pais,funcaoCriacaoPedido);

        if (idEstado != null){
            compra.setEstado(manager.find(Estado.class,idEstado));
        }

        return compra;
    }

    public boolean temEstado() {
        return idEstado != null;
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

    public NovoPedidoRequest getPedido() {
        return pedido;
    }

    @Override
    public String toString() {
        return "NovoCompraRequest{" +
                "nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", idPais=" + idPais +
                ", idEstado=" + idEstado +
                ", pedido=" + pedido +
                '}';
    }
}

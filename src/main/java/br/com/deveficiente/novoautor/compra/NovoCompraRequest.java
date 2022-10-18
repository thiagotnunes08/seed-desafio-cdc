package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.compartilhado.CPForCNPJ;
import br.com.deveficiente.novoautor.compartilhado.CampoExistente;
import br.com.deveficiente.novoautor.cupom.CupomRepository;
import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.livro.Livro;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Optional;
import java.util.function.Function;

//C.I = 10

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
    @NotNull //1
    private NovoPedidoRequest pedido;
    @CampoExistente(domainClass = Cupom.class, fieldName = "codigo")
    private String codigoCupom;

    public NovoCompraRequest(String nome, String sobrenome, String email, String documento, String telefone, String endereco, String cidade, String complemento, String cep, Long idPais, Long idEstado, NovoPedidoRequest pedido, String codigoCupom) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cidade = cidade;
        this.complemento = complemento;
        this.cep = cep;
        this.idPais = idPais;
        this.idEstado = idEstado;
        this.pedido = pedido;
        this.codigoCupom = codigoCupom;
    }

    //2
    public Compra toModel(EntityManager manager, CupomRepository repository) {

        //3   //4
        Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);

        //5
        Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, idPais));

        //6
        Optional<Livro> possivelLivro = Optional
                .ofNullable(manager.find(Livro.class, pedido.getItens().get(0).getIdLivro()));


        //7
        if (possivelLivro.isEmpty() || possivelPais.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Livro ou país inválidos!");
        }

        Pais pais = possivelPais.get();
        //10
        Compra compra = new Compra(nome, sobrenome, email, documento, telefone, endereco, complemento, cidade, cep, pais, funcaoCriacaoPedido);


        //8
        if (idEstado != null) {

            compra.setEstado(manager.find(Estado.class, idEstado));
        }
        //9
        if (StringUtils.hasLength(codigoCupom)) {
            Cupom cupom = repository.getByCodigo(codigoCupom);
            compra.aplicaCupom(cupom);

        }

        return compra;
    }

    public boolean docValido() {
        Assert.hasLength(documento, "documento deve ser válido!");
        CPFValidator cpfValidator = new CPFValidator();
        cpfValidator.initialize(null);

        CNPJValidator cnpjValidator = new CNPJValidator();
        cnpjValidator.initialize(null);

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);


    }

    public boolean temEstado() {
        return Optional.ofNullable(idEstado).isPresent();
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

    public Optional<String> getCodigoCupom() {
        return Optional.ofNullable(codigoCupom);
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

    public void setCodigoCupom(String codigoCupom) {
        this.codigoCupom = codigoCupom;
    }

    public void setIdEstado(Long idEstado) {
        this.idEstado = idEstado;
    }
}

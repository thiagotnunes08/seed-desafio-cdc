package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.cupom.CupomAplicado;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.function.Function;
import java.util.stream.Stream;

@Entity
public class Compra {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String sobrenome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String documento;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false)
    private String cidade;

    @Column(nullable = false)
    private String complemento;

    @Column(nullable = false)
    private String cep;

    @ManyToOne(optional = false)
    private Pais pais;

    @ManyToOne
    private Estado estado;
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
    private Pedido pedido;
    @Embedded
    private CupomAplicado cupomAplicado;


    public Compra(String nome, String sobrenome, String email, String documento, String telefone, String endereco, String complemento, String cidade, String cep, Pais pais, Function<Compra, Pedido> funcaoCriacaoPedido) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.documento = documento;
        this.telefone = telefone;
        this.complemento = complemento;
        this.endereco = endereco;
        this.cidade = cidade;
        this.cep = cep;
        this.pais = pais;
        this.pedido = funcaoCriacaoPedido.apply(this);
    }

    @Deprecated // HIBERNATE
    public Compra() {
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getEmail() {
        return email;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setEstado(Estado estado) {
        Assert.notNull(pais, "erro ao associar estado ao país. Este é nulo");
        Assert.isTrue(estado.pertenceAPais(pais), "esse estado não pertece a este país!");
        this.estado = estado;
    }

    public void aplicaCupom(Cupom cupom) {
        Assert.isTrue(cupom.cupomValido(), "cupom não é válido");
        Assert.isNull(this.cupomAplicado, "Você não pode trocar um cupom de uma compra!");
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    @Override
    public String toString() {
        return "Compra{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", documento='" + documento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cidade='" + cidade + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", pais=" + pais +
                ", estado=" + estado +
                ", pedido=" + pedido +
                ", cupomAplicado=" + cupomAplicado +
                '}';
    }

    public String getCidade() {
        return cidade;
    }

    public Long getId() {
        return id;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getTelefone() {
        return telefone;
    }

    public Pais getPais() {
        return pais;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }

    public BigDecimal descontoCupom() {

        BigDecimal valorTotal = (BigDecimal) pedido.getItens().stream().map(Item::getPrecoMomento);

        if (cupomAplicado != null) {

            BigDecimal valorDesconto = valorTotal.multiply(new BigDecimal(String.valueOf(cupomAplicado.getPercentualAtual())).divide(new BigDecimal("100")));

            valorTotal = valorTotal.subtract(valorDesconto);
        }
        System.out.println(valorTotal + "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%################");

        return valorTotal;

    }


}

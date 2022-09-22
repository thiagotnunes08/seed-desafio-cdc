package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.function.Function;

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
    @OneToOne(mappedBy = "compra",cascade = CascadeType.PERSIST)
    private Pedido pedido;

    @OneToOne
    @Column(updatable = false)
    private Cupom cupom;


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
        Assert.notNull(pais,"erro ao associar estado ao país. Este é nulo");
        Assert.isTrue(estado.pertenceAPais(pais),"esse estado não pertece a este país!");
        this.estado = estado;
    }

    public void setCupom(Cupom cupom) {
        Assert.notNull(cupom,"cupom não é válido");
        Assert.isTrue(!cupom.cupomExpirado(),"cupom não é válido");
        this.cupom = cupom;
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
                '}';
    }

    public String getCidade() {
        return cidade;
    }
}

package br.com.deveficiente.novoautor.cliente;

import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;

import javax.persistence.*;

@Entity
public class Cliente {

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

    @ManyToOne(optional = false)
    private Estado estado;

    public Cliente(String nome, String sobrenome, String email, String documento, String telefone, String endereco, String complemento, String cidade, String cep, Pais pais, Estado estado) {
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
        this.estado = estado;
    }

    @Deprecated // HIBERNATE
    public Cliente() {
    }


}

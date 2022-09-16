package br.com.deveficiente.novoautor.autor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(name = "UK_EMAIL", columnNames = "email"))
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false,length = 400)
    private String descricao;

    @Column(nullable = false)
    private final LocalDateTime criadoEm = now();

    /**
     * @Deprecated: Hibernate!
     */
    @Deprecated
    public Autor() {
    }

    public Autor(String nome, String email, String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }


    public String getDescricao() {
        return descricao;
    }
}

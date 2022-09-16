package br.com.deveficiente.novoautor.categoria;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints =
@UniqueConstraint(name = "UK_NAME",columnNames = "nome"))
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Deprecated // hibernate!
    public Categoria() {
    }

    public String getNome() {
        return nome;
    }
}

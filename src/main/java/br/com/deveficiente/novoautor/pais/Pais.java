package br.com.deveficiente.novoautor.pais;

import org.hibernate.procedure.spi.ParameterRegistrationImplementor;

import javax.persistence.*;

@Entity
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;

    public Pais(String nome) {
        this.nome = nome;
    }

    @Deprecated // USO HIBERNATE!
    public Pais() {
    }
}

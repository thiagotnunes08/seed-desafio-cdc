package br.com.deveficiente.novoautor.pais.estado;

import br.com.deveficiente.novoautor.pais.Pais;

import javax.persistence.*;

@Entity
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String nome;
    @ManyToOne(optional = false)
    private Pais pais;

    public Estado(String nome, Pais pais) {
        this.nome = nome;
        this.pais = pais;
    }

    @Deprecated // USO HIBERNATE!
    public Estado() {
    }

    public boolean pertenceAPais(Pais pais) {
       return this.pais.equals(pais);
    }

    @Override
    public String toString() {
        return "Estado{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }


}

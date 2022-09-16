package br.com.deveficiente.novoautor.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    Optional<Categoria> findByNome(String nome);
}

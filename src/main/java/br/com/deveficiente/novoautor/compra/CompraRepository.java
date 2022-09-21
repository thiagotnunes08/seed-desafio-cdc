package br.com.deveficiente.novoautor.compra;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompraRepository extends JpaRepository<Compra,Long> {
    Optional<Compra> findByEmail(String emailCliente);
}

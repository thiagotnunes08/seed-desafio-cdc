package br.com.deveficiente.novoautor.cupom;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CumpoRepository extends JpaRepository<Cupom,Long> {

   public Cupom findByCodigo(String codigo);
}

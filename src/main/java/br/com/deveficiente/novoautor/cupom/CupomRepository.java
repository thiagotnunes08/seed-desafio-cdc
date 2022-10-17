package br.com.deveficiente.novoautor.cupom;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CupomRepository extends JpaRepository<Cupom,Long> {

   public Cupom getByCodigo(String codigo);

}

package br.com.deveficiente.novoautor.compra;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@RestController
@RequestMapping("api/compras")
public class DetalhaCompraController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("/{id}")
    public DetalhesDaCompraResponse detalha(@PathVariable Long id){

        Compra compra = manager.find(Compra.class,id);

        return new DetalhesDaCompraResponse(compra);

    }
}

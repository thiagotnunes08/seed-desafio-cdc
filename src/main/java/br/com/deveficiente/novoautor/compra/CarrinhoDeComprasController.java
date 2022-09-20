package br.com.deveficiente.novoautor.compra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@RestController
@RequestMapping("api/comprar/")
public class CarrinhoDeComprasController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping("{id}")
    public CompraResponse detalha(@PathVariable Long id){

        Optional<Compra> possivelCompra = Optional.ofNullable(manager.find(Compra.class, id));

        if (possivelCompra.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Compra não encontrada!");
        }

        Compra compra = possivelCompra.get();


        return new CompraResponse(compra);

    }
}

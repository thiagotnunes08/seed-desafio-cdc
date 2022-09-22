package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.CumpoRepository;
import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.pais.EstadoPertenceAoPaisValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/compras")
public class NovoCompraController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private CumpoRepository repository;

    @Autowired
    private EstadoPertenceAoPaisValidator validator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(validator);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EnderecoCompraResponse> cadastra(@Valid @RequestBody NovoCompraRequest request){

        Compra novoCompra = request.toModel(manager,repository);


        manager.persist(novoCompra);

        return ResponseEntity.status(201).body(new EnderecoCompraResponse(novoCompra));

    }
}

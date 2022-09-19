package br.com.deveficiente.novoautor.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/clientes")
public class NovoClienteController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private EstadoPertenceAoPaisValidator validator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(validator);
    }

    @PostMapping
    @Transactional
    public void cadastra(@Valid @RequestBody NovoClienteRequest request){

        Cliente novoCliente = request.toModel(manager);


        manager.persist(novoCliente);


    }
}

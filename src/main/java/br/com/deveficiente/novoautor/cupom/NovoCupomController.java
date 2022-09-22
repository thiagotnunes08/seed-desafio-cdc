package br.com.deveficiente.novoautor.cupom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/cupons")
public class NovoCupomController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastra(@Valid @RequestBody NovoCupomRequest request){

        Cupom novoCupom = request.toModel();

        manager.persist(novoCupom);

        System.out.println(novoCupom);

    }
}

package br.com.deveficiente.novoautor.pais;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("api/paises")
public class NovoPaisController {

    // C.I = Pais e PaisRequest = 2
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public void cadastra(@RequestBody @Valid NovoPaisRequest request){

        Pais novoPais = request.toModel();

        manager.persist(novoPais);

    }
}

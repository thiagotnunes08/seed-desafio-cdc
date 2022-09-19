package br.com.deveficiente.novoautor.pais.estado;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RequestMapping("api/estados")
@RestController
public class NovoEstadoController {

    // C.I = 2; estado e novo estado!
    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @PostMapping
    public void cadastra(@Valid @RequestBody NovoEstadoRequest request){

        Estado novoEstado = request.toModel(manager);

        manager.persist(novoEstado);

    }
}

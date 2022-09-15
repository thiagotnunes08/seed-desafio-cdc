package br.com.deveficiente.novoautor.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/autores")
public class NovoAutorController {
    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastra(@Valid @RequestBody NovoAutorRequest request){

        Autor novoAutor = request.toModel();

        manager.persist(novoAutor);

    }
}

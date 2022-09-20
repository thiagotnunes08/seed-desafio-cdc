package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cliente.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/compras")
public class NovaCompraController {

    @PersistenceContext
    private EntityManager manager;
    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<CompraResponse> compra(@Valid @RequestBody NovaCompraRequest request){

        Compra novaComrpa = request.toModel(manager,repository);


        manager.persist(novaComrpa);


        return ResponseEntity.status(201).body(new CompraResponse(novaComrpa));

    }
}

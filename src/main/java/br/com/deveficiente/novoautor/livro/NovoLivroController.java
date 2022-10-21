package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.autor.AutorRepository;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

//carga intrinseca = 7 / max = 7. deu pra passar de ano

@RestController
@RequestMapping("/api/livros")
public class NovoLivroController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public void cadastra(@Valid @RequestBody NovoLivroRequest request) {

        Livro novoLivro = request.toModel(manager);


        manager.persist(novoLivro);


    }

}


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

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;


    @PostMapping("/categorias/{categoriaId}/autores/{autorId}")
    public void cadastra(@PathVariable Long categoriaId, @PathVariable Long autorId, @Valid @RequestBody NovoLivroRequest request) {

        Categoria possiveCategoria = categoriaRepository
                .findById(categoriaId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria nao encotrada no sistema!"));

        Autor possivelAutor = autorRepository
                .findById(autorId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Autor nao encotrado no sistema!"));

        Livro novoLivro = request.toModel(possiveCategoria,possivelAutor);

        livroRepository.save(novoLivro);


    }

}


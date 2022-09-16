package br.com.deveficiente.novoautor.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//C.I = 2

@RestController
@RequestMapping("/api/livros")
public class ListaLivrosController {

    @Autowired
    private LivroRepository repository;

    @GetMapping
    public List<LivroResponse> lista() {

        List<LivroResponse> livroResponses = repository.findAll().stream().map(LivroResponse::new).toList();

        if (livroResponses.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não há livros cadastrado no sistema!");
        }

        return livroResponses;

    }
}

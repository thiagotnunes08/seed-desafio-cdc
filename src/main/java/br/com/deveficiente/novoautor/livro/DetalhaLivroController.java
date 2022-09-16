package br.com.deveficiente.novoautor.livro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/livros/")
public class DetalhaLivroController {

    @Autowired
    private LivroRepository repository;

    @GetMapping("{id}")
    public List<DetalhesDoLivroResponse> detalha(@PathVariable Long id){

        List<DetalhesDoLivroResponse> collect = repository.findById(id).stream().map(DetalhesDoLivroResponse::new).toList();

        if (collect.isEmpty()){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND,"nenhum livro econtrado!");
        }

        return collect;
    }
}

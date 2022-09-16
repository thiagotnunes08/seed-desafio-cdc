package br.com.deveficiente.novoautor.autor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
//max 7
//total = 4
@RestController
@RequestMapping("/api/autores")
public class NovoAutorController {

    @Autowired        //1
    private final AutorRepository repository;

    public NovoAutorController(AutorRepository repository) {
        this.repository = repository;

    }

    @PostMapping                              //2
    public void cadastra(@Valid @RequestBody NovoAutorRequest request){

        //4
        Autor novoAutor = request.toModel();

        repository.save(novoAutor);

    }
}

package br.com.deveficiente.novoautor.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/categorias")
public class NovaCategoriaController {

    //MAX = 7
    //ATUAL = 4

    @Autowired        //1
    private final CategoriaRepository repository;

    @Autowired             //2
    private final ProibidoNomeCategoriaDupliado proibidoNomeCategoriaDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder){
        binder.addValidators(proibidoNomeCategoriaDuplicadoValidator);
    }

    public NovaCategoriaController(CategoriaRepository repository, ProibidoNomeCategoriaDupliado proibidoNomeCategoriaDuplicadoValidator) {
        this.repository = repository;
        this.proibidoNomeCategoriaDuplicadoValidator = proibidoNomeCategoriaDuplicadoValidator;
    }

    @PostMapping                              //3
    public void cadastra(@RequestBody @Valid NovaCategoriaRequest request){
        //4
      Categoria novaCategoria = request.toModel();

      repository.save(novaCategoria);

    }
}

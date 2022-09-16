package br.com.deveficiente.novoautor.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import java.util.Optional;

/**
 * poderia substituir por anotacao de campo unico.
 * mas deixei por conhecimento de mais uma maneira de valiar campo existente no sistema!
 */

@Component
public class ProibidoNomeCategoriaDupliado implements Validator {

    @Autowired
    private CategoriaRepository repository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()){
            return;
        }

        NovaCategoriaRequest request = (NovaCategoriaRequest) target;

        Optional<Categoria> possivelCategoria = repository.findByNome(request.getNome());

        if (possivelCategoria.isPresent()){
            errors.rejectValue("nome",null,"Categoria já existente no sitema!");
        }

    }
}

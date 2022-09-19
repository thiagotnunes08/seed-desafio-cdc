package br.com.deveficiente.novoautor.cliente;

import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import br.com.deveficiente.novoautor.pais.estado.NovoEstadoRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Component
public class EstadoPertenceAoPaisValidator implements Validator {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

      if (errors.hasErrors()){
          return;
      }

      NovoClienteRequest request = (NovoClienteRequest) target;

        Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, request.getIdPais()));

        Optional<Estado> possivelEstado = Optional.ofNullable(manager.find(Estado.class, request.getIdEstado()));

        if (possivelPais.isEmpty() || possivelEstado.isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"País ou Estado informado incorretamente!");
        }

        Pais pais = possivelPais.get();

        Estado estado = possivelEstado.get();

        if (!estado.pertenceAPais(pais)){
            errors.rejectValue("idEstado",null,"Estado não pertece ao país!");

        }


    }
}

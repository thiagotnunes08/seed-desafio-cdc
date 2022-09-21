package br.com.deveficiente.novoautor.pais;

import br.com.deveficiente.novoautor.compra.NovoCompraRequest;
import br.com.deveficiente.novoautor.pais.estado.Estado;
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
        return NovoCompraRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        if (errors.hasErrors()) {
            return;
        }

        NovoCompraRequest request = (NovoCompraRequest) target;

        if (request.temEstado()) {

            Optional<Estado> possivelEstado = Optional.ofNullable(manager.find(Estado.class, request.getIdEstado()));
            Optional<Pais> possivelPais = Optional.ofNullable(manager.find(Pais.class, request.getIdPais()));

            if (possivelEstado.isEmpty() || possivelPais.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            }

            Estado estado = possivelEstado.get();
            Pais pais = possivelPais.get();

            if (!estado.pertenceAPais(pais)) {
                errors.rejectValue("idEstado", null, "Estado não pertece ao país!");

            }

        }

    }
}

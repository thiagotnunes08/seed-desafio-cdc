package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.cupom.CupomRepository;
import br.com.deveficiente.novoautor.cupom.Cupom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class CupomValidoValidator implements Validator {

    @Autowired
    private CupomRepository repository;

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

        Optional<String> possivelCupom = request.getCodigoCupom();

        if (possivelCupom.isPresent()) {

            Cupom cupom = repository.getByCodigo(possivelCupom.get());

            if (!cupom.cupomValido()) {
                errors.rejectValue("codigoCupom", null, "cupom não é mais válido!");
            }


        }


    }
}

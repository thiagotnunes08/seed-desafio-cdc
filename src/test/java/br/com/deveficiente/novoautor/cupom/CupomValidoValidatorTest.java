package br.com.deveficiente.novoautor.cupom;

import br.com.deveficiente.novoautor.compra.ItensRequest;
import br.com.deveficiente.novoautor.compra.NovoCompraRequest;
import br.com.deveficiente.novoautor.compra.NovoPedidoRequest;
import br.com.deveficiente.novoautor.pais.NovoPaisRequest;
import br.com.deveficiente.novoautor.pais.estado.NovoEstadoRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class CupomValidoValidatorTest {

    CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);
    NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(BigDecimal.TEN, List.of(new ItensRequest(1L, 1)));
    NovoCompraRequest request = new NovoCompraRequest(
            "nome", "nome", "email@email",
            "doc", "3412321321",
            "ed", "udi", "comple", "cep", 1L, 1L, pedidoRequest, "codigo");

    NovoEstadoRequest estadoRequest = new NovoEstadoRequest("NOME",1L);

    NovoPaisRequest paisRequest = new NovoPaisRequest("NOME");


    @Test
    @DisplayName("deveria parar caso já houver algum erro")
    void test1() {

        Errors errors = new BeanPropertyBindingResult(request, "target");
        errors.reject("codigo");

        CupomValidoValidator validator = new CupomValidoValidator(cupomRepository);
        validator.validate(request, errors);

        Assertions.assertTrue(errors.hasErrors());
        Assertions.assertEquals("codigo", errors.getGlobalError().getCode());

    }

    @Test
    @DisplayName("deveria parar caso cupom seja invalido")
    void test2() {
        Cupom cupom = new Cupom("codigoCupom",BigDecimal.TEN, LocalDate.now().plusDays(1));

        ReflectionTestUtils.setField(cupom,"validoAte",LocalDate.now().minusDays(1));

        request.setCodigoCupom("codigoCupom");
        Mockito.when(cupomRepository.getByCodigo("codigoCupom")).thenReturn(cupom);

        Errors errors = new BeanPropertyBindingResult(request,"target");
        CupomValidoValidator cupomValidoValidator = new CupomValidoValidator(cupomRepository);

        cupomValidoValidator.validate(request,errors);

        Assertions.assertTrue(errors.hasErrors());
        Assertions.assertEquals("cupom não é mais válido!", errors.getFieldError("codigoCupom").getDefaultMessage());
    }

    @Test
    @DisplayName("deveria passar caso nao houver cupom")
    void test3() {

        Errors errors = new BeanPropertyBindingResult(request,"target");
        CupomValidoValidator cupomValidoValidator = new CupomValidoValidator(cupomRepository);

        cupomValidoValidator.validate(request,errors);

        Assertions.assertFalse(errors.hasErrors());


    }

    @Test
    @DisplayName("deveria passar o cupom caso esteja válido")
    void test4()  {

        Cupom cupom = new Cupom("codigoCupom",BigDecimal.TEN,LocalDate.now().plusDays(1));

        request.setCodigoCupom("codigoCupom");
        Mockito.when(cupomRepository.getByCodigo("codigoCupom")).thenReturn(cupom);

        Errors errors = new BeanPropertyBindingResult(request,"target");
        CupomValidoValidator validator = new CupomValidoValidator(cupomRepository);
        validator.validate(request,errors);

        Assertions.assertFalse(errors.hasErrors());
    }
}
package br.com.deveficiente.novoautor.pais;

import br.com.deveficiente.novoautor.compra.ItensRequest;
import br.com.deveficiente.novoautor.compra.NovoCompraRequest;
import br.com.deveficiente.novoautor.compra.NovoPedidoRequest;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

class EstadoPertenceAoPaisValidatorTest {

    private final EntityManager manager = Mockito.mock(EntityManager.class);

    private final NovoPedidoRequest pedidoRequest = new NovoPedidoRequest(BigDecimal.TEN, List.of(new ItensRequest(1L, 1)));
    private final NovoCompraRequest novoCompraRequest = new NovoCompraRequest(
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", 1L, 1L, pedidoRequest, "codigo");

    private final NovoCompraRequest novaCompraRequestSemEstado = new NovoCompraRequest(
            "a", "b", "c", "d", "e", "f",
            "g", "h", "i", 1L, null, pedidoRequest, "codigo");


    @Test
    @DisplayName("deveria parar se ja tem erro de validacao no fluxo")
    void test1() throws Exception {
        Errors errors = new BeanPropertyBindingResult(novoCompraRequest, "target");
        errors.reject("codigo");
        EstadoPertenceAoPaisValidator validator = new EstadoPertenceAoPaisValidator(manager);

        validator.validate(novoCompraRequest, errors);

        Assertions.assertEquals(errors.getAllErrors().size(), 1);
        Assertions.assertEquals("codigo", Objects.requireNonNull(errors.getGlobalError()).getCode());


    }

    @Test
    @DisplayName("deveria deixar passsar se a compra ta sem estado")
    void test2() {

        Errors errors = new BeanPropertyBindingResult(novaCompraRequestSemEstado, "target");

        EstadoPertenceAoPaisValidator validator = new EstadoPertenceAoPaisValidator(manager);
        validator.validate(novaCompraRequestSemEstado, errors);

        Assertions.assertFalse(errors.hasErrors());
    }

    @Test
    @DisplayName("deveria bloquear compra, pois estado nao pertece ao pais")
    void test3() {

        Pais pais = new Pais("USA");
        Pais pais1 = new Pais("BR");
        Estado estado = new Estado("mg", pais);

        Mockito.when(manager.find(Estado.class, 2L)).thenReturn(estado);

        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais1);
        novoCompraRequest.setIdEstado(2L);

        Errors errors = new BeanPropertyBindingResult(novoCompraRequest, "target");
        EstadoPertenceAoPaisValidator validator = new EstadoPertenceAoPaisValidator(manager);

        validator.validate(novoCompraRequest, errors);

        Assertions.assertEquals(errors.getAllErrors().size(), 1);
        Assertions.assertTrue(errors.hasFieldErrors("idEstado"));


    }

    @Test
    @DisplayName("deveria validar uma compra com pais e estado percente ao pais")
    void test4() {
        Pais pais = new Pais("BR");
        Estado estado = new Estado("MG",pais);

        Mockito.when(manager.find(Pais.class,1L)).thenReturn(pais);
        Mockito.when(manager.find(Estado.class,1L)).thenReturn(estado);
        novoCompraRequest.setIdEstado(1L);

        Errors errors = new BeanPropertyBindingResult(novoCompraRequest,"target");

        EstadoPertenceAoPaisValidator validator = new EstadoPertenceAoPaisValidator(manager);

        validator.validate(novoCompraRequest,errors);

        Assertions.assertFalse(errors.hasErrors());




    }
}
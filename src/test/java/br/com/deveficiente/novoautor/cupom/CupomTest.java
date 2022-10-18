package br.com.deveficiente.novoautor.cupom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

class CupomTest {

    @ParameterizedTest
    @CsvSource({"0,true","1,true"})
    public void test1(long valor,boolean resultado){
        Cupom cupom = new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(valor));

        Assertions.assertEquals(resultado,cupom.cupomValido());

    }

    @Test
    @DisplayName("cupom com data passada nao eh mais valido")
    void test2() {
        Cupom cupom = new Cupom("codigo",BigDecimal.TEN,LocalDate.now());
        ReflectionTestUtils.setField(cupom,"validoAte",LocalDate.now().minusDays(1));
        Assertions.assertFalse(cupom.cupomValido());
    }

    @Test
    @DisplayName("nao pode criar cumpom com data no passado")
    void test3() {
        Assertions.assertThrows(IllegalArgumentException.class,()-> new Cupom("codigo",BigDecimal.TEN,LocalDate.now().minusDays(1)));
    }
}
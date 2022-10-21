package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.livro.Livro;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

class PedidoTest {


    @Test
    @DisplayName("verifica se o total do pedido é igual ao passado como argumento")
    @ParameterizedTest
    @CsvSource({"10,true", "9.99,false", "10.01,false"})
    void test1(BigDecimal valor, boolean resultadoEsperado) {
        Autor autor = new Autor("autor", "email@email", "descricao");
        Categoria categoria = new Categoria("nome");
        Livro livro = new Livro("nome", "resumo",
                "sumario", BigDecimal.TEN, 100, "ISBN", LocalDate.now(), categoria, autor);

        Set<Item> items = Set.of(new Item(livro, 1));
        Pedido pedido = new Pedido(Mockito.mock(Compra.class), items);
        Assertions.assertEquals(resultadoEsperado, pedido.totalIgual(valor));


    }
}
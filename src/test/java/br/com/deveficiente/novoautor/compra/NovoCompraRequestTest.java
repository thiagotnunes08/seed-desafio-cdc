package br.com.deveficiente.novoautor.compra;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.cupom.Cupom;
import br.com.deveficiente.novoautor.cupom.CupomRepository;
import br.com.deveficiente.novoautor.livro.Livro;
import br.com.deveficiente.novoautor.pais.Pais;
import br.com.deveficiente.novoautor.pais.estado.Estado;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

class NovoCompraRequestTest {


    private final EntityManager manager = Mockito.mock(EntityManager.class);

    private final CupomRepository repository = Mockito.mock(CupomRepository.class);
    private final List<ItensRequest> itensRequest = List.of(new ItensRequest(1L, 10));

    private final NovoPedidoRequest request = new NovoPedidoRequest(BigDecimal.TEN, itensRequest);

    private final Pais pais = new Pais("BRASA");

    private final Autor autor = new Autor("thigas", "email@email", "uma descricao");

    private final Categoria categoria = new Categoria("JAVA");

    private final Livro livro = new
            Livro("titulo",
            "resumo",
            "sumario",
            BigDecimal.TEN,
            10,
            "ISBN11",
            LocalDate.of(2023, 1, 1)
            , categoria
            , autor);


    {
        Mockito.when(manager.find(Pais.class, 1L)).thenReturn(pais);
        Mockito.when(manager.find(Livro.class, 1L)).thenReturn(livro);
        Mockito.when(manager.find(Estado.class, 1L)).thenReturn(new Estado("BR", pais));
        Mockito.when(repository.getByCodigo("codigo"))
                .thenReturn(new Cupom("codigo", BigDecimal.TEN, LocalDate.now().plusDays(1)));

    }


    private final NovoCompraRequest compraRequest = new NovoCompraRequest(
            "compra",
            "sonbrenome",
            "email@email",
            "CPF",
            "3499222222",
            "endereco",
            "udia",
            "casa",
            "323233",
            1L,
            1L,
            request,
            "codigo");

    @Test
    @DisplayName("deve cadastrar uma compra com cupom e estado válido")
    void test1() {

        compraRequest.setCodigoCupom("codigo");
        compraRequest.setIdEstado(1L);

        Compra compra = compraRequest.toModel(manager, repository);

        Assertions.assertNotNull(compra);
        Mockito.verify(manager).find(Estado.class, 1L);
        Mockito.verify(repository).getByCodigo("codigo");

    }

    @Test
    @DisplayName("deve cadastrar uma compra com cupom inválido")
    void test2() throws Exception {

        compraRequest.setCodigoCupom("codigo");
        Compra compra = compraRequest.toModel(manager, repository);

        Assertions.assertNotNull(compra);

        Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
        Mockito.verify(repository.getByCodigo("codigo"));

    }


    @Test
    @DisplayName("deve cadastrar uma compra sem cupom e estado")
    void test3() throws Exception {

        Compra compra = compraRequest.toModel(manager, repository);
        Assertions.assertNotNull(compra);

        Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());

        Mockito.verify(repository, Mockito.never()).getByCodigo(Mockito.anyString());


    }

    /**
     * cpf true cnpj false
     * cpf false cnpj true
     * cpf false cnpj false
     */
    @ParameterizedTest
    @DisplayName("verifica doc valido")
    @CsvSource({"51999690052,true", "95239326000182,true", "21342354354351,false"})
    @Test
    void test4(String doc, boolean resultadoEsperado) throws Exception {
        NovoCompraRequest request1 =
                new NovoCompraRequest("thiago",
                        "nunes",
                        "email@email"
                        ,doc,
                        "341234123",
                        "ed"
                        ,"udi",
                        "casa"
                ,"341234",
                        1L,
                        1L,
                        request,
                        "codigo");

        Assertions.assertEquals(resultadoEsperado,request1.docValido());
    }

}

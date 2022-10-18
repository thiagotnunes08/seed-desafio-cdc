package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.time.LocalDate;

class NovoLivroRequestTest {

    private final NovoLivroRequest request = new NovoLivroRequest(
            1L,1L,"autor qualquer","um resumo",
            "um sumario", BigDecimal.ONE,10,"ASDF1234", LocalDate.of(2023,1,1));

    @Test
    @DisplayName("cria o livro com autor e categoria validos")
    public void test1() throws Exception{

        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class,1L))
                .thenReturn(new Categoria("programacao"));

        Mockito.when(manager.find(Autor.class,1L))
                .thenReturn(new Autor("thiago","email","descricao"));

        Assertions.assertNotNull(request.toModel(manager));

    }

    @Test
    @DisplayName("nao o cria livro, pois autor nao existe no banco")
    void test2() {

        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class,1L))
                .thenReturn(new Categoria("nome"));

        Mockito.when(manager.find(Autor.class,1L))
                .thenReturn(null);

        Assertions.assertThrows(IllegalStateException.class,()-> request.toModel(manager));

    }

    @Test
    @DisplayName("nao o cria livro, pois categoria nao existe no banco")
    void test3() {
        EntityManager manager = Mockito.mock(EntityManager.class);

        Mockito.when(manager.find(Categoria.class,1L))
                .thenReturn(null);

        Mockito.when(manager.find(Autor.class,1L))
                .thenReturn(new Autor("nome","email","descricao"));

        Assertions.assertThrows(IllegalStateException.class,()-> request.toModel(manager));
    }
}
package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.Autor;
import br.com.deveficiente.novoautor.categoria.Categoria;
import br.com.deveficiente.novoautor.categoria.NovaCategoriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
class NovoLivroControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private LivroRepository repository;


    @Test
    @DisplayName("deve cadastrar um livro!")
    void test1() throws Exception {
        Autor autor = new Autor("nome","email","descricao");



        NovoLivroRequest novoLivroRequest =
                new NovoLivroRequest(1L, 1L, "titulo", "resumo", "sumario", BigDecimal.TEN, 100, "ISBN", LocalDate.now());

        String payload = mapper.writeValueAsString(novoLivroRequest);

        MockHttpServletRequestBuilder conteudo = MockMvcRequestBuilders.post("/api/livros").content(payload).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(conteudo).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
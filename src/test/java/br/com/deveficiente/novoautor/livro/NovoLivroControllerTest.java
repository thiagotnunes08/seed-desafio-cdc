package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.NovoAutorRequest;
import br.com.deveficiente.novoautor.categoria.NovaCategoriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
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
    @DisplayName("deve cadastrar um livro")
    void test1() throws Exception {


        NovaCategoriaRequest categoriaRequest = new NovaCategoriaRequest("java");

        NovoAutorRequest autorRequest = new NovoAutorRequest("alberto","email@email","livro spring");

        NovoLivroRequest novoLivroRequest =
                new NovoLivroRequest(1L, 1L, "titulo", "resumo", "sumario",
                        BigDecimal.TEN.multiply(new BigDecimal(10)), 100, "978-85-94120-00-7", LocalDate.now().plusDays(1));

        String payloadLivrero = mapper.writeValueAsString(novoLivroRequest);

        String payloadCategoria = mapper.writeValueAsString(categoriaRequest);

        String payloadAutor = mapper.writeValueAsString(autorRequest);

        MockHttpServletRequestBuilder conteudoAutores = MockMvcRequestBuilders.post("/api/autores").content(payloadAutor).contentType(MediaType.APPLICATION_JSON);

        MockHttpServletRequestBuilder conteudoCategorias = MockMvcRequestBuilders.post("/api/categorias").content(payloadCategoria).contentType(MediaType.APPLICATION_JSON);

        MockHttpServletRequestBuilder conteudoLivros = MockMvcRequestBuilders.post("/api/livros").content(payloadLivrero).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(conteudoCategorias).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(conteudoAutores).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(conteudoLivros).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(conteudoLivros).andExpect(MockMvcResultMatchers.status().is4xxClientError());

        Assertions.assertEquals(1,repository.findAll().size());



    }
}
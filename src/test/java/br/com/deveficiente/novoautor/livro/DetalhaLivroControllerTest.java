package br.com.deveficiente.novoautor.livro;

import br.com.deveficiente.novoautor.autor.NovoAutorRequest;
import br.com.deveficiente.novoautor.categoria.NovaCategoriaRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DetalhaLivroControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("exibe detalhes do livro corretamente")
    void test1() throws Exception {

        var autorRequest = new NovoAutorRequest("alberto", "email@email", "descricao");
        var payloadAutor = mapper.writeValueAsString(autorRequest);
        var conteudoAutor =
                post("/api/autores")
                        .content(payloadAutor)
                        .contentType(MediaType.APPLICATION_JSON);

        var categoriaRequest = new NovaCategoriaRequest("categoria");
        String payloadCategoria = mapper.writeValueAsString(categoriaRequest);
        var conteudoCat = post("/api/categorias")
                .content(payloadCategoria)
                .contentType(MediaType.APPLICATION_JSON);

        var novoLivroRequest =
                new NovoLivroRequest(1L, 1L, "titulo", "resumo", "sumario",
                        BigDecimal.TEN.multiply(new BigDecimal(10)), 100, "978-85-94120-00-7", LocalDate.now().plusDays(1));
        var paylaodLivro = mapper.writeValueAsString(novoLivroRequest);
        var conteudoLivro =
                post("/api/livros")
                        .content(paylaodLivro)
                        .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(conteudoAutor).andExpect(status().is2xxSuccessful());

        mockMvc.perform(conteudoCat).andExpect(status().is2xxSuccessful());

        mockMvc.perform(conteudoLivro).andExpect(status().is2xxSuccessful());

        MockHttpServletRequestBuilder resultado = get("/api/livros/1").accept(MediaType.APPLICATION_JSON);

        Map<String,Object> autor = Map.of("nome","alberto","descricao","descricao");

        Map<String,Object> detalhesLivro = Map.of("titulo","titulo"
                ,"isbn","978-85-94120-00-7",
                "numerosDePaginas",100,
                "preco",BigDecimal.TEN.multiply(new BigDecimal(10)),
                "resumo","resumo",
                "sumario","sumario",
                "dataPublicacao",LocalDate.now().plusDays(1),
                "autor",autor);

        String jsonEspertado = mapper.writeValueAsString(detalhesLivro);

        mockMvc.perform(resultado).andExpect(content().json(jsonEspertado));




    }
}
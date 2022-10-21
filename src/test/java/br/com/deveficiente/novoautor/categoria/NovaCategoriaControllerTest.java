package br.com.deveficiente.novoautor.categoria;

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

import java.util.HashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class NovaCategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private CategoriaRepository repository;


    @Test
    @DisplayName("deve cadastrar uma categoria")
    public void test1() throws Exception {

        NovaCategoriaRequest request = new NovaCategoriaRequest("categoria");
        String payload = mapper.writeValueAsString(request);

        MockHttpServletRequestBuilder categoriaRequest = MockMvcRequestBuilders.post("/api/categorias").content(payload).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(categoriaRequest).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(categoriaRequest).andExpect(MockMvcResultMatchers.status().is4xxClientError());

        List<Categoria> categorias = repository.findAll();

        Assertions.assertEquals(1,categorias.size());
    }
}
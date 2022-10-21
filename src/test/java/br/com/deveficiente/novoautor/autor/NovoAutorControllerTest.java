package br.com.deveficiente.novoautor.autor;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class NovoAutorControllerTest {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private MockMvc mockMvc;


    @BeforeEach
    void setUp() {
        repository.deleteAll();


    }

    @Test
    @DisplayName("deve cadastra um autor")
    void test1() throws Exception {


        NovoAutorRequest autorRequest = new NovoAutorRequest("thigas", "email@email", "descricao");
        String payload = mapper.writeValueAsString(autorRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/autores").content(payload).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is4xxClientError());

        List<Autor> autors = repository.findAll();

        Assertions.assertEquals(1, autors.size());
    }
}
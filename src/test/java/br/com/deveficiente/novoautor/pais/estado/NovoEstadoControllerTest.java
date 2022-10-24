package br.com.deveficiente.novoautor.pais.estado;

import br.com.deveficiente.novoautor.pais.NovoPaisRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class NovoEstadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("deveria cadastrar um estado")
    void test1() throws Exception {

        NovoPaisRequest paisRequest = new NovoPaisRequest("BR");

        String payload = mapper.writeValueAsString(paisRequest);

        MockHttpServletRequestBuilder request = post("/api/paises")
                .content(payload)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(status().is2xxSuccessful());

        NovoEstadoRequest estadoRequest = new NovoEstadoRequest("MG",1L);

        String payloadEstado = mapper.writeValueAsString(estadoRequest);

        MockHttpServletRequestBuilder requestBuilder = post("/api/estados")
                .content(payloadEstado)
                .contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(requestBuilder).andExpect(status().is2xxSuccessful());
        mockMvc.perform(requestBuilder).andExpect(status().is4xxClientError());

    }
}
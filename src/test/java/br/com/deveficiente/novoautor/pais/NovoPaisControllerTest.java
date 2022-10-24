package br.com.deveficiente.novoautor.pais;

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

@SpringBootTest
@AutoConfigureMockMvc
class NovoPaisControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;


    @Test
    @DisplayName("deve cadastrar um pais")
    void test1() throws Exception {

        NovoPaisRequest paisRequest = new NovoPaisRequest("Brasil");

        String payload = mapper.writeValueAsString(paisRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/api/paises").content(payload).contentType(MediaType.APPLICATION_JSON);

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());

        mockMvc.perform(request).andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }
}
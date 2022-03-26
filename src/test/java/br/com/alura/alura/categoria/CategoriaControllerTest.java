package br.com.alura.alura.categoria;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;

@SpringBootTest
@AutoConfigureMockMvc
class CategoriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void retornar200TrazendoTodasCategoriasCadastradas() throws Exception {
        URI uri = new URI("/categorias");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void retornar200TrazendoUmaCategoriaCadastrada() throws Exception {
        URI uri = new URI("/categorias/1");
        mockMvc.perform(MockMvcRequestBuilders.get(uri)).andExpect(MockMvcResultMatchers.status().is(200));
    }

    @Test
    void retornar201CriandoUmaNovaCategoria() throws Exception {
        URI uri = new URI("/categorias");
        mockMvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\r\n    \"titulo\": \"terror\",\r\n    \"cor\": \"#D13817\"\r\n}"))
                        .andExpect(MockMvcResultMatchers.status().is(201));
    }

    @Test
    void deveriaRetornar200AoAtualizarUmaCategoria() throws Exception {
        URI uri = new URI("/categorias/3");

        mockMvc.perform(MockMvcRequestBuilders.put(uri).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content("{\r\n    \"titulo\": \"terror\",\r\n    \"cor\": \"#D13817\"\r\n}"))
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}

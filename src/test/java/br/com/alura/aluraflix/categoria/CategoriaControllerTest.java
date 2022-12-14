package br.com.alura.aluraflix.categoria;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.sql.DataSource;
import java.net.URI;


@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc(addFilters = false)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CategoriaControllerTest {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    void initDatabase() {
        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("data.sql"));
        populator.execute(dataSource);
    }


    // TESTES PARA VERIFICAR O CRUD

    @Test
    void deveDevolverStatusCode200AoBuscarTodasCategorias() throws Exception {
        URI uri = new URI("/categorias");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    void deveBuscarUmaCategoriaEspecificaPeloId() throws Exception {
        URI uri = new URI("/categorias/1");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}"));
    }

    @Test
    void deveCriarUmaCategoriaERetornarACategoriaCriada() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"titulo\":\"Categoria4\",\"cor\":\"BRANCA\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(201))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\":4,\"titulo\":\"Categoria4\",\"cor\":\"BRANCA\"}"));
    }

    @Test
    void deveAtualizarUmaCategoria() throws Exception {
        URI uri = new URI("/categorias/2");
        String json = "{\"titulo\":\"Categoria2Atualizada\",\"cor\":\"BRANCAAtualizada\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\":2,\"titulo\":\"Categoria2Atualizada\",\"cor\":\"BRANCAAtualizada\"}"));
    }

    @Test
    void deveDeletarUmaCategoria() throws Exception {
        URI uri = new URI("/categorias/3");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    void deveBuscarVideosPeloIdDaCategoria() throws Exception {
        URI uri = new URI("/categorias/1/videos");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"id\":1,\"titulo\":\"Video1\",\"descricao\":\"Primeiro video\",\"url\":\"https://www.video1.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}},{\"id\":2,\"titulo\":\"Video2\",\"descricao\":\"Segundo video\",\"url\":\"https://www.video2.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}},{\"id\":3,\"titulo\":\"Video3\",\"descricao\":\"Terceiro video\",\"url\":\"https://www.video3.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}}]"));
    }


    // TESTES PARA VERIFICAR O EXCEPTION HANDLER

    @Test
    void deveDevolverStatusCode404AoNaoEncontrarOIdDaCategoria() throws Exception {
        URI uri = new URI("/categorias/10");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    void deveTentarAtualizarUmaCategoriaDeIdInexistente() throws Exception {
        URI uri = new URI("/categorias/10");
        String json = "{\"titulo\":\"Categoria10Atualizada\",\"cor\":\"BRANCAAtualizada\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    void deveTentarDeletarUmaCategoriaDeIdInexistente() throws Exception {
        URI uri = new URI("/categorias/10");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }


    // TESTES PARA VERIFICAR O VALIDATION

    @Test
    void deveTentarCriarUmaCategoriaSemPassarAlgumCampoObrigatorio() throws Exception {
        URI uri = new URI("/categorias");
        String json = "{\"titulo\":\"Categoria5Atualizada\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"cor\",\"error\":\"must not be blank\"}]"));
    }

    @Test
    void deveTentarAtualizarUmaCategoriaSemPassarAlgumCampoObrigatorio() throws Exception {
        URI uri = new URI("/categorias/2");
        String json = "{\"titulo\":\"Categoria2Atualizada\"}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"cor\",\"error\":\"must not be blank\"}]"));
    }
}

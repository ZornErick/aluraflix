package br.com.alura.aluraflix.video;

import org.junit.jupiter.api.*;
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
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VideoControllerTest {

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
    void deveDevolverStatusCode200AoBuscarTodosVideos() throws Exception {
        URI uri = new URI("/videos");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(200));
    }

    @Test
    void deveBuscarUmVideoPeloTituloECheckarSeOVideoDevolvidoEstaComTituloCorreto() throws Exception {
        URI uri = new URI("/videos/?search=Video1");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"id\":1,\"titulo\":\"Video1\",\"descricao\":\"Primeiro video\",\"url\":\"https://www.video1.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}}]"));
    }

    @Test
    void deveBuscarUmVideoEspecificoPeloId() throws Exception {
        URI uri = new URI("/videos/1");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"id\":1,\"titulo\":\"Video1\",\"descricao\":\"Primeiro video\",\"url\":\"https://www.video1.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}}"));
    }

    @Test
    void deveCriarUmVideoERetornarOVideoCriado() throws Exception {
        URI uri = new URI("/videos");
        String json = "{\"titulo\":\"Video4\",\"descricao\":\"Quarto video\",\"url\":\"https://www.video4.com\",\"categoriaId\":1}";
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
                        .json("{\"id\":4,\"titulo\":\"Video4\",\"descricao\":\"Quarto video\",\"url\":\"https://www.video4.com\",\"categoria\":{\"id\":1,\"titulo\":\"Categoria1\",\"cor\":\"BRANCA\"}}"));
    }

    @Test
    void deveAtualizarUmVideo() throws Exception {
        URI uri = new URI("/videos/2");
        String json = "{\"titulo\":\"Video2Atualizado\",\"descricao\":\"Segundo video atualizado\",\"url\":\"https://www.video2atualizado.com\",\"categoriaId\":2}";
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
                        .json("{\"id\":2,\"titulo\":\"Video2Atualizado\",\"descricao\":\"Segundo video atualizado\",\"url\":\"https://www.video2atualizado.com\",\"categoria\":{\"id\":2,\"titulo\":\"Categoria2\",\"cor\":\"BRANCA\"}}"));
    }

    @Test
    void deveDeletarUmVideo() throws Exception {
        URI uri = new URI("/videos/3");
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


    // TESTES PARA VERIFICAR O EXCEPTION HANDLER

    @Test
    void deveDevolverStatusCode404AoNaoEncontrarOIdDoVideo() throws Exception {
        URI uri = new URI("/videos/10");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }

    @Test
    void deveTentarCriarUmVideoPassandoOIdDeUmaCategoriaInexistente() throws Exception {
        URI uri = new URI("/videos");
        String json = "{\"titulo\":\"Video5\",\"descricao\":\"Quinto video\",\"url\":\"https://www.video5.com\",\"categoriaId\":10}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"status\":404,\"error\":\"NOT_FOUND\",\"message\":\"Categoria não encontrada!\"}"));
    }

    @Test
    void deveTentarAtualizarUmVideoDeIdInexistente() throws Exception {
        URI uri = new URI("/videos/10");
        String json = "{\"titulo\":\"Video10Atualizado\",\"descricao\":\"Decimo video atualizado\",\"url\":\"https://www.video10atualizado.com\",\"categoriaId\":2}";
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
    void deveTentarAtualizarUmVideoPassandoOIdDeUmaCategoriaInexistente() throws Exception {
        URI uri = new URI("/videos/2");
        String json = "{\"titulo\":\"Video2Atualizado\",\"descricao\":\"Segundo video atualizado\",\"url\":\"https://www.video2atualizado.com\",\"categoriaId\":10}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("{\"status\":404,\"error\":\"NOT_FOUND\",\"message\":\"Categoria não encontrada!\"}"));
    }

    @Test
    void deveTentarDeletarUmVideoDeIdInexistente() throws Exception {
        URI uri = new URI("/videos/10");
        mockMvc
                .perform(MockMvcRequestBuilders
                        .delete(uri))
                .andExpect(MockMvcResultMatchers
                        .status()
                        .is(404));
    }


    // TESTES PARA VERIFICAR O VALIDATION

    @Test
    void deveTentarCriarUmVideoSemPassarAlgumCampoObrigatorio() throws Exception {
        URI uri = new URI("/videos");
        String json = "{\"descricao\":\"Quinto video\",\"url\":\"https://www.video5.com\",\"categoriaId\":1}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"titulo\",\"error\":\"must not be blank\"}]"));
    }

    @Test
    void deveTentarCriarUmVideoPassandoUmaURLInvalida() throws Exception {
        URI uri = new URI("/videos");
        String json = "{\"titulo\":\"Video5\",\"descricao\":\"Quinto video\",\"url\":\"video5\",\"categoriaId\":1}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"url\",\"error\":\"must be a valid URL\"}]"));
    }

    @Test
    void deveTentarAtualizarUmVideoSemPassarAlgumCampoObrigatorio() throws Exception {
        URI uri = new URI("/videos/2");
        String json = "{\"descricao\":\"Quinto video\",\"url\":\"https://www.video5.com\",\"categoriaId\":1}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"titulo\",\"error\":\"must not be blank\"}]"));
    }

    @Test
    void deveTentarAtualizarUmVideoPassandoUmaURLInvalida() throws Exception {
        URI uri = new URI("/videos/2");
        String json = "{\"titulo\":\"Video2\",\"descricao\":\"Segundo video\",\"url\":\"video2\",\"categoriaId\":1}";
        mockMvc
                .perform(MockMvcRequestBuilders
                        .put(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers
                        .content()
                        .json("[{\"field\":\"url\",\"error\":\"must be a valid URL\"}]"));
    }
}

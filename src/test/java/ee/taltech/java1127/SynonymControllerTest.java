package ee.taltech.java1127;

import ee.taltech.java1127.controller.SynonymController;
import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
import ee.taltech.java1127.service.SynonymService;
import ee.taltech.java1127.service.WordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SynonymController.class, secure = false)
public class SynonymControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    SynonymController synonymController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    WordRepository wordRepository;

    @MockBean
    SynonymRepository synonymRepository;

    @MockBean
    private SynonymService synonymService;

    @MockBean
    private WordService wordService;

    @Test
    public void contexLoads() throws Exception {
        assertThat(synonymController).isNotNull();
    }

    private User user1 = new User("egletest@gmail.com");
    private Word word1 = new Word("test1", 1L, true);
    private Word word2 = new Word("test2", 1L, true);
    private Word word3 = new Word("test3", 1L, true);
    private Synonym synonym1 = new Synonym(word1.getWordId(), "testime1", 1L, true);
    private Synonym synonym2 = new Synonym(word2.getWordId(), "testime2", 1L, true);
    private Synonym synonym3 = new Synonym(word3.getWordId(), "testime3", 1L, true);

    @Test
    public void findAllSynonyms() throws Exception {

        when(synonymService.getAllSynonyms()).thenReturn(Arrays.asList(synonym1, synonym2, synonym3));

        mockMvc.perform(MockMvcRequestBuilders.get("/synonyms")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void getSynonymByIdTest() throws Exception {

        synonym1.setSynonymId(8L);
        Mockito.when(synonymService.getById(8L)).thenReturn(synonym1);
        mockMvc.perform(get("/synonyms/id/{synonymId}", 8L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("synonymId").value(8));
    }

    @Test
    public void getSynonymsByWordIdTest() throws Exception {
        word1.setWordId(1L);
        synonym1.setSynonymId(4L);
        Mockito.when(synonymService.getSynonymsByWord(1L)).thenReturn(List.of(synonym1));
        mockMvc.perform(get("/synonyms/{wordId}", 1L)
                .accept(MediaType.ALL))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    public void addSynonymTest() throws Exception {
        String json = "{\"wordId\": 1,\n" +
                "    \"synonym\": \"vladislav1\",\n" +
                "    \"userId\": 1,\n" +
                "    \"active\": true\n" +
                "  }";
        mockMvc.perform(post("/synonyms")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void deleteSynonymTest() throws Exception {
        synonym2.setSynonymId(2L);
        mockMvc.perform(delete("/synonyms/{synonymId}", 2L)
                .contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk());
    }

    @Test
    public void when_saving_word_id_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"wordId\":,\"synonym\": \"vladislav1\",\"userId\": 1,\"active\": true}";
        this.mockMvc.perform(post("/synonyms")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_saving_synonym_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"wordId\": 1,\"synonym\":,\"userId\": 1,\"active\": true}";
        this.mockMvc.perform(post("/synonyms")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_saving_user_id_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"wordId\": 1,\"synonym\": \"vladislav1\",\"userId\":,\"active\": true}";
        this.mockMvc.perform(post("/synonyms")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}

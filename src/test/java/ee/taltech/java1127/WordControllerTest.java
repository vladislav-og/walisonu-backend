package ee.taltech.java1127;

import ee.taltech.java1127.controller.WordController;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = WordController.class, secure = false)
public class WordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    WordController wordController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    WordRepository wordRepository;

    @MockBean
    SynonymRepository synonymRepository;

    @MockBean
    private WordService wordService;

    @Test
    public void contexLoads() throws Exception {
        assertThat(wordController).isNotNull();
    }

    @Test
    public void findAllWords() throws Exception {
        Word word1 = new Word("test1", true);
        Word word2 = new Word("test2", true);
        Word word3 = new Word("test3", true);
        when(wordService.getAllWords()).thenReturn(Arrays.asList(word1, word2, word3));

        mockMvc.perform(MockMvcRequestBuilders.get("/words")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void getWordByIdTest() throws Exception {

        Word mockedWord = new Word("test1", true);
        mockedWord.setWord_id(1L);
        Mockito.when(wordService.getById(1L)).thenReturn(mockedWord);
        mockMvc.perform(get("/words/{word_id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("word_id").value(1));
    }

    @Test
    public void addWordTest() throws Exception {

        String json = "{\"name\":\"TestSõna\", \"isActive\":\"true\"}";
        mockMvc.perform(post("/words")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }
}

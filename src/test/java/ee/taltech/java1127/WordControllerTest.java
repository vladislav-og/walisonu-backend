package ee.taltech.java1127;

import ee.taltech.java1127.controller.WordController;
import ee.taltech.java1127.dto.WordDto;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
import ee.taltech.java1127.security.JwtTokenProvider;
import ee.taltech.java1127.security.MyUserDetailsService;
import ee.taltech.java1127.security.Role;
import ee.taltech.java1127.service.WordService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
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

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @Test
    public void contexLoads() throws Exception {
        assertThat(wordController).isNotNull();
    }

    @Test
    public void findAllWords() throws Exception {
        User user = new User("egletest@mail.com");
        Word word1 = new Word("test1", 1L, true);
        Word word2 = new Word("test2", 1L, true);
        Word word3 = new Word("test3",1L,  true);
        when(wordService.getAllWords()).thenReturn(Arrays.asList(word1, word2, word3));

        mockMvc.perform(MockMvcRequestBuilders.get("/words")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void getWordByIdTest() throws Exception {
        User user = new User("egletest@mail.com");
        Word mockedWord = new Word("test1", 1L,true);
        mockedWord.setWordId(1L);
        Mockito.when(wordService.getById(1L)).thenReturn(mockedWord);
        mockMvc.perform(get("/words/{wordId}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("wordId").value(1));
    }

    @Test
    public void addWordTest() throws Exception {

        String json = "{\"userId\":1,\"name\":\"TestSõna\", \"isActive\":\"true\"}";
        mockMvc.perform(post("/words")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void when_saving_word_name_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"userId\":1,\"name\":, \"isActive\":\"true\"}";
        this.mockMvc.perform(post("/words")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void when_saving_user_id_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"userId\":,\"name\":, \"isActive\":\"true\"}";
        this.mockMvc.perform(post("/words")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }



}

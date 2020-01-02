package ee.taltech.java1127;

import ee.taltech.java1127.controller.UserController;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
import ee.taltech.java1127.security.JwtTokenProvider;
import ee.taltech.java1127.security.MyUserDetailsService;
import ee.taltech.java1127.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    UserController userController;

    @MockBean
    UserRepository userRepository;

    @MockBean
    WordRepository wordRepository;

    @MockBean
    SynonymRepository synonymRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private MyUserDetailsService myUserDetailsService;

    @MockBean
    private JwtTokenProvider jwtTokenProvider;

    @MockBean
    private AuthenticationManager authenticationManager;

    @Test
    public void contexLoads() throws Exception {
        assertThat(userController).isNotNull();
    }

    @Test
    public void findAllUsers() throws Exception {
        User mockedUser1 = new User("test1@test.ee");
        User mockedUser2 = new User("test2@test.ee");
        User mockedUser3 = new User("test3@test.ee");
        when(userService.getAllUsers()).thenReturn(Arrays.asList(mockedUser1, mockedUser2, mockedUser3));

        mockMvc.perform(MockMvcRequestBuilders.get("/users")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void getUserByIdTest() throws Exception {

        User mockedUser = new User("tes@test.ee");
        mockedUser.setUserId(1L);
        Mockito.when(userService.getById(1L)).thenReturn(mockedUser);
        mockMvc.perform(get("/users/{userId}", 1L)
                .accept(APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("userId").value(1));
    }


    @Test
    public void addUserTest() throws Exception {

        String json = "{\"email\":\"test@test.ee\",\"password\":\"test\"}";
        mockMvc.perform(post("/users/register")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }



   @Test
    public void when_saving_email_cannot_be_empty_thenBadRequest() throws Exception {
        String json = "{\"email\"}";
        this.mockMvc.perform(post("/users/register")
                .contentType(APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

}

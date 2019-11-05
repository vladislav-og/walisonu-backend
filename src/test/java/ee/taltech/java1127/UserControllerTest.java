package ee.taltech.java1127;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ee.taltech.java1127.controller.UserController;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
import ee.taltech.java1127.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;
import java.util.Arrays;

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
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)));

    }

    @Test
    public void getUserByIdTest() throws Exception {

        User mockedUser = new User("tes@test.ee");
        mockedUser.setUser_id(1L);
        Mockito.when(userService.getById(1L)).thenReturn(mockedUser);
        mockMvc.perform(get("/users/{user_id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(System.out::println)
                .andExpect(MockMvcResultMatchers.jsonPath("user_id").value(1));
    }


    @Test
    public void addUserTest() throws Exception {

        String json = "{\"email\":\"tes@test.ee\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk())
                .andReturn();
    }

   /* @Test
    public void email_cannot_be_empty() throws Exception {

        String json = "{}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @Test
    public void user_email_has_to_be_unique() throws Exception {
        User mockedUser = new User("tes@test.ee");
        String json = "{\"email\":\"tes@test.ee\"}";
        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andReturn();
    }
*/
    /*@Test
    public void user_must_have_a_email() {
        UserDto userDto = new UserDto();
        ResponseEntity<UserDto> entity = template.exchange("/users", HttpMethod.POST, new HttpEntity<>(userDto), UserDto.class);
        assertEquals(HttpStatus.BAD_REQUEST, entity.getStatusCode());
    }*/
}

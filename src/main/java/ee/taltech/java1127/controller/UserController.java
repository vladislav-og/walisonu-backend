package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable Long user_id) {
        return userService.getById(user_id);
    }

    @PostMapping
    public UserDto saveUser (@RequestBody UserDto userDto){
        log.error("inside saveUser() method");
        return userService.createNewUser(userDto);
    }

    //TODO fix deleting
    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id){
        log.error("inside deleteUser() method");
        userService.deleteUser(user_id);
    }

}

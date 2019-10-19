package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.service.UserService;
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

@RestController
@RequestMapping("/users")
public class UserController {


    @Resource
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public UserDto saveUser (@RequestBody UserDto userDto){
        return userService.createNewUser(userDto);
    }

    //TODO fix some foigin key problems
    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id){
        userService.deleteUser(user_id);
    }

}

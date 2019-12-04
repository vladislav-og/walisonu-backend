package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.exception.UserValidationException;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.security.Roles;
import ee.taltech.java1127.service.UserService;
import org.springframework.security.access.annotation.Secured;
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

    @Secured(Roles.ROLE_ADMIN)
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Secured(Roles.ROLE_ADMIN)
    @GetMapping("/{user_id}")
    public User getUserById(@PathVariable Long user_id) {
        return userService.getById(user_id);
    }

    @PostMapping("register")
    public void saveUser(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null) {
            throw new UserValidationException();
        }
        if (userDto.getPassword() == null) {
            throw new UserValidationException();
        }
        userService.createNewUser(userDto);
    }

    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
    }

}

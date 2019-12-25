package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.exception.UserValidationException;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.pojo.LoginDetails;
import ee.taltech.java1127.security.JwtTokenProvider;
import ee.taltech.java1127.security.MyUser;
import ee.taltech.java1127.security.MyUserDetailsService;
import ee.taltech.java1127.security.Roles;
import ee.taltech.java1127.security.UserSessionHolder;
import ee.taltech.java1127.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    @Resource
    private UserService userService;

    private AuthenticationManager authenticationManager;
    private MyUserDetailsService myUserDetailsService;
    private JwtTokenProvider jwtTokenProvider;

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
    public UserDto saveUser(@RequestBody UserDto userDto) {
       return userService.createNewUser(userDto);
    }

    @PostMapping("login")
    public LoginDetails login(@RequestBody UserDto userDto) {
        if (userDto.getEmail() == null) {
            throw new UserValidationException();
        }
        if (userDto.getPassword() == null) {
            throw new UserValidationException();
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(userDto.getEmail());
        final String token = jwtTokenProvider.generateToken(userDetails);
        MyUser myUser = (MyUser) userDetails;
        return new LoginDetails(myUser.getUsername(), token, toAuthorities(myUser), myUser.getRole());
    }

    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{user_id}")
    public void deleteUser(@PathVariable Long user_id) {
        userService.deleteUser(user_id);
    }

    @GetMapping("me")
    public MyUser me() {
        return UserSessionHolder.getLoggedInUser();
    }

    private List<String> toAuthorities(MyUser myUser) {
        return myUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

}

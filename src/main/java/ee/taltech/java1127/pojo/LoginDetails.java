package ee.taltech.java1127.pojo;

import ee.taltech.java1127.security.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class LoginDetails {

    private String username;
    private String token;
    private List<String> roles;
    private Role role;

}
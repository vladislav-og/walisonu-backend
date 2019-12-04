package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.User;
import ee.taltech.java1127.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;
    private String password;
    private Role role;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.role = user.getRole();
    }
}

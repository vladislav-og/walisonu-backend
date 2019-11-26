package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String email;

    public UserDto(User user) {
        this.id = user.getUserId();
        this.email = user.getEmail();
    }
}

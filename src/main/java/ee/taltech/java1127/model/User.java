package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.security.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_sequence", allocationSize = 1)
    @Column(name = "user_id")
    private Long userId;
    @Email
    @Column(unique = true)
    private String email;
    private String password;
    private Role role;


    public User(@Email String email) {
        this.email = email;
    }

    public User(Long id, String email) {
        this.userId = id;
        this.email = email;
    }

    public User(@Email String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(UserDto userDto) {
        this.userId = userDto.getId();
        this.email = userDto.getEmail();
        this.password = userDto.getPassword();
        this.role=userDto.getRole();
    }
}

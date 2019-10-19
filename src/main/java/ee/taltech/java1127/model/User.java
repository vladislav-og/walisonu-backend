package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    @SequenceGenerator(name = "user_seq", sequenceName = "users_sequence", allocationSize = 1)
    private Long user_id;
    @Email
    @Column(unique = true)
    private String email;


    public User(@Email String email) {
        this.email = email;
    }

    public User(Long id, String email) {
        this.user_id = id;
        this.email = email;
    }

    public User(UserDto userDao) {
        this.user_id = userDao.getId();
        this.email = userDao.getEmail();
    }
}

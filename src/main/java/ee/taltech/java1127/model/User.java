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
import javax.validation.constraints.Email;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Users {


    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    //@SequenceGenerator(name = "user_seq", sequenceName = "users_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long user_id;
    @Column(unique = true)
    private String email;


    public Users(String email) {
        this.email = email;
    }

    public Users(Long id, String email) {
        this.user_id = id;
        this.email = email;
    }

    public Users(UserDto userDao) {
        this.user_id = userDao.getId();
        this.email = userDao.getEmail();
    }
}

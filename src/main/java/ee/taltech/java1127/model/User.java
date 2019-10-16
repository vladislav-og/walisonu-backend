package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.UserDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long user_id;
    @Email
    private String email;


    public User() {
    }

    public User(@Email String email) {
        this.email = email;
    }

    public User(Long id, String email) {
        this.user_id = id;
        this.email = email;
    }

    public User(UserDto userDao){
        this.user_id = userDao.getId();
        this.email = userDao.getEmail();
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

package ee.taltech.java1127.dao;

import ee.taltech.java1127.model.User;

public class UserDao {

    private Long id;
    private String email;

    public UserDao() {
    }

    public UserDao(User user) {
        this.id = user.getUser_id();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

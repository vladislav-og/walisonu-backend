package ee.taltech.java1127.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;



@Entity
public class Word {

    @Id
    @GeneratedValue
    private Long word_id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive = true;


    public Word() {
    }

    public Word(String name, User user, boolean isActive) {
        this.name = name;
        this.user = user;
        this.isActive = isActive;
    }

    public Long getWord_id() {
        return word_id;
    }

    public void setWord_id(Long word_id) {
        this.word_id = word_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}

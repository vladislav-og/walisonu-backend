package ee.taltech.java1127.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Set;


@Entity
public class Word {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive = true;
    @OneToMany(mappedBy = "word")
    private Set<Synonym> synonyms;


    public Word() {
    }

    public Word(Long id, String name, User user, boolean isActive) {
        this.id = id;
        this.name = name;
        this.user = user;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;

public class WordDto {
    private Long id;
    private String name;
    private User user;
    private boolean isActive = true;

    public WordDto() {
    }

    public WordDto(Word word) {
        this.id = word.getWord_id();
        this.name = word.getName();
        this.user = word.getUser();
        this.isActive = word.isActive();
    }

    public Long getSynonym_id() {
        return id;
    }

    public void setSynonym_id(Long id) {
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

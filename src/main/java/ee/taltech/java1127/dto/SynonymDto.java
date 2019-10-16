package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;

public class SynonymDto {

    private Long id;
    private Word word;
    private String synonym;
    private User user;
    private boolean isActive = true;

    public SynonymDto() {
    }

    public SynonymDto(Synonym synonym) {
        this.id = synonym.getSynonym_id();
        this.word = synonym.getWord();
        this.synonym = synonym.getSynonym();
        this.user = synonym.getUser();
        this.isActive = synonym.isActive();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
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

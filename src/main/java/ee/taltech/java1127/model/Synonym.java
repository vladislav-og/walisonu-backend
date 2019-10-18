package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.SynonymDto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Synonym {

    @GeneratedValue
    @Id
    private Long synonym_id;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
    private String synonym;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive = true;


    public Synonym() {
    }

    /*public Synonym(Word word, String synonym, User user, boolean isActive) {
        this.word = word;
        this.synonym = synonym;
        this.user = user;
        this.isActive = isActive;
    }*/

    public Synonym(Word word, String synonym, boolean isActive) {
        this.word = word;
        this.synonym = synonym;
        this.isActive = isActive;
    }

    public Synonym(SynonymDto synonymDao) {
        this.word = synonymDao.getWord();
        this.synonym = synonymDao.getSynonym();
        //this.user = synonymDao.getUser();
        this.isActive = synonymDao.isActive();
    }

    public Long getSynonym_id() {
        return synonym_id;
    }

    public void setSynonym_id(Long synonym_id) {
        this.synonym_id = synonym_id;
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


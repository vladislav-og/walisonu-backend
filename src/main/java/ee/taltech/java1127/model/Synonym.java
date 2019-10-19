package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.SynonymDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Synonym {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "synonym_seq")
    @SequenceGenerator(name = "synonym_seq", sequenceName = "synonym_sequence", allocationSize = 1)
    private Long synonym_id;
    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
    private String synonym;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive = true;




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

}


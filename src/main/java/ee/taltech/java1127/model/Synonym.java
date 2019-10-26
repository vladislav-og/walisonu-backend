package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.SynonymDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "synonyms", schema="public")
public class Synonym {


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "synonym_seq")
    @SequenceGenerator(name = "synonym_seq", sequenceName = "synonym_sequence", allocationSize = 1)
    private Long synonym_id;
    @JoinColumn(name = "word_id")
    private Long word_id;
    private String synonym;
    @JoinColumn(name = "user_id")
    private Long user_id;
    @Column(name="isactive")
    private boolean isActive = true;




    public Synonym(Long word_id, String synonym, Long user_id, boolean isActive) {
        this.word_id = word_id;
        this.synonym = synonym;
        this.user_id = user_id;
        this.isActive = isActive;
    }

    /*public Synonym(Long word, String synonym, boolean isActive) {
        this.word_id = word;
        this.synonym = synonym;
        this.isActive = isActive;
    }*/

    public Synonym(SynonymDto synonymDto) {
        this.word_id = synonymDto.getWord_id();
        this.synonym = synonymDto.getSynonym();
        this.user_id = synonymDto.getUser_id();
        this.isActive = synonymDto.isActive();
    }

}


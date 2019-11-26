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
    private Long synonymId;
    @JoinColumn(name = "word_id")
    @Column(name="word_id")
    private Long wordId;
    private String synonym;
    @JoinColumn(name = "userId")
    @Column(name="user_id")
    private Long userId;
    @Column(name="isactive")
    private boolean isActive = true;




    public Synonym(Long wordId, String synonym, Long userId, boolean isActive) {
        this.wordId = wordId;
        this.synonym = synonym;
        this.userId = userId;
        this.isActive = isActive;
    }


    public Synonym(SynonymDto synonymDto) {
        this.wordId = synonymDto.getWordId();
        this.synonym = synonymDto.getSynonym();
        this.userId = synonymDto.getUserId();
        this.isActive = synonymDto.isActive();
    }

}


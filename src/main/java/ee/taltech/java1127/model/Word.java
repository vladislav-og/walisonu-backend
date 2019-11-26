package ee.taltech.java1127.model;

import ee.taltech.java1127.dto.WordDto;
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
@Table(name = "words", schema = "public")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_seq")
    @SequenceGenerator(name = "word_seq", sequenceName = "word_sequence", allocationSize = 1)
    @Column(name="word_id")
    private Long wordId;
    @Column(unique = true)
    private String name;
    @JoinColumn(name = "user_id")
    private Long userId;
    @Column(name = "isactive")
    private boolean isActive = true;


    public Word(String name, Long userId, boolean isActive) {
        this.name = name;
        this.userId = userId;
        this.isActive = isActive;
    }

    public Word(WordDto wordDto) {
        this.name = wordDto.getName();
        this.userId = wordDto.getUserId();
        this.isActive = wordDto.isActive();
    }

}

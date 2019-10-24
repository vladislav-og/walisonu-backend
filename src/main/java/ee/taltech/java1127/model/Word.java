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
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "words", schema="public")
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_seq")
    @SequenceGenerator(name = "word_seq", sequenceName = "word_sequence", allocationSize = 1)
    private Long word_id;
    @Column(unique=true)
    private String name;
    //@ManyToOne
    @JoinColumn(name = "user_id")
    private Long user_id;
    @Column(name="isactive")
    private boolean isActive = true;


    public Word(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    /*public Word(String name, Long user_id, boolean isActive) {
        this.name = name;
        this.user_id = user_id;
        this.isActive = isActive;
    }*/

    public Word(WordDto wordDto) {
        this.name = wordDto.getName();
        //this.user_id = wordDto.getUser_id();
        this.isActive = wordDto.isActive();
    }

}

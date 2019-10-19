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


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Word {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_seq")
    @SequenceGenerator(name = "word_seq", sequenceName = "word_sequence", allocationSize = 1)
    private Long word_id;
    @Column(unique=true)
    private String name;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private boolean isActive = true;


    public Word(String name, boolean isActive) {
        this.name = name;
        this.isActive = isActive;
    }

    /*public Word(String name, User user, boolean isActive) {
        this.name = name;
        this.user = user;
        this.isActive = isActive;
    }*/

    public Word(WordDto wordDto) {
        this.name = wordDto.getName();
        //this.user = wordDto.getUser();
        this.isActive = wordDto.isActive();
    }

}

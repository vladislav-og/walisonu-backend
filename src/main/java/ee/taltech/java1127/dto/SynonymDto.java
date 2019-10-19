package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SynonymDto {

    private Long id;
    private Word word;
    private String synonym;
    private User user;
    private boolean isActive = true;


    public SynonymDto(Synonym synonym) {
        this.id = synonym.getSynonym_id();
        this.word = synonym.getWord();
        this.synonym = synonym.getSynonym();
        //this.user = synonym.getUser();
        this.isActive = synonym.isActive();
    }
}

package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.Synonym;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SynonymDto {

    private Long id;
    private Long word_id;
    private String synonym;
    private Long user_id;
    private boolean isActive = true;


    public SynonymDto(Synonym synonym) {
        this.id = synonym.getSynonym_id();
        this.word_id = synonym.getWord_id();
        this.synonym = synonym.getSynonym();
        //this.user_id = synonym.getUser_id();
        this.isActive = synonym.isActive();
    }
}

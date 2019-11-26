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
    private Long wordId;
    private String synonym;
    private Long userId;
    private boolean isActive = true;


    public SynonymDto(Synonym synonym) {
        this.id = synonym.getSynonymId();
        this.wordId = synonym.getWordId();
        this.synonym = synonym.getSynonym();
        this.userId = synonym.getUserId();
        this.isActive = synonym.isActive();
    }
}

package ee.taltech.java1127.dto;

import ee.taltech.java1127.model.Word;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WordDto {
    private Long id;
    private String name;
    private Long userId;
    private boolean isActive = true;

    public WordDto(Word word) {
        this.id = word.getWordId();
        this.name = word.getName();
        this.userId = word.getUserId();
        this.isActive = word.isActive();
    }
}

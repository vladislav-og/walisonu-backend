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
    private Long user_id;
    private boolean isActive = true;

    public WordDto(Word word) {
        this.id = word.getWord_id();
        this.name = word.getName();
        this.user_id = word.getUser_id();
        this.isActive = word.isActive();
    }
}

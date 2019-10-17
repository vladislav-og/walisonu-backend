package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.WordDto;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.service.WordService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/words")
public class WordController {

    @Resource
    private WordService wordService;

    @GetMapping
    public List<Word> getAllWords() {
        return wordService.getAllWords();
    }

    @GetMapping("/{word_id}")
    public Word getWordById(@PathVariable Long word_id) {
        return wordService.getById(word_id);
    }

    @PostMapping
    public WordDto saveWord(@RequestBody WordDto wordDto) {
        return wordService.createNewWord(wordDto);
    }

    //TODO fix Delete not working
    @DeleteMapping("/{word_id}")
    public void deleteWord(@PathVariable Long word_id){
        wordService.deleteWord(word_id);
    }

}

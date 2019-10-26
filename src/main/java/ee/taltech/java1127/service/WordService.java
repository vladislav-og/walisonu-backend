package ee.taltech.java1127.service;

import ee.taltech.java1127.dto.WordDto;
import ee.taltech.java1127.exception.WordNotFoundException;
import ee.taltech.java1127.exception.WordValidationException;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> getByname(String name) {
        return wordRepository.findByName(name);
    }

    public Word getById(Long id) {
        return wordRepository.findById(id).orElseThrow(WordNotFoundException::new);
    }

    public WordDto createNewWord(WordDto wordDto) {
        Word word = new Word(wordDto);
        if (StringUtils.isEmpty(word.getName())) {
            log.error("Word name is empty");
            throw new WordValidationException();
        } else {
            word.setName(word.getName().trim());
            word.setName(word.getName().substring(0, 1).toUpperCase() + word.getName().substring(1));
        }
        /*if (StringUtils.isEmpty(word.getUser_id())) {
        log.error("Word user is empty");
            throw new WordValidationException();
        }*/
        if (isWordAlreadyAdded(word)) {
            log.error("Word is already added");
            throw new WordValidationException();
        }
        return new WordDto(wordRepository.save(word));
    }

    private boolean isWordAlreadyAdded(Word word) {
        for (Word wordToFind : getAllWords()) {
            if (wordToFind.getName().toLowerCase().equals(word.getName().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void deleteWord(Long word_id) {
        try {
            wordRepository.deleteById(word_id);
        } catch (Exception e) {
            log.error("Word deleting failed!");
        }
    }
}

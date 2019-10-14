package ee.taltech.java1127.service;

import ee.taltech.java1127.exception.WordNotFoundException;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.WordRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class WordService {

    private final WordRepository wordRepository;

    public WordService(WordRepository wordRepository) {
        this.wordRepository = wordRepository;
    }

    public Iterable<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public List<Word> getByname(String name) {
        return wordRepository.findByName(name);
    }

    public Word getById(Long id) {
        return wordRepository.findById(id).orElseThrow(WordNotFoundException::new);
    }

}

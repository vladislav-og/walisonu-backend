package ee.taltech.java1127.controller;

import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/")
public class WordController {
    @Autowired

    @Resource
    private WordService wordService;

    private List<Word> words;

    @GetMapping(path="/all")
    public @ResponseBody
    Iterable<Word> getAllWords() {
        return wordService.getAllWords();
    }



}

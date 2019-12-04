package ee.taltech.java1127.controller;

import ee.taltech.java1127.dto.SynonymDto;
import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.security.Roles;
import ee.taltech.java1127.service.SynonymService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/synonyms")
public class SynonymController {

    @Resource
    private SynonymService synonymService;

    @GetMapping
    public List<Synonym> getAllSynonyms() {
        return synonymService.getAllSynonyms();
    }

    @GetMapping("/id/{synonym_id}")
    public Synonym getSynonymBuId(@PathVariable Long synonym_id) {
        return synonymService.getById(synonym_id);
    }

    @GetMapping("/{word_id}")
    public List getWordSynonyms(@PathVariable Long word_id) {
        return synonymService.getSynonymsByWord(word_id);
    }

    @Secured(Roles.ROLE_USER)
    @PutMapping("/{id}")
    public SynonymDto updateSynonym(@RequestBody SynonymDto synonymDto, @PathVariable Long id) {
        return synonymService.updateSynonym(synonymDto, id);
    }

    @Secured(Roles.ROLE_USER)
    @PostMapping
    public SynonymDto saveSynonym(@RequestBody SynonymDto synonymDto) {
        return synonymService.createNewSynonym(synonymDto);
    }

    @Secured(Roles.ROLE_ADMIN)
    @DeleteMapping("/{synonym_id}")
    public void deleteSynonym(@PathVariable Long synonym_id) {
        synonymService.deleteSynonym(synonym_id);
    }

}

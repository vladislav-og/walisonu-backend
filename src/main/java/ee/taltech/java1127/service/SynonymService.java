package ee.taltech.java1127.service;

import ee.taltech.java1127.dto.SynonymDto;
import ee.taltech.java1127.exception.SynonymNotFoundException;
import ee.taltech.java1127.exception.SynonymValidationException;
import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.SynonymRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class SynonymService {

    private final SynonymRepository synonymRepository;

    public SynonymService(SynonymRepository synonymRepository) {
        this.synonymRepository = synonymRepository;
    }

    public List<Synonym> getAllSynonyms() {
        return synonymRepository.findAll();
    }

    public Synonym getById(Long synonym_id) {
        return synonymRepository.findById(synonym_id).orElseThrow(SynonymNotFoundException::new);
    }

    public List<Synonym> getSynonymsByWord(Long word_id) {
        return synonymRepository.findAll().stream()
                .filter(synonym -> synonym.getWord().getWord_id().equals(word_id))
                .collect(Collectors.toList());
    }

    public SynonymDto createNewSynonym(SynonymDto synonymDao) {
        Synonym synonym = new Synonym(synonymDao);
        if (StringUtils.isEmpty(synonym.getWord())) {
            throw new SynonymValidationException();
        }
        /*if (StringUtils.isEmpty(synonym.getUser())) {
            throw new SynonymValidationException();
        }*/
        if (StringUtils.isEmpty(synonym.getSynonym())) {
            throw new SynonymValidationException();
        }

        if (isSynonymAlreadyAdded(synonym.getWord(), synonym)) {
            throw new SynonymValidationException();
        }
        return new SynonymDto(synonymRepository.save(synonym));
    }

    private boolean isSynonymAlreadyAdded(Word word, Synonym synonym) {
        for (Synonym synonymToFind : getSynonymsByWord(word.getWord_id())) {
            if (synonym.getSynonym().equals(synonymToFind.getSynonym())) {
                return true;
            }
        }
        return false;
    }

    public void deleteSynonym(Long synonym_id) {
        Synonym synonym = getById(synonym_id);
        synonymRepository.delete(synonym);
    }

}

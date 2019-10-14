package ee.taltech.java1127.service;

import ee.taltech.java1127.dao.SynonymDao;
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

    public List<Synonym> getSynonymsByWord (Long word_id){
        return synonymRepository.findAll().stream()
        .filter(synonym-> synonym.getWord().getWord_id().equals(word_id))
        .collect(Collectors.toList());
    }

    public SynonymDao createNewSynonym(SynonymDao synonymDao) {
        Synonym synonym = new Synonym(synonymDao);
        if (StringUtils.isEmpty(synonym.getWord())) {
            throw new SynonymValidationException();
        }
        if (StringUtils.isEmpty(synonym.getUser())) {
            throw new SynonymValidationException();
        }
        if (StringUtils.isEmpty(synonym.getSynonym())) {
            throw new SynonymValidationException();
        }
        return new SynonymDao(synonymRepository.save(synonym));
    }

    public void deleteSynonym(Long synonym_id) {
        Synonym synonym = getById(synonym_id);
        synonymRepository.delete(synonym);
    }

}

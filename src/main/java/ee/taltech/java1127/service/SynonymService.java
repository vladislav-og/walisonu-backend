package ee.taltech.java1127.service;

import ee.taltech.java1127.dto.SynonymDto;
import ee.taltech.java1127.exception.SynonymNotFoundException;
import ee.taltech.java1127.exception.SynonymValidationException;
import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.repository.SynonymRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SynonymService {

    private final SynonymRepository synonymRepository;

    public SynonymService(SynonymRepository synonymRepository) {
        this.synonymRepository = synonymRepository;
    }

    public List<Synonym> getAllSynonyms() {
        return synonymRepository.findAll();
    }

    public Synonym getById(Long synonymId) {
        return synonymRepository.findById(synonymId).orElseThrow(SynonymNotFoundException::new);
    }

    public List<Synonym> getSynonymsByWord(Long wordId) {
        return synonymRepository.findAll().stream()
                .filter(synonym -> synonym.getWordId().equals(wordId))
                .collect(Collectors.toList());
    }

    public SynonymDto createNewSynonym(SynonymDto synonymDao) {
        Synonym synonym = new Synonym(synonymDao);
        if (StringUtils.isEmpty(synonym.getWordId())) {
            log.error("Word_id is empty");
            throw new SynonymValidationException();
        }
        if (StringUtils.isEmpty(synonym.getUserId())) {
            log.error("User_id is empty");
            throw new SynonymValidationException();
        }
        if (StringUtils.isEmpty(synonym.getSynonym())) {
            log.error("Synonym name is empty");
            throw new SynonymValidationException();
        } else {
            synonym.setSynonym(synonym.getSynonym().trim());
            synonym.setSynonym(synonym.getSynonym().substring(0, 1).toUpperCase() + synonym.getSynonym().substring(1));
        }
        if (isSynonymAlreadyAdded(synonym.getWordId(), synonym)) {
            log.error("Synonym is already added to a wordId " + synonym.getWordId());
            throw new SynonymValidationException();
        }
        return new SynonymDto(synonymRepository.save(synonym));
    }

    private boolean isSynonymAlreadyAdded(Long wordId, Synonym synonym) {
        for (Synonym synonymToFind : getSynonymsByWord(wordId)) {
            if (synonym.getSynonym().toLowerCase().equals(synonymToFind.getSynonym().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void deleteSynonym(Long synonymId) {
        try {
            synonymRepository.deleteById(synonymId);
        } catch (Exception e) {
            log.error("Synonym deleting failed!");
        }

    }

    public SynonymDto updateSynonym(SynonymDto synonymDto, Long id) {
        Synonym synonym = new Synonym();
        if (!id.equals(synonymDto.getId())) {
            throw new SynonymValidationException();
        }
        synonym.setSynonymId(synonymDto.getId());
        synonym.setWordId(synonymDto.getWordId());
        synonym.setSynonym(synonymDto.getSynonym());
        synonym.setActive(synonymDto.isActive());


        if (StringUtils.isEmpty(synonym.getSynonym())) {
            throw new SynonymValidationException();
        }
        return convert(synonymRepository.save(synonym));
    }

    private SynonymDto convert(Synonym synonym) {
        SynonymDto synonymDto = new SynonymDto();
        synonymDto.setId(synonym.getSynonymId());
        synonymDto.setWordId(synonym.getWordId());
        synonymDto.setSynonym(synonym.getSynonym());
        synonymDto.setActive(synonym.isActive());

        return synonymDto;
    }

}

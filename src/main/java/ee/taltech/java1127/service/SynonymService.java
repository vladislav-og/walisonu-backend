package ee.taltech.java1127.service;

import ee.taltech.java1127.repository.SynonymRepository;

public class SynonymService {

    private final SynonymRepository synonymRepository;

    public SynonymService(SynonymRepository synonymRepository) {
        this.synonymRepository = synonymRepository;
    }
}

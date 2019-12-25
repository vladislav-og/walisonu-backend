package ee.taltech.java1127.repository;

import ee.taltech.java1127.model.Synonym;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SynonymRepository extends JpaRepository<Synonym, Long> {
    List<Synonym> findSynonymByWordId(Long wordId);

}

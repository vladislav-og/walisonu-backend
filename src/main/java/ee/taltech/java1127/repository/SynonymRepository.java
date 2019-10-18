package ee.taltech.java1127.repository;

import ee.taltech.java1127.model.Synonym;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynonymRepository extends JpaRepository<Synonym, Long> {

}

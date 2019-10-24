package ee.taltech.java1127;

import ee.taltech.java1127.model.Synonym;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
import ee.taltech.java1127.repository.SynonymRepository;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.repository.WordRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


@SpringBootApplication
public class Java1127Application {

    public static void main(String[] args) {
        SpringApplication.run(Java1127Application.class, args);
    }

    @Bean
    public CommandLineRunner initUsers(UserRepository userRepository, WordRepository wordRepository, SynonymRepository
            synonymRepository) {
        return (args) -> {
            List<User> users = List.of(
                    new User("egle1@gmail.com"),
                    new User("egle2@gmail.com"),
                    new User("egle3@gmail.com")
            );
            userRepository.saveAll(users);
            List<Word> words = List.of(
                    new Word("Tüdruk", true),
                    new Word("Poiss", true),
                    new Word("Alkohol", true)
                    /*new Word("vlad1", users.get(0), true),
                    new Word("vlad2", users.get(1), true),
                    new Word("vlad3", users.get(2), true)*/
            );
            wordRepository.saveAll(words);
            List<Synonym> synonyms = List.of(
                    /*new Synonym(words.get(0), "vladislav1", users.get(0), true),
                    new Synonym(words.get(1), "vladislav2", users.get(1), true),
                    new Synonym(words.get(2), "vladislav3", users.get(2), true),
                    new Synonym(words.get(0), "vladis1", users.get(0), true),
                    new Synonym(words.get(1), "vladis2", users.get(1), true),
                    new Synonym(words.get(2), "vladis3", users.get(2), true)*/
                    new Synonym(words.get(0).getWord_id(), "Eit", true),
                    new Synonym(words.get(0).getWord_id(), "Baby", true),
                    new Synonym(words.get(0).getWord_id(), "Plika", true),
                    new Synonym(words.get(1).getWord_id(), "Kutt", true),
                    new Synonym(words.get(1).getWord_id(), "Lõngus", true),
                    new Synonym(words.get(2).getWord_id(), "Samakas", true),
                    new Synonym(words.get(2).getWord_id(), "Peedikas", true),
                    new Synonym(words.get(2).getWord_id(), "Kärakas", true)

            );
            synonymRepository.saveAll(synonyms);
        };
    }
}

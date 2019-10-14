package ee.taltech.java1127;

import ee.taltech.java1127.model.User;
import ee.taltech.java1127.model.Word;
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
	public CommandLineRunner initUsers(UserRepository userRepository, WordRepository wordRepository) {
		return (args) -> {
			List<User> users = List.of(
					new User ("egle1@gmail.com"),
					new User ("egle2@gmail.com"),
					new User ("egle3@gmail.com")
			);
			userRepository.saveAll(users);
			List<Word> words = List.of(
					new Word ("vlad1", users.get(0),true),
					new Word ("vlad2", users.get(1), true),
					new Word ("vlad3", users.get(2), true)
			);
			wordRepository.saveAll(words);
		};
	}
}

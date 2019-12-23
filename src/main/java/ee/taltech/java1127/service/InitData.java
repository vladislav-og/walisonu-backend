package ee.taltech.java1127.service;

import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.security.Role;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@Profile("test")
@AllArgsConstructor
public class InitData implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        User admin = new User();
        admin.setEmail("admintest@admin.ee");
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRole(Role.ADMIN);
        userRepository.save(admin);
    }
}
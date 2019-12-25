package ee.taltech.java1127.service;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.exception.UserNotFoundException;
import ee.taltech.java1127.exception.UserValidationException;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.UserRepository;
import ee.taltech.java1127.security.Role;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long userId) {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public UserDto createNewUser(UserDto userDto) {
        User user = new User(userDto);
        if (StringUtils.isEmpty(user.getEmail())) {
            log.error("User email is empty");
            throw new UserValidationException();
        }
        if (StringUtils.isEmpty(user.getPassword())) {
            log.error("User password is empty");
            throw new UserValidationException();
        }
        if (isEmailAlreadyAdded(user)) {
            log.error("User email is already added");
            throw new UserValidationException();
        }
        user.setEmail(user.getEmail().toLowerCase().trim());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRole(Role.USER);
        return new UserDto(userRepository.save(user));
    }

    private boolean isEmailAlreadyAdded(User user) {
        List<User> users = userRepository.findByEmail(user.getEmail().toLowerCase());
        return users.size() > 0;
    }

    public void deleteUser(Long userId) {
        try {
            userRepository.deleteById(userId);
        } catch (Exception e) {
            log.error("User deleting failed!");
        }

    }

}

package ee.taltech.java1127.service;

import ee.taltech.java1127.dto.UserDto;
import ee.taltech.java1127.exception.UserNotFoundException;
import ee.taltech.java1127.exception.UserValidationException;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getById(Long user_id) {
        return userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);
    }

    public UserDto createNewUser(UserDto userDao) {
        User user = new User(userDao);
        if (StringUtils.isEmpty(user.getEmail())) {
            log.error("User email is empty");
            throw new UserValidationException();
        } else {
            user.setEmail(user.getEmail().toLowerCase().trim());
        }
        if (isEmailAlreadyAdded(user)) {
            log.error("User email is already added");
            throw new UserValidationException();
        }
        return new UserDto(userRepository.save(user));
    }

    private boolean isEmailAlreadyAdded(User user) {
        for (User emailToFind : getAllUsers()) {
            if (emailToFind.getEmail().toLowerCase().equals(user.getEmail().toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public void deleteUser(Long user_id) {
        try {
            userRepository.deleteById(user_id);
        } catch (Exception e) {
            log.error("User deleting failed!");
        }

    }

}

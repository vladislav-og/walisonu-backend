package ee.taltech.java1127.service;

import ee.taltech.java1127.dao.UserDao;
import ee.taltech.java1127.exception.UserNotFoundException;
import ee.taltech.java1127.exception.UserValidationException;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers () {
        return userRepository.findAll();
    }

    public User getById (Long user_id){
        return userRepository.findById(user_id).orElseThrow(UserNotFoundException::new);
    }

    public UserDao createNewUser(UserDao userDao){
        User user = new User(userDao);
        if (StringUtils.isEmpty(user.getUser_id())) {
            throw new UserValidationException();
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            throw new UserValidationException();
        }
        return new UserDao(userRepository.save(user));
    }

    public void deleteUser(Long user_id){
        User user = getById(user_id);
        userRepository.delete(user);
    }

}

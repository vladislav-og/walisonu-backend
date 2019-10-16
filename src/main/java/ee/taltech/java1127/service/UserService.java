package ee.taltech.java1127.service;

import ee.taltech.java1127.dao.UserDao;
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


}

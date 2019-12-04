package ee.taltech.java1127.security;
import ee.taltech.java1127.model.User;
import ee.taltech.java1127.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;
import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * to load user with username, password and roles (authorities)
 */
@Service
@AllArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> users = userRepository.findByEmail(username);
        if (isEmpty(users)) {
            throw new UsernameNotFoundException(format("email not found: %s", username));
        }
        User user = users.get(0); //this application doesn't protect against duplicate users
        return new MyUser(user.getEmail(), user.getPassword(), getAuthorities(user), user.getRole(), user.getUserId());
    }

    private List<SimpleGrantedAuthority> getAuthorities(User user) {
        return getRoles(user)
                .map(Role::toSpringRole)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    private Stream<Role> getRoles(User user) {
        if (user.getRole().isAdmin()) {
            return Arrays.stream(Role.values());
        }
        return Stream.of(user.getRole());
    }
}
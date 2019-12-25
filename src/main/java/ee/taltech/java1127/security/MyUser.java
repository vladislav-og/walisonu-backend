package ee.taltech.java1127.security;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * to have our own properties on session user
 * also to have clean imports as our model User and spring security User have same name "User"
 */
@Getter
public class MyUser extends User {

    private Long id;
    private Role role;

    public MyUser(String username, String password, Collection<? extends GrantedAuthority> authorities, Role role, Long id) {
        super(username, password, authorities);
        this.role = role;
        this.id = id;
    }

    /**
     * we are forced to override this constructor, however we don't use it
     */
    public MyUser(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    }
}
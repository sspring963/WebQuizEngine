package engine;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class MyDatabaseUserDetailsService implements UserDetailsService {

    private final String userName;

    UserDetails loadUsersByUsername(String username) throws UsernameNotFoundException {
        User user
    }

    public MyDatabaseUserDetailsService(String userName) {
        this.userName = userName;
    }
}



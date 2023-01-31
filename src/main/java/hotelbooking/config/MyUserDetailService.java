package hotelbooking.config;

import hotelbooking.models.pojo.User;
import hotelbooking.repositories.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;


    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getUser(email);
        return new MyUserSec(user.getEmail(), user.getPassword(),user.getId().toString(),user.getRole(),new ArrayList<>());

    }
}

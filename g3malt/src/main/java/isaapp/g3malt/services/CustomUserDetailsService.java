package isaapp.g3malt.services;

import isaapp.g3malt.model.User;
import isaapp.g3malt.model.UserCredentials;
import isaapp.g3malt.repository.UserCredentialsRepository;
import isaapp.g3malt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;

    // Funkcija koja na osnovu username-a iz baze vraca objekat User-a
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserCredentials userCredentials = userCredentialsRepository.findByEmail(email);
        User user = userService.findById(userCredentials.getId());
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            return user;
        }
    }
}

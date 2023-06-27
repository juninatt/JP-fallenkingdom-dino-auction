package se.pbt.mrcoffee.service.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.repository.user.UserRepository;
import se.pbt.mrcoffee.security.MrCoffeeUserDetails;

/**
 * Custom implementation of UserDetailsService for MrCoffeeUser.
 */
@Service
public class MrCoffeeUserDetailService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MrCoffeeUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user details for the given username.
     *
     * @param username the username of the user to load
     * @return the UserDetails object representing the user details
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MrCoffeeUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new MrCoffeeUserDetails(user);
    }
}

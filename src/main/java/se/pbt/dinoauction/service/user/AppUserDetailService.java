package se.pbt.dinoauction.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.pbt.dinoauction.model.entity.user.AppUser;
import se.pbt.dinoauction.repository.UserRepository;
import se.pbt.dinoauction.security.AppUserDetails;

/**
 * Custom service class responsible for user authentication, serving as an implementation of Spring Security's {@link UserDetailsService}.
 * It integrates with the application's data model to look up {@link AppUser} entities by username.
 */
@Service
public class AppUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    /**
     * Constructs a new {@code AppUserDetailService}, initializing it with the given {@link UserRepository}.
     *
     * @param userRepository the repository used to look up {@link AppUser} entities
     */
    @Autowired
    public AppUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Attempts to locate the {@link AppUser} with the specified username and construct a corresponding {@link UserDetails} object.
     *
     * @param username the username whose corresponding user is to be found
     * @return a {@link UserDetails} instance representing the authenticated user
     * @throws UsernameNotFoundException if no such user could be found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (appUser == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new AppUserDetails(appUser);
    }
}

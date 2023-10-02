package se.pbt.dinoauction.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.dinoauction.model.entity.user.AppUser;
import se.pbt.dinoauction.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class responsible for managing users within the application.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    /**
     * Constructs a new UserService instance with the provided {@link UserRepository}.
     *
     * @param userRepository the UserRepository instance responsible for user data operations
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all {@link AppUser} instances.
     *
     * @return a list of all AppUser instances
     */
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves an {@link AppUser} by their unique ID.
     *
     * @param id the unique ID of the AppUser
     * @return the AppUser instance
     * @throws RuntimeException if user not found
     */
    public AppUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    /**
     * Creates a new {@link AppUser}.
     *
     * @param appUser the AppUser to create
     * @return the created AppUser instance
     */
    public AppUser createUser(AppUser appUser) {
        // Add validation
        return userRepository.save(appUser);
    }

    /**
     * Updates an existing {@link AppUser}.
     *
     * @param id the unique ID of the AppUser to update
     * @param updatedAppUser the updated AppUser instance
     * @return the updated AppUser instance
     * @throws RuntimeException if user not found
     */
    public AppUser updateUser(Long id, AppUser updatedAppUser) {
        Optional<AppUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            AppUser appUser = optionalUser.get();

            // TODO: Add validation and further updating of fields

            appUser.setUsername(updatedAppUser.getUsername());
            appUser.setPassword(updatedAppUser.getPassword());
            return userRepository.save(appUser);
        } else {
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    /**
     * Deletes an {@link AppUser} by their unique ID.
     *
     * @param id the unique ID of the AppUser to delete
     * @return true if the operation is successful
     * @throws RuntimeException if deletion failed
     */
    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException.getMessage());
        }
        return true;
    }
}

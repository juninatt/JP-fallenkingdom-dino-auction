package se.pbt.mrcoffee.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class MrCoffeeUserService {

    private final UserRepository userRepository;

    @Autowired
    public MrCoffeeUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<MrCoffeeUser> getAllUsers() {
        return userRepository.findAll();
    }

    public MrCoffeeUser getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow();
    }

    public MrCoffeeUser createUser(MrCoffeeUser user) {
        // Perform any necessary validations or business logic
        // before saving the user
        return userRepository.save(user);
    }

    public MrCoffeeUser updateUser(Long id, MrCoffeeUser updatedUser) {
        Optional<MrCoffeeUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            MrCoffeeUser user = optionalUser.get();
            // Perform any necessary updates to the user object
            // using the values from updatedUser
            // For example, you can update the username and password:
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());

            // Save the updated user
            return userRepository.save(user);
        } else {
            // Handle the case where the user with the given id is not found
            // For example, you can throw an exception or return null
            throw new RuntimeException("User not found with id: " + id);
        }
    }

    public boolean deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        } catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException.getMessage());
        }
        return true;
    }
}

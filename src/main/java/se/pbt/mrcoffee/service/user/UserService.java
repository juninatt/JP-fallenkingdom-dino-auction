package se.pbt.mrcoffee.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.repository.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
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
        // Add validation
        return userRepository.save(user);
    }

    public MrCoffeeUser updateUser(Long id, MrCoffeeUser updatedUser) {
        Optional<MrCoffeeUser> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            MrCoffeeUser user = optionalUser.get();

            // Add validation and further updating of fields

            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            return userRepository.save(user);
        } else {
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

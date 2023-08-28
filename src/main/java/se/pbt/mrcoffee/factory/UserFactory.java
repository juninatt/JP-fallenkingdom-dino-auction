package se.pbt.mrcoffee.factory;

import se.pbt.mrcoffee.enums.CustomerLevel;
import se.pbt.mrcoffee.exception.RoleNotFoundException;
import se.pbt.mrcoffee.model.user.Admin;
import se.pbt.mrcoffee.model.user.Customer;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.model.user.security.Role;
import se.pbt.mrcoffee.repository.RoleRepository;

/**
 * Factory class responsible for creating different types of {@link MrCoffeeUser}.
 * Handles setting {@link Role} for users by fetching them from the role repository.
 */
public class UserFactory {

    private final RoleRepository roleRepository;

    /**
     * Constructor that initializes the {@link RoleRepository}.
     *
     * @param roleRepository Repository to look up roles.
     */
    public UserFactory(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Creates an {@link Admin} user with the "ROLE_ADMIN" role.
     *
     * @param username Desired username.
     * @param password Desired password.
     * @return Admin object with assigned role.
     */
    public MrCoffeeUser createAdmin(String username, String password) {
        Admin admin = new Admin(username, password);
        return createUserWithRole(admin, "ROLE_ADMIN");
    }


    /**
     * Creates a {@link Customer} user with the "ROLE_CUSTOMER" role.
     *
     * @param username Desired username.
     * @param password Desired password.
     * @param level Customer level.
     * @return Customer object with assigned role.
     */
    public MrCoffeeUser createCustomer(String username, String password, CustomerLevel level) {
        Customer customer = new Customer(username, password, level);
        return createUserWithRole(customer, "ROLE_CUSTOMER");
    }

    /**
     * Internal method that sets a {@link Role} for a user.
     *
     * @param user User to assign the role to.
     * @param roleName Role to be assigned.
     * @return User object with the assigned role.
     * @throws RoleNotFoundException if the role is not found.
     */
    private MrCoffeeUser createUserWithRole(MrCoffeeUser user, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException(roleName + " role not found"));
        user.addRole(role);
        return user;
    }
}


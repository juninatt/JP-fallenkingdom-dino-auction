package se.pbt.dinoauction.factory;

import se.pbt.dinoauction.exception.RoleNotFoundException;
import se.pbt.dinoauction.model.entity.user.Organizer;
import se.pbt.dinoauction.model.entity.user.Participant;
import se.pbt.dinoauction.model.entity.user.AppUser;
import se.pbt.dinoauction.model.entity.user.security.Role;
import se.pbt.dinoauction.repository.user.RoleRepository;

/**
 * Creates {@link AppUser} instances with specific {@link Role} assignments.
 * Utilizes {@link RoleRepository} for role look-up.
 */
public class UserFactory {

    private final RoleRepository roleRepository;

    /**
     * Initializes the role {@link RoleRepository}.
     *
     * @param roleRepository Source for {@link Role} data.
     */
    public UserFactory(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    /**
     * Produces an {@link Organizer} with "ROLE_ADMIN" role.
     *
     * @param username Username.
     * @param password Password.
     * @return Organizer with role.
     */
    public AppUser createOrganizer(String username, String password) {
        Organizer organizer = new Organizer(username, password);
        return createUserWithRole(organizer, "ROLE_ADMIN");
    }

    /**
     * Produces a {@link Participant} with "ROLE_PARTICIPANT" role.
     *
     * @param username Username.
     * @param password Password.
     * @param alias Alias.
     * @return Participant with role.
     */
    public AppUser createParticipant(String username, String password, String alias) {
        Participant participant = new Participant(username, password, alias);
        return createUserWithRole(participant, "ROLE_PARTICIPANT");
    }

    /**
     * Assigns a role to a {@link AppUser}.
     *
     * @param appUser Target user.
     * @param roleName Role to assign.
     * @return User with assigned role.
     * @throws RoleNotFoundException If role is absent.
     */
    private AppUser createUserWithRole(AppUser appUser, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RoleNotFoundException(roleName + " role not found"));
        appUser.addRole(role);
        return appUser;
    }
}

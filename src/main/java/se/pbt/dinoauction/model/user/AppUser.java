package se.pbt.dinoauction.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.dinoauction.model.user.security.Role;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user in the system.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;


    /**
     * Many-to-Many relationship to {@link Role}.
     * This AppUser is the owner, managing the 'user_role' join table.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private final Set<Role> roles = new HashSet<>();


    /**
     * No-args constructor for JPA operations.
     */
    protected AppUser() {
    }

    public AppUser(@NotBlank String username, @NotBlank String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Adds a {@link Role} to this user and updates the corresponding set of users in the Role entity.
     * This operation ensures that the relationship between User and Role entities is kept in sync.
     *
     * @param role The role to be added to this user.
     */
    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    /**
     * Removes a {@link Role} from this user and updates the corresponding set of users in the Role entity.
     * This operation ensures that the relationship between User and Role entities is kept in sync.
     *
     * @param role The role to be removed from this user.
     */
    public void removeRole(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }


    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return Collections.unmodifiableSet(roles);
    }

}


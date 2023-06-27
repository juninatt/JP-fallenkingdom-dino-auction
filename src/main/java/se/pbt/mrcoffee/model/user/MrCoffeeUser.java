package se.pbt.mrcoffee.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.contact.Contact;

import java.util.Set;

/**
 * Represents a user in the system.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MrCoffeeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;


    @ManyToMany
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    /**
     * Represents the contact information associated with this user.
     * The relationship is defined as a one-to-one mapping, where MrCoffeeUser is the owner side.
     */
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contact;


    protected MrCoffeeUser() {
    }

    public MrCoffeeUser(
            @NotBlank String username,
            @NotBlank String password
    ) {
        this.username = username;
        this.password = password;
    }

    public MrCoffeeUser(
            @NotBlank String username,
            @NotBlank String password,
            Contact contact
    ) {
        this.username = username;
        this.password = password;
        this.contact = contact;
    }


    public Long getUserId() {
        return userId;
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
        return roles;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}


package se.pbt.mrcoffee.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.contact.Contact;

/**
 * Represents a user in the system.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    @NotBlank(message = "Username is required")
    @Size(min = 5, message = "Username must be at least 5 characters")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "contact_id")
    private Contact contactInformation;

    /**
     * Default constructor for the User class.
     * Protected access modifier to prevent direct instantiation.
     */
    protected User() {
    }

    /**
     * Constructor for the User class with the username and password parameters.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Constructor for the User class with the username, password and contact parameters.
     *
     * @param username The username of the user
     * @param password The password of the user
     * @param contactInformation The contact information of the user
     */
    public User(String username, String password, Contact contactInformation) {
        this.username = username;
        this.password = password;
        this.contactInformation = contactInformation;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
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

    public Contact getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(Contact contactInformation) {
        this.contactInformation = contactInformation;
    }
}


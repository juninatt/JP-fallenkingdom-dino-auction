package se.pbt.mrcoffee.model.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a users contact information
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    @NotBlank(message = "Email is required")
    private String email;

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String additionalInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private MrCoffeeUser mrCoffeeUser;

    @ManyToMany
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private final Map<String, Address> addresses = new HashMap<>();

    /**
     * Default constructor for the Contact class.
     */
    public Contact() {
    }

    /**
     * Constructor for the Contact class.
     *
     * @param email           The email address of the contact.
     * @param phoneNumber     The phone number of the contact.
     * @param additionalInfo  Additional information about the contact.
     * @param mrCoffeeUser            The user associated with the contact.
     */
    public Contact(String email, String phoneNumber, String additionalInfo, MrCoffeeUser mrCoffeeUser) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
        this.mrCoffeeUser = mrCoffeeUser;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public MrCoffeeUser getUser() {
        return mrCoffeeUser;
    }

    public void setUser(MrCoffeeUser mrCoffeeUser) {
        this.mrCoffeeUser = mrCoffeeUser;
    }

    public Map<String, Address> getAddresses() {
        return addresses;
    }
}


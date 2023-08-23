package se.pbt.mrcoffee.model.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a user's contact information within the system.
 * <p>
 * A Contact object includes an email, phone number, and optional additional information.
 * It also has associated user details and may be linked to multiple addresses.
 * <p>
 * This abstract class can be extended to define specific types of contacts, such as customer or supplier contacts.
 * Validations are included to ensure that essential fields are provided, and certain fields are constrained in length.
 * <p>
 * The class includes relationships to {@link MrCoffeeUser} and {@link Address}, and the mappings are
 * defined with JPA annotations.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Email is required")
    @Size(max = 256, message = "Email address is to long")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must follow the  international phone numbering plan (ITU-T E. 164)")
    private String phoneNumber;

    @Size(max = 255, message = "Additional information cant have more than 255 characters")
    private String additionalInfo;

    @OneToOne(mappedBy = "contact")
    private MrCoffeeUser user;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "contact_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id")
    )
    private final Set<Address> addresses = new HashSet<>();

    public Contact() {}

    public Contact(
            @NotBlank String email,
            @NotBlank String phoneNumber,
            String additionalInfo,
            MrCoffeeUser user
    ) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.additionalInfo = additionalInfo;
        this.user = user;
    }

    /**
     * Connects this contact information to an address.
     *
     * @param address A {@link Address} object containing the adress information
     */
    public void addAddress(Address address) {
        this.addresses.add(address);
        address.getContacts().add(this);
    }

    // Getters and setters

    public Long getId() {
        return id;
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
        return user;
    }

    public void setUser(MrCoffeeUser mrCoffeeUser) {
        this.user = mrCoffeeUser;
    }

    public Set<Address>getAddresses() {
        return addresses;
    }
}


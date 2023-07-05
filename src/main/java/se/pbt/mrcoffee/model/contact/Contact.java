package se.pbt.mrcoffee.model.contact;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.HashSet;
import java.util.Set;

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
    @Size(max = 256, message = "Email address is to long")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15, message = "Phone number must follow the  international phone numbering plan (ITU-T E. 164)")
    private String phoneNumber;

    @Size(max = 255, message = "Additional information cant have more than 255 characters")
    private String additionalInfo;

    /**
     * Represents the associated {@link MrCoffeeUser} for this contact information.
     * The relationship is defined as a one-to-one mapping using a foreign key
     * where the user is the owner.
     */
    @OneToOne(mappedBy = "contact")
    private MrCoffeeUser user;

    /**
     * Represents the mapping of {@link Address} entities associated with this contact information.
     * The mapping is defined as a many-to-many relationship using a join table.
     */
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

    public Long getContactId() {
        return contactId;
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

    public void addAddress(Address address) {
        this.addresses.add(address);
        address.getContacts().add(this);  // assuming Address class has a getContacts method
    }

}


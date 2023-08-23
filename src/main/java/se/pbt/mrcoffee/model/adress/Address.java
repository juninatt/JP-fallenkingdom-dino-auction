package se.pbt.mrcoffee.model.adress;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.contact.Contact;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents an address within the system.
 * <p>
 * An Address object includes the street, street number, optional apartment number,
 * city, postal code, and country. It can be associated with various entities that require
 * address details, such as customers or suppliers.
 * <p>
 * Validations are included to ensure that essential fields are provided, and certain fields
 * are constrained in length.
 * <p>
 * The class includes a relationship to {@link Contact} and the mapping is
 * defined with JPA annotations.
 */
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Street is required")
    @Size(max = 30, message = "Street can't be more than 30 characters")
    private String street;

    @NotNull(message = "Street number is required")
    private int streetNumber;

    private int apartmentNumber;

    @NotBlank(message = "City is required")
    @Size(max = 15, message = "City can't be more than 15 characters")
    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @Size(max = 20, message = "Country can't be more than 20 characters")
    private String country;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private final Set<Contact> contacts = new HashSet<>();

    public Address() {}

    public Address(
            @NotBlank String street,
            @NotBlank int streetNumber,
            int apartmentNumber,
            @NotBlank String city,
            @NotBlank String postalCode,
            String country
    ) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    /**
     * Connects this address to a users contact information.
     *
     * @param contactInformation A {@link Contact} object containing contact information of the user in.
     */
    public void addContact(Contact contactInformation) {
        contacts.add(contactInformation);
        contactInformation.getAddresses().add(this);
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(int apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Contact> getContacts() {
        return contacts;
    }

    // Overridden methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNumber == address.streetNumber &&
                apartmentNumber == address.apartmentNumber &&
                Objects.equals(id, address.id) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, streetNumber, apartmentNumber, city, postalCode, country);
    }
}


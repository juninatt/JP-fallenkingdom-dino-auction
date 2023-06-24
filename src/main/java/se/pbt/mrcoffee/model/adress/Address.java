package se.pbt.mrcoffee.model.adress;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.contact.Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank(message = "Street is required")
    @Size(max = 30, message = "Street can't be more than 30 characters")
    private String street;

    @NotBlank(message = "Street number is required")
    private int streetNumber;

    private int apartmentNumber;

    @NotBlank(message = "City is required")
    @Size(max = 15, message = "City can't be more than 15 characters")
    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @Size(max = 20, message = "Country can't be more than 20 characters")
    private String country;

    /**
     * Represents the mapping of contacts associated with this address.
     * The mapping is defined as a many-to-many relationship using a join table.
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private final Map<String, Contact> contacts = new HashMap<>();

    /**
     * Default constructor for the Address class.
     */
    public Address() {
    }

    public Address(String street, int streetNumber, int apartmentNumber, String city, String postalCode, String country) {
        this.street = street;
        this.streetNumber = streetNumber;
        this.apartmentNumber = apartmentNumber;
        this.city = city;
        this.postalCode = postalCode;
        this.country = country;
    }

    public Long getAddressId() {
        return addressId;
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

    public Map<String, Contact> getContacts() {
        return contacts;
    }

    public void addContact(String identifier, Contact contact) {
        contacts.put(identifier, contact);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return streetNumber == address.streetNumber &&
                apartmentNumber == address.apartmentNumber &&
                Objects.equals(addressId, address.addressId) &&
                Objects.equals(street, address.street) &&
                Objects.equals(city, address.city) &&
                Objects.equals(postalCode, address.postalCode) &&
                Objects.equals(country, address.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(addressId, street, streetNumber, apartmentNumber, city, postalCode, country);
    }
}


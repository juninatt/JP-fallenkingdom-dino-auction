package se.pbt.mrcoffee.model.contact.adress;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.model.contact.Contact;

import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    @NotBlank(message = "Street is required")
    private String street;

    @NotBlank(message = "Street number is required")
    private int streetNumber;

    private int apartmentNumber;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    private String country;

    @ManyToMany
    @JoinTable(
            name = "contact_address",
            joinColumns = @JoinColumn(name = "address_id"),
            inverseJoinColumns = @JoinColumn(name = "contact_id")
    )
    private Map<String, Contact> contacts = new HashMap<>();

    /**
     * Default constructor for the Address class.
     */
    public Address() {
    }

    /**
     * Parameterized constructor for the Address class.
     *
     * @param street          The street name.
     * @param streetNumber    The street number.
     * @param apartmentNumber The apartment or house number.
     * @param city            The city name.
     * @param postalCode      The postal code.
     * @param country         The country name.
     */
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

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
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

    public void setContacts(Map<String, Contact> contacts) {
        this.contacts = contacts;
    }
}


package se.pbt.mrcoffee.dto;

/**
 * Data Transfer Object for transferring Address data.
 * <p>
 * This DTO includes the essential fields of the Address entity and
 * it's used for transferring data across different layers or systems.
 */
public record AddressDTO(
        String street,
        int streetNumber,
        int apartmentNumber,
        String city,
        String postalCode,
        String country) {}

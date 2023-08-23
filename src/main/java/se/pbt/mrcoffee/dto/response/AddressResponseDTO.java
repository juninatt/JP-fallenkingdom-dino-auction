package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.adress.Address;

/**
 * Data Transfer Object representing the response for {@link Address} information.
 * <p>
 * This DTO contains the essential fields related to an address, including street, street number,
 * apartment number, city, postal code, and country.
 */
public record AddressResponseDTO (
        Long id,
        String street,
        int streetNumber,
        int apartmentNumber,
        String city,
        String postalCode,
        String country
) {}

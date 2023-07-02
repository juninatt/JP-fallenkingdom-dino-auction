package se.pbt.mrcoffee.dto.response;

public record AddressResponseDTO (
        Long addressId,
        String street,
        int streetNumber,
        int apartmentNumber,
        String city,
        String postalCode,
        String country
) {}

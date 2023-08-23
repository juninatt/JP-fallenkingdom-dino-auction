package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import se.pbt.mrcoffee.model.adress.Address;

/**
 * Data Transfer Object for transferring {@link Address} data.
 * <p>
 * This DTO includes the essential fields of the Address entity, and
 * it's used for transferring data across different layers or systems.
 */
public record AddressDTO(
        @NotBlank(message = "Street is required")
        String street,
        @NotNull(message = "Street number is required")
        @Positive(message = "Street number must be positive")
        int streetNumber,
        @Positive(message = "Apartment number must be positive")
        int apartmentNumber,
        @NotBlank(message = "City is required")
        String city,
        @NotBlank(message = "Postal code is required")
        String postalCode,
        @NotBlank(message = "Country is required")
        String country
) {}

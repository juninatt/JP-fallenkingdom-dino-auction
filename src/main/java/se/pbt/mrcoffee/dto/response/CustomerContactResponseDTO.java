package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.dto.request.AddressDTO;

import java.util.List;

/**
 * Data Transfer Object representing the response for {@link CustomerContactResponseDTO} information.
 * <p>
 * This DTO contains the essential fields related to customer contact,
 * including their name, email, phone number, additional information, and associated addresses.
 */
public record CustomerContactResponseDTO(
        long id,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String additionalInfo,
        List<AddressDTO> addresses
) {}

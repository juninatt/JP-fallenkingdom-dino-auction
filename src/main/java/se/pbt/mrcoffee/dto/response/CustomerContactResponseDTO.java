package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.dto.request.AddressDTO;

import java.util.List;

public record CustomerContactResponseDTO(
        long contactId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String additionalInfo,
        List<AddressDTO> addresses
) {}

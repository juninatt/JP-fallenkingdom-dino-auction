package se.pbt.mrcoffee.dto.response;

import java.util.List;

public record CustomerContactResponseDTO(
        String email,
        String phoneNumber,
        String additionalInfo,
        String mrCoffeeUserUsername,
        List<AddressResponseDTO> addresses
) {}

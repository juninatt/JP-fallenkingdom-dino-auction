package se.pbt.mrcoffee.dto.request;

public record CustomerContactDTO (
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        String additionalInfo
) {}


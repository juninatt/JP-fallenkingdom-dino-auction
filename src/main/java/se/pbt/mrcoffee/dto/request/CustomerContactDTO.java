package se.pbt.mrcoffee.dto.request;

public record CustomerContactDTO (
        String email,
        String phoneNumber,
        String additionalInfo,
        String mrCoffeeUserUsername
) {}


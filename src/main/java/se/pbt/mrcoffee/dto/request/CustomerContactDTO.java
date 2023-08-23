package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for transferring Customer Contact data.
 * <p>
 * This DTO includes the essential fields related to the contact information
 * of a customer, and it's used for transferring data across different layers
 * or systems.
 */
public record CustomerContactDTO (
        @NotBlank(message = "First name is required")
        String firstName,
        @NotBlank(message = "Last name is required")
        String lastName,
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be a valid email address")
        String email,
        @NotBlank(message = "Phone number is required")
        String phoneNumber,
        String additionalInfo
) {}


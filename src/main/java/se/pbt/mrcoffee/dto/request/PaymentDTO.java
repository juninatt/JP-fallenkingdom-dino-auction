package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.payment.Payment;

/**
 * Data Transfer Object representing the details of a paymen{@link Payment}.
 * <p>
 * This DTO is used to transfer payment information from the client to the server during payment processing.
 * Validation constraints ensure that the provided information adheres to the expected format.
 */
public record PaymentDTO (

        @NotBlank
        @Size(min = 5, max = 20, message = "Status must be between 5 and 20 characters")
        String status,

        @NotBlank
        @Size(min = 5, max = 30, message = "Payment method must be between 5 and 30 characters")
        String paymentMethod,

        @NotNull(message = "Purchase ID is required")
        long purchaseId
) {}


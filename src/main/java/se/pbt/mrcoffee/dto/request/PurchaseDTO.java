package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.NotNull;

/**
 * Represents a data transfer object for a purchase.
 * <p>
 * This DTO simplifies the complex purchase object by referencing related entities through their IDs,
 * making it suitable for various data operations, including serialization and deserialization.
 */
public record PurchaseDTO(
        @NotNull(message = "Receipt ID is required") Long receiptId,
        @NotNull(message = "Payment ID is required") Long paymentId,
        @NotNull(message = "Customer ID is required") Long customerId
)
{}


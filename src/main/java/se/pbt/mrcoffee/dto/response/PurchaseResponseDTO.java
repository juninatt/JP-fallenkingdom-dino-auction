package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.purchase.Purchase;

/**
 * Represents a data transfer object for responding to client requests regarding a {@link Purchase}.
 * <p>
 * By including the purchase ID, this response object provides a complete view of the purchase data,
 * facilitating client interactions with the purchase resource within the system.
 */
public record PurchaseResponseDTO(
        Long id,
        Long receiptId,
        Long paymentId,
        Long customerId
) {}


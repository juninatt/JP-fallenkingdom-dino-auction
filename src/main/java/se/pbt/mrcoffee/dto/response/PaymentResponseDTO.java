package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.payment.Payment;

/**
 * Represents the response data for a {@link Payment} made for a purchase.
 * <p>
 * This DTO includes the details related to a specific payment, including the payment ID,
 * status, payment method, and the associated purchase. It is used to send payment information
 * in response to client requests.
 */
public record PaymentResponseDTO(
        Long id,
        String status,
        String paymentMethod,
        long purchaseId
) {}

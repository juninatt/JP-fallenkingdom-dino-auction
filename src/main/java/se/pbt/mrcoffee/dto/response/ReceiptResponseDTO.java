package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.receipt.Receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a data transfer object for responding to client requests regarding a {@link Receipt}.
 * <p>
 * The ReceiptResponseDTO includes all the details contained in the ReceiptDTO along with
 * the receipt ID and creation time. It is intended to be used when responding to client
 * requests, such as after creating or retrieving a receipt.
 */
public record ReceiptResponseDTO(
        Long id,
        BigDecimal totalAmount,
        String discountCode,
        BigDecimal discountAmount,
        String orderNumber,
        Long purchaseId,
        LocalDateTime createdAt
) {}


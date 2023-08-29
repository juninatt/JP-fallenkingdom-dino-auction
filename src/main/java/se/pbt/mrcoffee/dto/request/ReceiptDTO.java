package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.order.Receipt;

import java.math.BigDecimal;

/**
 * Represents a data transfer object for a {@link Receipt}.
 * <p>
 * The ReceiptDTO captures essential details about a receipt, including the total amount,
 * discount code and amount, order number, and purchase ID. This object can be used for
 * transferring receipt data between different layers of the application, such as between
 * the service layer and the API.
 */
public record ReceiptDTO(

        @NotBlank(message = "Total amount is required")
        @DecimalMin(value = "0", message = "Total amount can't be below 0")
        BigDecimal totalAmount,

        @Size(max = 255)
        String discountCode,

        @DecimalMin(value = "0", inclusive = false, message = "Discount amount can't be below 0")
        BigDecimal discountAmount,

        @NotBlank(message = "Order number is required")
        @Size(max = 255)
        String orderNumber,

        @NotNull(message = "Purchase ID is required")
        Long purchaseId
) {}


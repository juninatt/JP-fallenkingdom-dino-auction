package se.pbt.dinoauction.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * An OrderPreviewDTO is a Data Transfer Object that encapsulates the information for previewing an order.
 * It includes the title of the order, a mapping of product names to the quantity ordered, and the total dollar price of all products in the preview.
 */
public record TransactionPreviewDTO(
        @NotBlank(message = "Title is required")
        String title,

        @NotNull(message = "Products cannot be null")
        HashMap<String, Integer> products,
        @Positive(message = "Total sum must be greater than zero")
        BigDecimal totalSum
) {}

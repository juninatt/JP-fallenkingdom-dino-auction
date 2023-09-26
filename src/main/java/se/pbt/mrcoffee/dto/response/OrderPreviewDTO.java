package se.pbt.mrcoffee.dto.response;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @param title The title of the order preview.
 * @param products A map with the name of the product as key(String) and the amount ordered as value(Integer)
 * @param totalSum Tho total price of all products in the preview.
 */
public record OrderPreviewDTO (
    String title,

    HashMap<String, Integer> products,
    BigDecimal totalSum
) {}

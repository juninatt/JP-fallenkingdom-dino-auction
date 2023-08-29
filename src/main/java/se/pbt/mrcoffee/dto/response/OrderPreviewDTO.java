package se.pbt.mrcoffee.dto.response;

import se.pbt.mrcoffee.model.product.Coffee;

import java.math.BigDecimal;
import java.util.HashMap;

public record OrderPreviewDTO (
    String title,
    HashMap<Coffee, Integer> products,
    BigDecimal totalSum
) {}

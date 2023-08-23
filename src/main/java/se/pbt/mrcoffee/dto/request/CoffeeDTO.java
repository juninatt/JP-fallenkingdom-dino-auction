package se.pbt.mrcoffee.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.product.Coffee;

import java.math.BigDecimal;

/**
 * Represents a Data Transfer Object for a {@link Coffee} product.
 * <p>
 * This record encapsulates the attributes specific to a coffee product, providing a means to transfer this
 * information between different parts of the system or between the system and its clients.
 */
public record CoffeeDTO(
        @NotBlank
        @Size(max = 25)
        String origin,

        @NotBlank
        @Size(max = 10)
        String roastLevel,

        @NotBlank
        @Size(max = 255)
        String flavorNotes,

        @NotBlank
        @Size(max = 10)
        String caffeineContent,

        @NotBlank
        String name,

        @NotBlank
        String description,

        @NotNull
        BigDecimal price
)
{}


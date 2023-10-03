package se.pbt.dinoauction.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;

import java.math.BigDecimal;

/**
 * A DinosaurDTO for the {@link Dinosaur} class used for transferring dinosaur product information.
 * It includes details like the name, species, description, and gender of the dinosaur, as well as its weight in kilograms and price in dollars.
 */
public record DinosaurDTO(
        @NotBlank(message = "Name is required")
        String name,
        @NotBlank(message = "Species is required")
        String species,
        @NotBlank(message = "Description is required")
        String description,
        @NotBlank(message = "Gender is required")
        String gender,
        @NotNull(message = "Age is required")
        double age,
        @Positive(message = "Weight must be greater than zero")
        int kgWeight,
        @NotNull(message = "Price cannot be null")
        BigDecimal dollarPrice
)
{}


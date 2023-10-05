package se.pbt.dinoauction.model.dto;

import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;

import java.math.BigDecimal;

/**
 * A DinoCardDataDTO for displaying {@link Dinosaur} information on a card.
 * It includes properties like the name, species, gender, age in years, and weight in kilograms
 * of the dinosaur, as well as its description, price in dollars, and an image resource.
 */
public record DinoCardDataDTO (
        String name,
        String species,
        String gender,
        double ageInYears,
        int weightInKg,
        String description,
        BigDecimal priceInDollar,
        String imageResource
){
}

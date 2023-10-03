package se.pbt.dinoauction.model.entity.auctionitem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Represents an entity class for a Dinosaur which extends {@link AuctionItem}.
 * This entity contains various attributes to describe a dinosaur, such as its species, gender, age, and weight.
 */
@Entity
@Table(name = "dinosaurs")
public class Dinosaur extends AuctionItem {

    @NotBlank(message = "Species is required")
    private String species;

    @NotBlank(message = "Gender is required")
    private String gender;

    @NotNull(message = "Aeg is required")
    private double ageInYears;

    @Positive(message = "Weight is required")
    private int weightInKg;

    /**
     * No-args constructor for JPA operations.
     */
    public Dinosaur() {
        super();
    }

    public Dinosaur(String name, String species, String gender, double ageInYears, int weightInKg,
                    String description, BigDecimal priceInDollar) {
        super(name, description, priceInDollar);
        this.species = species;
        this.gender = gender;
        this.ageInYears = ageInYears;
        this.weightInKg = weightInKg;
    }

    // Getters and setters

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getAgeInYears() {
        return ageInYears;
    }

    public void setAgeInYears(double ageInYears) {
        this.ageInYears = ageInYears;
    }

    public int getWeightInKg() {
        return weightInKg;
    }

    public void setWeightInKg(int weightInKg) {
        this.weightInKg = weightInKg;
    }
}

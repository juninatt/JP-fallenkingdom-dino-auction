package se.pbt.dinoauction.model.auctionitem;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

/**
 * Represents an entity class for a Dinosaur, extending AuctionItem.
 * Holds details like species, gender, and weight of the Dinosaur.
 */
@Entity
@Table(name = "dinosaurs")
public class Dinosaur extends AuctionItem {

    @NotBlank(message = "Species is required")
    private String species;

    @NotBlank(message = "Gender is required")
    private String gender;

    @Positive(message = "Weight is required")
    private int kgWeight;

    /**
     * No-args constructor for JPA operations.
     */
    public Dinosaur() {
        super();
    }

    /**
     * Parameterized constructor.
     *
     * @param name        Name of the dinosaur.
     * @param description Description of the dinosaur.
     * @param price       Auction price.
     * @param species     Species name.
     * @param gender      Gender.
     * @param kgWeight    Weight in kilograms.
     */
    public Dinosaur(String name, String description, BigDecimal price,
                    String species, String gender, int kgWeight) {
        super(name, description, price);
        this.species = species;
        this.gender = gender;
        this.kgWeight = kgWeight;
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

    public int getKgWeight() {
        return kgWeight;
    }

    public void setKgWeight(int kgWeight) {
        this.kgWeight = kgWeight;
    }
}

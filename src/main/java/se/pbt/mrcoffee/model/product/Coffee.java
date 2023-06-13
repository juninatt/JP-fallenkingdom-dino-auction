package se.pbt.mrcoffee.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

/**
 * Represents a coffee product in the system, extending the Product base class.
 */
@Entity
public class Coffee extends Product {

    @Column(nullable = false)
    private String origin;

    @Column(nullable = false)
    private String roastLevel;

    @Column(nullable = false)
    private String flavorNotes;

    @Column(nullable = false)
    private String caffeineContent;

    /**
     * Default constructor for the Coffee class.
     */
    public Coffee() {
        super();
    }

    /**
     * Parameterized constructor for the Coffee class.
     *
     * @param name            The name of the coffee.
     * @param description     The description of the coffee.
     * @param price           The price of the coffee.
     * @param origin          The origin of the coffee beans.
     * @param roastLevel      The level of roast for the coffee beans.
     * @param flavorNotes     The flavor notes of the coffee.
     * @param caffeineContent The caffeine content in the coffee.
     */
    public Coffee(String name, String description, BigDecimal price, String origin, String roastLevel,
                  String flavorNotes, String caffeineContent) {
        // Call the super class constructor
        super(name, description, price);
        this.origin = origin;
        this.roastLevel = roastLevel;
        this.flavorNotes = flavorNotes;
        this.caffeineContent = caffeineContent;
    }

    /**
     * Get the origin of the coffee beans.
     *
     * @return The origin of the coffee beans.
     */
    public String getOrigin() {
        return origin;
    }

    /**
     * Set the origin of the coffee beans.
     *
     * @param origin The origin of the coffee beans.
     */
    public void setOrigin(String origin) {
        this.origin = origin;
    }

    /**
     * Get the roast level of the coffee beans.
     *
     * @return The roast level of the coffee beans.
     */
    public String getRoastLevel() {
        return roastLevel;
    }

    /**
     * Set the roast level of the coffee beans.
     *
     * @param roastLevel The roast level of the coffee beans.
     */
    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    /**
     * Get the flavor notes of the coffee.
     *
     * @return The flavor notes of the coffee.
     */
    public String getFlavorNotes() {
        return flavorNotes;
    }

    /**
     * Set the flavor notes of the coffee.
     *
     * @param flavorNotes The flavor notes of the coffee.
     */
    public void setFlavorNotes(String flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    /**
     * Get the caffeine content in the coffee.
     *
     * @return The caffeine content in the coffee.
     */
    public String getCaffeineContent() {
        return caffeineContent;
    }

    /**
     * Set the caffeine content in the coffee.
     *
     * @param caffeineContent The caffeine content in the coffee.
     */
    public void setCaffeineContent(String caffeineContent) {
        this.caffeineContent = caffeineContent;
    }
}

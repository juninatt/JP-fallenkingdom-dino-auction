package se.pbt.mrcoffee.model.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

/**
 * Represents a coffee product in the system, extending the Product base class.
 */
@Entity
public class Coffee extends Product {

    @NotBlank(message = "Origin is required")
    @Column(nullable = false)
    private String origin;

    @NotBlank(message = "Roast level is required")
    @Column(nullable = false)
    private String roastLevel;

    @NotBlank(message = "Flavour notes is required")
    @Column(nullable = false)
    private String flavorNotes;

    @NotBlank(message = "Caffeine content is required")
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
    public Coffee(@NotBlank String name, @NotBlank String description, @NotNull BigDecimal price,
                  @NotBlank String origin, @NotBlank String roastLevel,
                  @NotBlank String flavorNotes, @NotBlank String caffeineContent) {
        super(name, description, price);
        this.origin = origin;
        this.roastLevel = roastLevel;
        this.flavorNotes = flavorNotes;
        this.caffeineContent = caffeineContent;
    }


    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getRoastLevel() {
        return roastLevel;
    }

    public void setRoastLevel(String roastLevel) {
        this.roastLevel = roastLevel;
    }

    public String getFlavorNotes() {
        return flavorNotes;
    }

    public void setFlavorNotes(String flavorNotes) {
        this.flavorNotes = flavorNotes;
    }

    public String getCaffeineContent() {
        return caffeineContent;
    }

    public void setCaffeineContent(String caffeineContent) {
        this.caffeineContent = caffeineContent;
    }
}

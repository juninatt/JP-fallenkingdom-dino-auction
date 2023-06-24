package se.pbt.mrcoffee.model.product;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

/**
 * Represents a coffee product in the system, extending the Product base class.
 */
@Entity
public class Coffee extends Product {

    @NotBlank(message = "Origin is required")
    @Size(max = 25, message = "Origin can't be more than 25 characters")
    private String origin;

    @NotBlank(message = "Roast level is required")
    @Size(max = 10, message = "Roast level can't be more than 10 characters")
    private String roastLevel;

    @NotBlank(message = "Flavour notes is required")
    @Size(max = 255, message = "Flavour notes can't be more than 255 characters")
    private String flavorNotes;

    @NotBlank(message = "Caffeine content is required")
    @Size(max = 10, message = "Caffeine content can't be more than 10 characters")
    private String caffeineContent;

    public Coffee() {
        super();
    }

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

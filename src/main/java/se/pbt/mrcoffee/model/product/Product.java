package se.pbt.mrcoffee.model.product;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product in the system.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false, updatable = false)
    private final LocalDateTime createdAt;


    protected Product() { createdAt = LocalDateTime.now(); }

    /**
     * Parameterized constructor for the Product class.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param price       The price of the product.
     */
    public Product(String name, String description, BigDecimal price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.createdAt = LocalDateTime.now();
    }

    /**
     * Get the ID of the product.
     *
     * @return The ID of the product.
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the ID of the product.
     *
     * @param id The ID of the product.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the name of the product.
     *
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the product.
     *
     * @param name The name of the product.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the description of the product.
     *
     * @return The description of the product.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the description of the product.
     *
     * @param description The description of the product.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the price of the product.
     *
     * @return The price of the product.
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Set the price of the product.
     *
     * @param price The price of the product.
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * Get the creation timestamp of the product.
     *
     * @return The creation timestamp of the product.
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

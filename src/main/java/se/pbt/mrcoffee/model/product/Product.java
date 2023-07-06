package se.pbt.mrcoffee.model.product;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.model.receipt.Receipt;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Represents a product in the system.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "products")
public abstract class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotBlank(message = "Price is required")
    private BigDecimal price;

    @Column(columnDefinition = "TIMESTAMP(0)", nullable = false, updatable = false)
    private final LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;


    /**
     * Default constructor for the Product class.
     * Sets the creation timestamp to the current date and time.
     */
    protected Product() { createdAt = LocalDateTime.now().withNano(0); }

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
        createdAt = LocalDateTime.now().withNano(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }
}

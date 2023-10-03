package se.pbt.dinoauction.model.entity.auctionitem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import se.pbt.dinoauction.model.entity.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * An abstract base class representing an auction item.
 * <p>
 * The {@code AuctionItem} class captures common properties for all items that can be auctioned.
 * It serves as a superclass for more specific types of auction items, such as dinosaurs.
 * </p>
 * <p>
 * Each {@code AuctionItem} entity is associated with an optional transaction,
 * represented by the {@link Transaction} class.
 * </p>
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "auction-items")
public abstract class AuctionItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    private String description;

    @NotNull(message = "Price is required")
    private BigDecimal priceInDollar;

    private String imageResource;

    @Column(columnDefinition = "TIMESTAMP(0)", nullable = false, updatable = false)
    private final LocalDateTime createdAt;

    /**
     * Represents the relationship to the {@link Transaction} associated with this entity.
     * This entity is not the owner; the foreign key is maintained in the Transaction table.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;


    /**
     * No-argument constructor for JPA compatibility.
     * Automatically sets the creation timestamp to the current date and time.
     */
    protected AuctionItem() { createdAt = LocalDateTime.now().withNano(0); }

    /**
     * Parameterized constructor for creating a new {@code AuctionItem} entity.
     * Automatically sets the creation timestamp to the current date and time.
     */
    public AuctionItem(String name, String description, BigDecimal priceInDollar) {
        this.name = name;
        this.description = description;
        this.priceInDollar = priceInDollar;
        createdAt = LocalDateTime.now().withNano(0);
    }

    // Getter and setters

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

    public BigDecimal getPriceInDollar() {
        return priceInDollar;
    }

    public void setPriceInDollar(BigDecimal priceInDollar) {
        this.priceInDollar = priceInDollar;
    }

    public String getImageResource() {
        return imageResource;
    }

    public void setImageResource(String imageResource) {
        this.imageResource = imageResource;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

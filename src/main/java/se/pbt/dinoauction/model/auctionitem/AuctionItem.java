package se.pbt.dinoauction.model.auctionitem;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import se.pbt.dinoauction.model.transaction.Transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    private BigDecimal dollarPrice;

    @Column(columnDefinition = "TIMESTAMP(0)", nullable = false, updatable = false)
    private final LocalDateTime createdAt;

    /**
     * Represents the relationship to the {@link Transaction} associated with this entity.
     * This entity is not the owner; the foreign key is maintained in the Transaction table.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    private Transaction transaction;


    /**
     * Default constructor for the Product class.
     * Sets the creation timestamp to the current date and time.
     */
    protected AuctionItem() { createdAt = LocalDateTime.now().withNano(0); }

    /**
     * Parameterized constructor for the Product class.
     *
     * @param name        The name of the product.
     * @param description The description of the product.
     * @param dollarPrice       The dollarPrice of the product.
     */
    public AuctionItem(String name, String description, BigDecimal dollarPrice) {
        this.name = name;
        this.description = description;
        this.dollarPrice = dollarPrice;
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

    public BigDecimal getDollarPrice() {
        return dollarPrice;
    }

    public void setDollarPrice(BigDecimal dollarPrice) {
        this.dollarPrice = dollarPrice;
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

package se.pbt.mrcoffee.model.receipt;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.product.Product;
import se.pbt.mrcoffee.model.purchase.Purchase;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

/**
 * Represents a receipt for a purchase within the system.
 * <p>
 * The Receipt class contains details about the purchase, such as total amount,
 * any applied discount codes and amounts, order number, and creation time.
 * <p>
 * A receipt is associated with a list of {@link Product} objects that were part of the purchase,
 * and a link to the corresponding {@link Purchase} entity.
 */
@Entity
@Table(name = "receipts")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Total amount is required")
    @DecimalMin(value = "0", message = "Total amount cant be below 0")
    private BigDecimal totalAmount;

    @Size(max = 255)
    private String discountCode;

    @DecimalMin(value = "0", inclusive = false, message = "Discount amount cant be below 0")
    private BigDecimal discountAmount;

    @NotBlank(message = "Order number is required")
    @Size(max = 255)
    private String orderNumber;

    @NotNull
    private final LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "receipt",
            fetch = FetchType.EAGER
    )
    private List<Product> products;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;


    public Receipt() {
        createdAt = LocalDateTime.now();
    }

    public Receipt(
            @NotBlank BigDecimal totalAmount,
            String discountCode,
            BigDecimal discountAmount,
            @NotBlank String orderNumber
    ) {
        this.totalAmount = totalAmount;
        this.discountCode = discountCode;
        this.discountAmount = discountAmount;
        this.orderNumber = orderNumber;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    // Overridden methods

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Receipt receipt = (Receipt) o;
        return Objects.equals(id, receipt.id) &&
                Objects.equals(totalAmount, receipt.totalAmount) &&
                Objects.equals(discountCode, receipt.discountCode) &&
                Objects.equals(discountAmount, receipt.discountAmount) &&
                Objects.equals(orderNumber, receipt.orderNumber) &&
                Objects.equals(createdAt, receipt.createdAt) &&
                Objects.equals(products, receipt.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalAmount, discountCode, discountAmount, orderNumber, createdAt, products);
    }
}

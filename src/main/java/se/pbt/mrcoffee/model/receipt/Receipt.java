package se.pbt.mrcoffee.model.receipt;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.product.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "receipt")
public class Receipt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long receiptId;

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

    /**
     * Represents the list of products associated with this Receipt.
     * The relationship is defined as a one-to-many mapping, where Receipt is the owner side.
     * The products are eagerly loaded, and any operations performed on Receipt will be cascaded to the associated products.
     */
    @OneToMany(
            mappedBy = "receipt",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    private List<Product> products;

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


    public Long getReceiptId() {
        return receiptId;
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
}

package se.pbt.mrcoffee.model.order;

import jakarta.persistence.*;

/**
 * Represents a payment made for a purchase, including details such as the status of the payment and the method used.
 * <p>
 * The payment can have different statuses, e.g., Pending, Completed, Failed,
 * and may be made using various methods like Credit Card, PayPal, Cash, etc.
 * <p>
 * It is associated with a {@link Order} object that represents the purchase for which the payment is made.
 */
@Entity
@Table(name = "payments")
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Status of the payment, e.g. Pending, Completed, Failed.
     */
    private String status;

    /**
     * Payment method, e.g. Credit Card, Pay Pal, Cash etc.
     */
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Order order;

    /**
     * Default constructor required for JPA.
     */
    public Payment() {}

    /**
     * Constructs a Payment object with the given purchase.
     *
     * @param order The purchase associated with the payment.
     */
    public Payment(String status, String paymentMethod, Order order) {
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.order = order;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setPurchase(Order order) {
        this.order = order;
    }

    public Order getPurchase() {
        return order;
    }
}

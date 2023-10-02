package se.pbt.dinoauction.model.entity.transaction;

import jakarta.persistence.*;

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

    /**
     * Represents the relationship to the {@link Transaction} associated with this Payment.
     * This entity is not the owner; the foreign key 'payment_id' is managed by the Transaction entity.
     */
    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    /**
     * Default constructor required for JPA.
     */
    public Payment() {}

    /**
     * Constructs a Payment object with the given purchase.
     *
     * @param transaction The purchase associated with the payment.
     */
    public Payment(String status, String paymentMethod, Transaction transaction) {
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.transaction = transaction;
    }

    // Getters and setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}

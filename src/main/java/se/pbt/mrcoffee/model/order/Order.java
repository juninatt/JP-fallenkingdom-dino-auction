package se.pbt.mrcoffee.model.order;

import jakarta.persistence.*;
import se.pbt.mrcoffee.model.user.Customer;

/**
 * Represents a purchase made within the system.
 * <p>
 * This class captures the details of a purchase transaction, including the associated receipt,
 * payment, and the customer who made the purchase.
 * <p>
 * Each purchase is linked to a single {@link Receipt} and {@link Payment} object, defining a one-to-one
 * relationship with both. The {@link Customer} who made the purchase is referenced as a many-to-one
 * relationship, as a {@link Customer} can make multiple purchases.
 * <p>
 * The Purchase class acts as a central point for linking various aspects of a transaction,
 * and can be used for tracking and managing purchases within the system.
 */
@Entity
@Table(name = "purchases")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "order")
    private Receipt receipt;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Order() {
    }

    public Order(Receipt receipt, Payment payment, Customer customer) {
        this.receipt = receipt;
        this.payment = payment;
        this.customer = customer;
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}

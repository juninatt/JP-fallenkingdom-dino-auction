package se.pbt.mrcoffee.model.purchase;

import jakarta.persistence.*;
import se.pbt.mrcoffee.model.payment.Payment;
import se.pbt.mrcoffee.model.receipt.Receipt;
import se.pbt.mrcoffee.model.user.Customer;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "purchase")
    private Receipt receipt;

    @OneToOne(mappedBy = "purchase")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    public Purchase() {
    }

    public Purchase(Receipt receipt, Payment payment, Customer customer) {
        this.receipt = receipt;
        this.payment = payment;
        this.customer = customer;
    }

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

    // Getters and setters
}

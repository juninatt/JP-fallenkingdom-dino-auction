package se.pbt.mrcoffee.model.payment;

import jakarta.persistence.*;
import se.pbt.mrcoffee.model.purchase.Purchase;

/**
 * Represents a payment made for a purchase.
 */
@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne
    @JoinColumn(name = "purchase_id")
    private Purchase purchase;


    public Payment() {}

    public Payment(Purchase purchase) {
        this.purchase = purchase;
    }


    public Long getPaymentId() {
        return paymentId;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public Purchase getPurchase() {
        return purchase;
    }
}

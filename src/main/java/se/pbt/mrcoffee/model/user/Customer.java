package se.pbt.mrcoffee.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.enums.CustomerLevel;
import se.pbt.mrcoffee.model.purchase.Purchase;

import java.util.List;

/**
 * Represents a Customer in the system.
 * Manages customer-specific attributes and purchase history.
 */
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends MrCoffeeUser {

    private CustomerLevel customerLevel;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    /**
     * No-args constructor for JPA operations.
     */
    public Customer() {}

    public Customer(@NotBlank String username, @NotBlank String password, CustomerLevel customerLevel) {
        super(username, password);
        this.customerLevel = customerLevel;
    }

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
        purchase.setCustomer(this);
    }

    // Getters and setters

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}

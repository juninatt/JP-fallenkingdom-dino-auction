package se.pbt.mrcoffee.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.enums.CustomerLevel;
import se.pbt.mrcoffee.model.purchase.Purchase;

import java.util.List;

@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public class Customer extends MrCoffeeUser {

    private CustomerLevel customerLevel;

    @OneToMany(mappedBy = "customer")
    private List<Purchase> purchases;

    public Customer() {}

    public Customer(
            @NotBlank String username,
            @NotBlank String password,
            CustomerLevel customerLevel,
            List<Purchase> purchases
    ) {
        super(username, password);
        this.customerLevel = customerLevel;
        this.purchases = purchases;
    }

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

    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }
}

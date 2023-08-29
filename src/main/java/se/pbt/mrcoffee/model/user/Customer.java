package se.pbt.mrcoffee.model.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.enums.CustomerLevel;
import se.pbt.mrcoffee.model.order.Order;

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
    private List<Order> orders;

    /**
     * No-args constructor for JPA operations.
     */
    public Customer() {}

    public Customer(@NotBlank String username, @NotBlank String password, CustomerLevel customerLevel) {
        super(username, password);
        this.customerLevel = customerLevel;
    }

    public void addPurchase(Order order) {
        orders.add(order);
        order.setCustomer(this);
    }

    // Getters and setters

    public CustomerLevel getCustomerLevel() {
        return customerLevel;
    }

    public void setCustomerLevel(CustomerLevel customerLevel) {
        this.customerLevel = customerLevel;
    }

    public List<Order> getPurchases() {
        return orders;
    }

    public void setPurchases(List<Order> orders) {
        this.orders = orders;
    }
}

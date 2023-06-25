package se.pbt.mrcoffee.model.user;

import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import se.pbt.mrcoffee.enums.CustomerType;

@Table(name = "customer")
public class Customer extends MrCoffeeUser {

    private CustomerType customerType;

    public Customer() {}

    public Customer(
            String username,
            String password,
            @NotBlank CustomerType customerType
    ) {
        super(username, password);
        this.customerType = customerType;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }
}

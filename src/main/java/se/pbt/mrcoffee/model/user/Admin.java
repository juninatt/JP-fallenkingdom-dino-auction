package se.pbt.mrcoffee.model.user;

import jakarta.persistence.Entity;

@Entity
public class Admin extends MrCoffeeUser {

    /**
     * No-args constructor for JPA operations.
     */
    public Admin() {}

    public Admin(String username, String password) {
        super(username, password);
    }
}

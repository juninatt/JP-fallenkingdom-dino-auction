package se.pbt.mrcoffee.model.user;

import jakarta.persistence.Entity;

@Entity
public class Admin extends MrCoffeeUser {

    public Admin() {}

    public Admin(String username, String password) {
        super(username, password);
    }
}

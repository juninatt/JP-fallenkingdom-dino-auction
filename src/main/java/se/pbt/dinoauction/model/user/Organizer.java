package se.pbt.dinoauction.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity
@Table(name = "organizer")
@Inheritance(strategy = InheritanceType.JOINED)
public class Organizer extends AppUser {

    /**
     * No-args constructor for JPA operations.
     */
    public Organizer() {}

    public Organizer(String username, String password) {
        super(username, password);
    }
}

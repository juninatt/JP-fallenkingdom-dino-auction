package se.pbt.mrcoffee.model.contact;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.Objects;

/**
 * Represents contact information for a customer.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class CustomerContact extends Contact {

    @NotBlank(message = "First name is required")
    @Size(max = 25, message = "First name cant be more than 25 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 30, message = "Last name cant be more than 30 characters")
    private String lastName;


    public CustomerContact() {}

    public CustomerContact(String email, String phoneNumber, String additionalInfo, MrCoffeeUser mrCoffeeUser, String firstName, String lastName) {
        super(email, phoneNumber, additionalInfo, mrCoffeeUser);
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerContact that = (CustomerContact) o;
        return Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName);
    }
}

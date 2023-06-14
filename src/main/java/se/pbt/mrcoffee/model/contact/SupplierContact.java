package se.pbt.mrcoffee.model.contact;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;


/**
 * Represents a supplier in the system
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class SupplierContact extends Contact {

    @Column(name = "company_name", nullable = false)
    @NotBlank(message = "Company name is required")
    @Size( max = 30, message = "Cant be longer than 30 characters")
    private String companyName;

    @Column(name = "industry", nullable = false)
    @NotBlank(message = "Industry is required")
    @Size( max = 30, message = "Cant be longer than 30 characters")
    private String industry;

    /**
     * Default constructor for SupplierContact class
     */
    public SupplierContact() {}

    /**
     * Constructor for the PrivateContact class.
     *
     * @param email           The email address of the supplier.
     * @param phoneNumber     The phone number of the supplier.
     * @param additionalInfo  Additional information about the supplier.
     * @param mrCoffeeUser            The user associated with the supplier.
     * @param companyName     The company name of the supplier.
     * @param industry        The industry of the supplier.
     */
    public SupplierContact(String email, String phoneNumber, String additionalInfo, MrCoffeeUser mrCoffeeUser, String companyName, String industry) {
        super(email, phoneNumber, additionalInfo, mrCoffeeUser);
        this.companyName = companyName;
        this.industry = industry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}

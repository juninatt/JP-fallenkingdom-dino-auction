package se.pbt.mrcoffee.testobject;

import se.pbt.mrcoffee.dto.request.CustomerContactDTO;
import se.pbt.mrcoffee.dto.request.PurchaseDTO;
import se.pbt.mrcoffee.dto.request.SupplierContactDTO;
import se.pbt.mrcoffee.enums.CustomerLevel;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.model.contact.SupplierContact;
import se.pbt.mrcoffee.model.payment.Payment;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.model.purchase.Purchase;
import se.pbt.mrcoffee.model.receipt.Receipt;
import se.pbt.mrcoffee.model.user.Customer;

import java.math.BigDecimal;

/**
 * Factory class to create test objects.
 */
public class TestObjectFactory {

    // Product objects

    /**
     * @return A {@link Coffee} object with pre-set field values
     */
    public static Coffee createCoffee() {
        return new Coffee(
                "Americano",
                "Black",
                BigDecimal.valueOf(9.99),
                "America",
                "Dark",
                "Bitter",
                "Intense"
        );
    }

    // User objects

    /**
     * @return A {@link Customer object with pre-set field values}
     */
    public static Customer createCustomer() {
        return new Customer(
                "JamesGosling",
                "JamesGosling",
                CustomerLevel.VIP);
    }


    // Address objects

    /**
     * @return A {@link Address} object with pre-set field values
     */
    public static Address createAddress() {
        return new Address(
                "Elm Street",
                1,
                1,
                "Göteborg",
                "12345",
                "Sweden"
        );
    }

    /**
     * @return A {@link AddressDTO} object with pre-set field values
     */
    public static AddressDTO createAddressDTO() {
        return new AddressDTO(
                "Elm Street",
                1,
                1,
                "Göteborg",
                "12345",
                "Sweden"
        );
    }

    /**
     * @return A {@link AddressResponseDTO} object with pre-set field values
     */
    public static AddressResponseDTO createAddressResponseDTO(long id) {
        return new AddressResponseDTO(
                id,
                "Elm Street",
                1,
                1,
                "Göteborg",
                "12345",
                "Sweden"
        );
    }

    // Contact objects

    /**
     * @return A {@link CustomerContact} object with pre-set field values
     */
    public static CustomerContact createCustomerContact() {
        return new CustomerContact(
                "james.gosling@coffee.se",
                "0735 666 666",
                "Apartment",
                null,
                "Petter",
                "Bergström"
        );
    }

    public static CustomerContactDTO createCustomerContactDTO() {
        return new CustomerContactDTO(
                "James",
                "Gosling",
                "james.gosling@coffee.se",
                "0735 666 666",
                "Apartment"
        );
    }

    /**
     * @return A {@link SupplierContact} object with pre-set field values
     */
    public static SupplierContact createSupplierContact() {
        return new SupplierContact(
                "nespresso@coffee.se",
                "031-12345",
                "Insolvent",
                null,
                "Nespresso",
                "Coffee"
        );
    }

    /**
     * @return A {@link SupplierContactDTO} object with pre-set field values
     */
    public static SupplierContactDTO createSupplierContactDTO() {
        return new SupplierContactDTO(
                "email@example.com",
                "1234567890",
                "info",
                "Company Name",
                "Industry");

    }

    // Receipt objects

    /**
     * @return A {@link Receipt} object with pre-set field values
     */
    public static Receipt createReceipt() {
        return new Receipt(
                BigDecimal.valueOf(999.99),
                "Secret",
                BigDecimal.valueOf(9.99),
                "1"
        );
    }

    // Purchase objects

    /**
     * @return A {@link Purchase} object with pre-set field values
     */
    public static Purchase createPurchase() {
        return new Purchase(
                createReceipt(),
                createPayment(),
                createCustomer()
        );
    }

    /**
     * @return A {@link PurchaseDTO} object with pre-set field values
     */
    public static PurchaseDTO createPurchaseDTO() {
        return new PurchaseDTO(
                1L,
                1L,
                1L
        );
    }

    // Payment objects

    /**
     * @return A {@link Payment} object with pre-set field values
     */
    private static Payment createPayment() {
        return new Payment(
                "Pending",
                "Cash",
                null
        );
    }
}

package se.pbt.mrcoffee.integrationtest.mapper;

import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import org.junit.jupiter.api.Test;
import se.pbt.mrcoffee.mapper.CustomerContactMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CustomerContactMapperTest {

    private final CustomerContactMapper mapper = CustomerContactMapper.INSTANCE;

    @Test
    public void toCustomerContactDTO() {
        var testUser = TestObjectFactory.createCustomer();
        var customerContact = TestObjectFactory.createCustomerContact();
        customerContact.setUser(testUser);

        var customerContactDTO = mapper.toCustomerContactDTO(customerContact);

        assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
        assertEquals(customerContact.getLastName(), customerContactDTO.lastName());
        assertEquals(customerContact.getEmail(), customerContactDTO.email());
        assertEquals(customerContact.getPhoneNumber(), customerContactDTO.phoneNumber());
        assertEquals(customerContact.getAdditionalInfo(), customerContactDTO.additionalInfo());
    }

    @Test
    public void toCustomerContact() {
        var customerContactDTO = TestObjectFactory.createCustomerContactDTO();

        var customerContact = mapper.toCustomerContact(customerContactDTO);

        assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
        assertEquals(customerContact.getFirstName(), customerContactDTO.firstName());
        assertEquals(customerContactDTO.email(), customerContact.getEmail());
        assertEquals(customerContactDTO.phoneNumber(), customerContact.getPhoneNumber());
        assertEquals(customerContactDTO.additionalInfo(), customerContact.getAdditionalInfo());
    }

    @Test
    void toCustomerContactResponseDTO_ShouldReturnCorrectDTO() {
        // Arrange
        var contact = TestObjectFactory.createCustomerContact();

        // Act
        var dto = mapper.toCustomerContactResponseDTO(contact);

        // Assert
        assertEquals(contact.getEmail(), dto.email());
        assertEquals(contact.getPhoneNumber(), dto.phoneNumber());
        assertEquals(contact.getAdditionalInfo(), dto.additionalInfo());

        // Assert address mapping
        assertEquals(contact.getAddresses().size(), dto.addresses().size());
    }
}

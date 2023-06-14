package se.pbt.mrcoffee.service.privatecustomercontact;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.model.contact.PrivateCustomerContact;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PrivateCustomerContactServiceTest {

    @Autowired
    private PrivateCustomerContactService contactService;

    @Test
    @DisplayName("Test creating a private customer contact")
    public void testCreatePrivateCustomerContact() {
        // Create a test private customer contact
        PrivateCustomerContact contact = new PrivateCustomerContact("test@email.com",
                "1234567890", "Additional info", null, "John", "Doe");

        // Create the contact
        PrivateCustomerContact createdContact = contactService.createPrivateCustomerContact(contact);

        // Assert the contact is created and has the correct properties
        assertNotNull(createdContact.getContactId());
        assertEquals("John", createdContact.getFirstName());
        assertEquals("Doe", createdContact.getLastName());
    }

    @Test
    @DisplayName("Test getting all private customer contacts")
    public void testGetAllPrivateCustomerContacts() {
        // Retrieve all private customer contacts
        List<PrivateCustomerContact> contacts = contactService.getAllPrivateCustomerContacts();

        // Assert the contacts list is not null
        assertNotNull(contacts);
    }

    @Test
    @DisplayName("Test updating a private customer contact")
    public void testUpdatePrivateCustomerContact() {
        // Create a test private customer contact
        PrivateCustomerContact contact = new PrivateCustomerContact("test@email.com",
                "1234567890", "Additional info", null, "John", "Doe");

        // Create the contact
        PrivateCustomerContact createdContact = contactService.createPrivateCustomerContact(contact);

        // Update the contact
        createdContact.setFirstName("Updated");
        PrivateCustomerContact updatedContact = contactService.updatePrivateCustomerContact(createdContact.getContactId(), createdContact);

        // Assert the contact is updated and has the correct property value
        assertNotNull(updatedContact);
        assertEquals("Updated", updatedContact.getFirstName());
    }

    @Test
    @DisplayName("Test deleting a private customer contact")
    public void testDeletePrivateCustomerContact() {
        // Create a test private customer contact
        PrivateCustomerContact contact = new PrivateCustomerContact("test@email.com",
                "1234567890", "Additional info", null, "John", "Doe");

        // Create the contact
        PrivateCustomerContact createdContact = contactService.createPrivateCustomerContact(contact);

        // Delete the contact
        boolean isDeleted = contactService.deletePrivateCustomerContact(createdContact.getContactId());

        // Assert the contact is deleted
        assertTrue(isDeleted);
    }
}

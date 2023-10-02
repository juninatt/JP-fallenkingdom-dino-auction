package se.pbt.dinoauction.integrationtest.service;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.dinoauction.repository.contact.ContactRepository;
import se.pbt.dinoauction.service.ContactService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.List;

@SpringBootTest
@DisplayName("ContactService Integration Tests")
public class ContactServiceTest {

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    public void setUp() {
        // Clear database of existing contacts
        contactRepository.deleteAll();

        // Populate database with test contacts
        var contact1 = TestObjectFactory.contact();
        var contact2 = TestObjectFactory.contact();
        contactRepository.saveAll(List.of(contact1, contact2));
    }

    @Nested
    class GetAllContactsTest {

        @Test
        @DisplayName("Returns all contacts from the database")
        public void return_allContacts_fromDatabase() {
            // Execute the method and capture the result
            var fetchedContacts = contactService.getAllContacts();

            // Validate the number of returned contacts matches expectation
            Assertions.assertEquals(2, fetchedContacts.size());
        }
    }

    @Nested
    class GetContactByIdTest {

        @Test
        @DisplayName("Returns null for a non-existing ID")
        public void return_null_forNonExistingId() {
            // Execute the method with a non-existing ID
            var fetchedContact = contactService.getContactById(100L);

            // Validate the result is null
            Assertions.assertNull(fetchedContact);
        }
    }

    @Nested
    class CreateNewContactTest {

        @Test
        @DisplayName("Creates new contact to the database")
        public void successfully_addsNewContact() {
            // Create a test contact object
            var newContact = TestObjectFactory.contactDTO();

            // Execute the method to create a new contact
            var createdContact = contactService.createContact(newContact);

            // Validate the new contact was successfully created
            Assertions.assertNotNull(createdContact);
            Assertions.assertEquals(3, contactService.getAllContacts().size());
        }
    }

    @Nested
    class DeleteContactTest {

        @Test
        @DisplayName("Fails to delete when the ID does not exist")
        public void fails_toDelete_nonExistingId() {
            // Execute the method to delete a contact with a non-existing ID
            var wasDeleted = contactService.deleteContact(100L);

            // Validate the contact was not deleted
            Assertions.assertFalse(wasDeleted);
            Assertions.assertEquals(2, contactRepository.findAll().size());
        }
    }
}

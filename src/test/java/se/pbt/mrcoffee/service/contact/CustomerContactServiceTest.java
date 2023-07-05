package se.pbt.mrcoffee.service.contact;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.exception.CustomerContactNotFoundException;
import se.pbt.mrcoffee.repository.contact.CustomerContactRepository;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("CustomerContactService:")
class CustomerContactServiceTest {

    @Autowired
    private CustomerContactRepository contactRepository;

    @Autowired
    private CustomerContactService contactService;


    @BeforeEach
    public void setUp() {
        contactRepository.deleteAll();
    }

    @Nested
    @DisplayName("createCustomerContact():")
    class CreateCustomerContactTests {

        @Test
        @DisplayName("Returns a CustomerContactResponseDTO object")
        void createCustomerContact_validInput_returnsCreatedContact() {
            // Create a test object
            var testContact = TestObjectFactory.createCustomerContact();

            // Call method to test and assign the returned value to result variable
            var result = contactService.createCustomerContact(testContact);

            // Assert the result is not null and of correct class
            assertNotNull(result);
            assertSame(CustomerContactResponseDTO.class, result.getClass());
        }

        @Test
        @DisplayName("Returns DTO same ID as saved object")
        void createCustomerContact_validInput_savesContactToRepository() {
            // Call method to be tested with test object and assign the object to variable after ID has been generated
            var testContact = contactService.createCustomerContact(TestObjectFactory.createCustomerContact());

            // Fetch object from database
            var result = contactRepository.findById(testContact.contactId())
                    .orElseThrow(CustomerContactNotFoundException::new);

            // Assert that the object in the database is equal to created object
            assertEquals(testContact.contactId(), result.getContactId());
        }
    }

    @Nested
    @DisplayName("getAllCustomerContacts():")
    class GetAllCustomerContactsTests {

        @Test
        @DisplayName("Returns a list of all customer contacts")
        void getAllCustomerContacts_returnsListOfAllContacts() {
            // Create test objects and save to database
            var contact1 = TestObjectFactory.createCustomerContact();
            var contact2 = TestObjectFactory.createCustomerContact();
            contactRepository.save(contact1);
            contactRepository.save(contact2);

            // Call method to test and assign returned value to result variable
            var result = contactService.getAllCustomerContacts();

            // Assert the result is not null and all objects are of correct class
            assertNotNull(result);
            assertEquals(2, result.size());
            assertSame(CustomerContactResponseDTO.class, result.get(0).getClass());
            assertSame(CustomerContactResponseDTO.class, result.get(1).getClass());
        }

        @Test
        @DisplayName("Returns an empty list when database is empty")
        void getAllCustomerContacts_noContacts_returnsEmptyList() {

            // Call the method to test and assign returned value to result variable
            var result = contactService.getAllCustomerContacts();

            // Assert the result is not null but empty
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    @DisplayName("getCustomerContactById():")
    class GetCustomerContactByIdTests {

        @Test
        @DisplayName("Returns a CustomerContact when provided with a valid ID")
        void getCustomerContactById_validId_returnsContact() {
            // Create test object and save to database
            var testContact = TestObjectFactory.createCustomerContact();
            var id = contactRepository.save(testContact).getContactId();

            // Call method to be tested and assign returned value to result variable
            var result = contactService.getCustomerContactById(id);

            // Assert the result is not null and of correct class
            assertNotNull(result);
            assertSame(CustomerContactResponseDTO.class, result.getClass());
        }

        @Test
        @DisplayName("Throws CustomerContactNotFoundException when provided with an invalid ID")
        void getCustomerContactById_invalidId_throwsException() {
            // Arrange
            var id = 1L;

            // Act & Assert
            assertThrows(CustomerContactNotFoundException.class,
                    () -> contactService.getCustomerContactById(id));
        }
    }

    @Nested
    @DisplayName("updateCustomerContact():")
    class UpdateCustomerContactTests {

        @Test
        @DisplayName("Returns updates email when provided with a valid ID")
        void  updateCustomerContact_validId_returnsUpdatedContact() {
            // Create test object and save it to database
            var originalContact = TestObjectFactory.createCustomerContact();
            contactRepository.save(originalContact);

            // Create object to update the old values with
            var updatedContact = TestObjectFactory.createCustomerContact();
            updatedContact.setEmail("updatedEmail@test.com");

            // Call method to test and assign returned value to result variable
            var result = contactService.updateCustomerContact(originalContact.getContactId(), updatedContact);

            // Assert result is not null, is of correct class and field is updated
            assertNotNull(result);
            assertSame(CustomerContactResponseDTO.class, result.getClass());
            assertEquals(updatedContact.getEmail(), result.email());
        }

        @Test
        @DisplayName("Throws CustomerContactNotFoundException when provided with an invalid ID")
        void updateCustomerContact_invalidId_throwsException() {
            // Assign bad ID and create test object
            var id = 666L;
            var updatedContact = TestObjectFactory.createCustomerContact();


            // Call method to be tested and assert an exception is thrown
            assertThrows(CustomerContactNotFoundException.class,
                    () -> contactService.updateCustomerContact(id, updatedContact));
        }
    }

    @Nested
    @DisplayName("deleteCustomerContact():")
    class DeleteCustomerContactTests {

        @Test
        @DisplayName("Deletes CustomerContact when provided with a valid ID")
        void deleteCustomerContact_validId_deletesContact() {
            // Create a test object and save to database
            var existingContact = TestObjectFactory.createCustomerContact();
            contactRepository.save(existingContact);


            // Call method to test
            contactService.deleteCustomerContact(existingContact.getContactId());

            // Assert the object was deleted from database
            assertEquals(0, contactRepository.findAll().size());
        }

        @Test
        @DisplayName("Throws CustomerContactNotFoundException when provided with an invalid ID")
        void deleteCustomerContact_invalidId_throwsException() {
            // Create bad ID
            var id = 666L;


            // Call method to test and assert an exception is thrown
            assertThrows(CustomerContactNotFoundException.class,
                    () -> contactService.deleteCustomerContact(id));
        }
    }

}

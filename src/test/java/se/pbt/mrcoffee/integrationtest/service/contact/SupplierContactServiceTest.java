package se.pbt.mrcoffee.integrationtest.service.contact;

import se.pbt.mrcoffee.testobject.TestObjectFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.repository.contact.SupplierContactRepository;
import se.pbt.mrcoffee.service.contact.SupplierContactService;

import java.util.List;

@SpringBootTest
public class SupplierContactServiceTest {

    @Autowired
    private SupplierContactService contactService;
    @Autowired
    private SupplierContactRepository contactRepository;


    @BeforeEach
    public void setUp() {
        // Clear existing data and initialize test data if needed
        contactRepository.deleteAll();
        // Initialize test data
        var supplier1 = TestObjectFactory.createSupplierContact();
        var supplier2 = TestObjectFactory.createSupplierContact();
        contactRepository.saveAll(List.of(supplier1, supplier2));
    }


    @Nested
    @DisplayName("getAllSupplierContacts()")
    class GetAllSupplierContactsTest {

        @Test
        @DisplayName("Returns all supplier-contacts in database")
        public void testGetAllSupplierContacts() {
            // Call tested method and assign returned value
            var supplierContacts = contactService.getAllSupplierContacts();

            // Assert the number of objects in database is the expected value
            Assertions.assertEquals(2, supplierContacts.size());
        }
    }


    @Nested
    @DisplayName("getSupplierContactById()")
    class GetSupplierContactByIdTest {

        @Test
        @DisplayName("Returns null when no object with passed ID exists")
        public void testGetSupplierContactByIdNonExistingId() {
            // Call tested method with ID that does not exist in database and assign returned value
            var supplierContact = contactService.getSupplierContactById(100L);

            // Assert the returned value is null
            Assertions.assertNull(supplierContact);
        }
    }


    @Nested
    @DisplayName("createSupplierContact()")
    class CreateSupplierContactTest {

        @Test
        @DisplayName("Creates new supplier-contact")
        public void testCreateSupplierContact() {
            // Create test object
            var newSupplierContact = TestObjectFactory.createSupplierContact();

            // Call the tested method with test object and assign the returned value
            var createdSupplierContact = contactService.createSupplierContact(newSupplierContact);

            // Assert the returned value is not null and a new supplier-contact was added to database
            Assertions.assertNotNull(createdSupplierContact);
            Assertions.assertEquals(3, contactService.getAllSupplierContacts().size());
        }
    }


    @Nested
    @DisplayName("deleteSupplierContact()")
    class DeleteSupplierContactTest {

        @Test
        @DisplayName("Returns false when passed ID does not exist in database")
        public void testDeleteSupplierContactNonExistingId() {
            // Call the tested method with ID that does not exist in database and assign returned value
            var deleted = contactService.deleteSupplierContact(100L);

            // Assert the returned value is correct(false) and that no supplier-contact was deleted
            Assertions.assertFalse(deleted);
            Assertions.assertEquals(2, contactService.getAllSupplierContacts().size());
        }
    }
}

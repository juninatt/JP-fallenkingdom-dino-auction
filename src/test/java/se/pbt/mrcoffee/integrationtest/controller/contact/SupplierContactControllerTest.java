package se.pbt.mrcoffee.integrationtest.controller.contact;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import se.pbt.mrcoffee.model.contact.SupplierContact;
import se.pbt.mrcoffee.repository.contact.SupplierContactRepository;
import se.pbt.mrcoffee.service.contact.SupplierContactService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class SupplierContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SupplierContactService supplierContactService;
    @Autowired
    private SupplierContactRepository supplierContactRepository;

    @BeforeEach
    void setup() {
        // Clear the supplier contact data before each test
        supplierContactRepository.deleteAll();;
    }

    @Test
    @DisplayName("GET /supplier-contacts - Returns List of All Supplier Contacts")
    void getAllSupplierContacts() throws Exception {
        // Arrange: Create and store a supplier contact
        var supplierContact = TestObjectFactory.createSupplierContact();
        var storedContact = supplierContactService.createSupplierContact(supplierContact);

        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/supplier-contacts"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(storedContact))));
    }

    @Test
    @DisplayName("GET /supplier-contacts/{id} - Returns Supplier Contact with given ID")
    void getSupplierContactById() throws Exception {
        // Arrange: Create and store a supplier contact
        var supplierContact = TestObjectFactory.createSupplierContact();
        var storedContact = supplierContactService.createSupplierContact(supplierContact);

        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/supplier-contacts/" + storedContact.getContactId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(storedContact)));
    }

    @Test
    @DisplayName("POST /supplier-contacts - Creates new Supplier Contact")
    void createSupplierContact() throws Exception {
        // Arrange: Create a supplier contact
        var supplierContact = TestObjectFactory.createSupplierContact();

        // Act and Assert: Perform POST request and validate the response
        mockMvc.perform(post("/supplier-contacts")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(supplierContact)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /supplier-contacts/{id} - Updates existing Supplier Contact")
    void updateSupplierContact() throws Exception {
        // Arrange: Create and store a supplier contact
        var supplierContact = TestObjectFactory.createSupplierContact();
        var storedContact = supplierContactService.createSupplierContact(supplierContact);

        // Create an updated supplier contact
        var updatedSupplierContact = new SupplierContact(
                "john.doe@example.com",
                "123",
                "In progress",
                null,
                "IKEA",
                "Furniture"
        );

        // Act and Assert: Perform PUT request and validate the response
        mockMvc.perform(put("/supplier-contacts/" + storedContact.getContactId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedSupplierContact)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /supplier-contacts/{id} - Deletes existing Supplier Contact")
    void deleteSupplierContact() throws Exception {
        // Arrange: Create and store a supplier contact
        var supplierContact = TestObjectFactory.createSupplierContact();
        var storedContact = supplierContactService.createSupplierContact(supplierContact);

        // Act and Assert: Perform DELETE request and validate the response
        mockMvc.perform(delete("/supplier-contacts/" + storedContact.getContactId()))
                .andExpect(status().isNoContent());
    }
}

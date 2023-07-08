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
import se.pbt.mrcoffee.dto.request.CustomerContactDTO;
import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import se.pbt.mrcoffee.repository.contact.CustomerContactRepository;
import se.pbt.mrcoffee.service.contact.CustomerContactService;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = {"ADMIN"})
public class CustomerContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerContactRepository contactRepository;

    @Autowired
    private CustomerContactService contactService;

    @BeforeEach
    void setup() {
        // Clear the contact data before each test
        contactRepository.deleteAll();
    }

    @Test
    @DisplayName("GET /customer-contact - Returns List of All Customer Contacts")
    void getAllPrivateCustomerContacts() throws Exception {
        // Arrange: Create and store a customer contact
        var contact = TestObjectFactory.createCustomerContact();
        var contactResponse = contactService.createCustomerContact(contact);

        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/customer-contact"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(contactResponse))));
    }

    @Test
    @DisplayName("GET /customer-contact/{contactId} - Returns Customer Contact with given ID")
    void getCustomerContactById() throws Exception {
        // Arrange: Create and store a customer contact
        var contact = TestObjectFactory.createCustomerContact();
        var storedContact = contactService.createCustomerContact(contact);


        // Act and Assert: Perform GET request and validate the response
        mockMvc.perform(get("/customer-contact/" + storedContact.contactId()))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(storedContact)));
    }

    @Test
    @DisplayName("POST /customer-contact - Creates new Customer Contact")
    void createPrivateCustomerContact() throws Exception {
        // Arrange: Create a customer contact
        var contact = TestObjectFactory.createCustomerContact();

        // Act and Assert: Perform POST request and validate the response
        mockMvc.perform(post("/customer-contact")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(contact)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("PUT /customer-contact/{contactId} - Updates existing Customer Contact")
    void updatePrivateCustomerContact() throws Exception {
        // Arrange: Create and store a customer contact
        var contact = TestObjectFactory.createCustomerContact();
        var storedContact = contactService.createCustomerContact(contact);

        // Create an updated customer contact
        var updatedContact = new CustomerContactDTO(
                "Djingis",
                "Khan",
                "djingis_k@gmail.com",
                "031-123456",
                "Ill tempered"
        );

        // Act and Assert: Perform PUT request and validate the response
        mockMvc.perform(put("/customer-contact/" + storedContact.contactId())
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(updatedContact)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("DELETE /customer-contact/{contactId} - Deletes existing Customer Contact")
    void deletePrivateCustomerContact() throws Exception {
        // Arrange: Create and store a customer contact
        var contact = TestObjectFactory.createCustomerContact();
        var storedContact = contactService.createCustomerContact(contact);

        // Act and Assert: Perform DELETE request and validate the response
        mockMvc.perform(delete("/customer-contact/" + storedContact.contactId()))
                .andExpect(status().isNoContent());
    }
}
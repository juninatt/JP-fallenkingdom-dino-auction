package se.pbt.dinoauction.integrationtest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import se.pbt.dinoauction.repository.contact.ContactRepository;
import se.pbt.dinoauction.service.ContactService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.util.Collections;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("ContractController Integration Tests:")
public class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ContactService contactService;
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setup() {
        // Clear the supplier contact data before each test
        contactRepository.deleteAll();
    }

    @Nested
    @WithMockUser(roles = {"ADMIN"})
    class AdminAccessTest {

        @Test
        @DisplayName("Returns a list of all contacts when accessed by an ADMIN")
        void return_allContacts_adminAccess() throws Exception {
            // Arrange: Create and store a Contact
            var contactData = TestObjectFactory.contactDTO();
            var savedContact = contactService.createContact(contactData);

            // Act and Assert: Perform GET request and validate the response
            mockMvc.perform(get("/contacts"))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(Collections.singletonList(savedContact))));
        }

        @Test
        @DisplayName("Returns a specific contact based on its ID when accessed by an ADMIN")
        void return_contactById_adminAccess() throws Exception {
            // Arrange: Create and store a Contact
            var contactData = TestObjectFactory.contactDTO();
            var savedContact = contactService.createContact(contactData);

            // Act and Assert: Perform GET request and validate the response
            mockMvc.perform(get("/contacts/" + savedContact.getId()))
                    .andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(savedContact)));
        }

        @Test
        @DisplayName("Creates a new contact when accessed by an ADMIN")
        void create_newContact_adminAccess() throws Exception {
            // Arrange: Create a Contact
            var contactData = TestObjectFactory.contactDTO();

            // Act and Assert: Perform POST request and validate the response
            mockMvc.perform(post("/contacts")
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(contactData)))
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Updates an existing contact when accessed by an ADMIN")
        void delete_existingContact_adminAccess() throws Exception {
            // Arrange: Create and store a Contact
            var contactData = TestObjectFactory.contactDTO();
            var savedContact = contactService.createContact(contactData);

            // Create an updated Contact
            var updatedSupplierContact = TestObjectFactory.contact();

            // Act and Assert: Perform PUT request and validate the response
            mockMvc.perform(put("/contacts/" + savedContact.getId())
                            .contentType("application/json")
                            .content(objectMapper.writeValueAsString(updatedSupplierContact)))
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Deletes an existing contact when accessed by an ADMIN")
        void deleteSupplierContact() throws Exception {
            // Arrange: Create and store a Contact
            var contactData = TestObjectFactory.contactDTO();
            var savedContact = contactService.createContact(contactData);
            System.out.println(savedContact.getId());
            // Act and Assert: Perform DELETE request and validate the response
            mockMvc.perform(delete("/contacts/" + savedContact.getId()))
                    .andExpect(status().isNoContent());
        }
    }

    @Nested
    @WithMockUser(roles = "PARTICIPANT")
    class ParticipantAccessTest {

    }
}

package se.pbt.mrcoffee.controller.contact;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.annotation.GlobalApiResponses;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.service.contact.CustomerContactService;

import java.util.List;

/**
 * The CustomerContactController is a REST controller that handles HTTP requests for the CustomerContact resource.
 * It provides endpoints to create, retrieve, update, and delete customer contacts.
 */
@RestController
@RequestMapping("/customer-contact")
public class CustomerContactController {

    private final CustomerContactService contactService;

    @Autowired
    public CustomerContactController(CustomerContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Retrieves a list of all stored {@link CustomerContact} objects in the database.
     *
     * @return A ResponseEntity containing a list of {@link CustomerContactResponseDTO}
     *         The HTTP status code is 200 (OK) if the operation is successful.
     */
    @GetMapping
    @Operation(summary = "Retrieve all customer contacts", description = "Retrieve a list of all customer contacts from the database")
    @GlobalApiResponses(schemaImplementation = CustomerContact.class)
    public ResponseEntity<List<CustomerContactResponseDTO>> getAllCustomerContacts() {
        List<CustomerContactResponseDTO> contacts = contactService.getAllCustomerContacts();
        return ResponseEntity.ok(contacts);
    }

    /**
     * Retrieves {@link CustomerContact} by its ID and returns a DTO representation of the contact.
     *
     * @param id The ID of the contact to retrieve.
     * @return A ResponseEntity containing a {@link CustomerContactResponseDTO} that represents the requested contact,
     *         or a 404 status if the contact does not exist.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve contact by ID", description = "Retrieve a specific contact from the database based on the contact ID")
    @GlobalApiResponses(schemaImplementation = CustomerContactResponseDTO.class)
    public ResponseEntity<CustomerContactResponseDTO> getCustomerContactById(@PathVariable long id) {
        var contactDetails = contactService.getCustomerContactById(id);
            return ResponseEntity.ok(contactDetails);
    }

    /**
     * Creates new {@link CustomerContact} object and stores it in the database.
     *
     * @param contactDetails The new contact details.
     * @return A ResponseEntity containing a {@link CustomerContactResponseDTO} with the stored information.
     */
    @PostMapping
    @Operation(summary = "Create a new customer contact", description = "Create a new customer contact and persist it to the database")
    @GlobalApiResponses(schemaImplementation = CustomerContactResponseDTO.class)
    public ResponseEntity<CustomerContactResponseDTO> createCustomerContact(@RequestBody CustomerContact contactDetails) {
        var createdContact = contactService.createCustomerContact(contactDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    /**
     * Updates {@link CustomerContact} in database by its ID.
     *
     * @param id The ID of the contact to update.
     * @param contactDetails The new details for the contact.
     * @return A ResponseEntity containing {@link CustomerContactResponseDTO},
     *         or a 404 status if the contact does not exist```java
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update contact by ID",  description = "Update an existing contact in the database by its ID")
    @GlobalApiResponses(schemaImplementation = CustomerContactResponseDTO.class)
    public ResponseEntity<CustomerContactResponseDTO> updateCustomerContact(@PathVariable long id, @RequestBody CustomerContact contactDetails) {
        var updatedContact = contactService.updateCustomerContact(id, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    /**
     * Deletes {@link CustomerContact} from the database by its ID.
     *
     * @param id the ID of the contact to delete.
     * @return A ResponseEntity with no content if the deletion was successful,
     *         or a 404 status if the contact does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a contact by ID")
    @GlobalApiResponses
    public ResponseEntity<Void> deleteCustomerContact(@PathVariable long id) {
        contactService.deleteCustomerContact(id);
        return ResponseEntity.noContent().build();
    }
}

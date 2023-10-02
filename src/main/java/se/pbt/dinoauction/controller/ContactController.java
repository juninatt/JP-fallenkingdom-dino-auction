package se.pbt.dinoauction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.dinoauction.model.dto.ContactDTO;
import se.pbt.dinoauction.model.entity.contact.Contact;
import se.pbt.dinoauction.service.ContactService;

import java.util.List;

/**
 * ContactController is a RESTful controller class responsible for managing contacts.
 * Provides CRUD operations for Contact entities.
 */
@RestController
@RequestMapping("/contacts")
public class ContactController {

    private final ContactService contactService;

    /**
     * Constructor for dependency injection.
     *
     * @param contactService The service for managing contacts
     */
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    /**
     * Fetches all existing contacts.
     *
     * @return ResponseEntity containing a list of all contacts and HTTP status OK (200).
     */
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    /**
     * Fetches a single contact by its unique ID.
     *
     * @param id Unique identifier for the contact
     * @return ResponseEntity containing the contact or HTTP status NOT FOUND (404) if contact does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable long id) {
        Contact getContact = contactService.getContactById(id);
        return getContact != null ? ResponseEntity.ok(getContact) : ResponseEntity.notFound().build();
    }

    /**
     * Creates a new contact based on the provided data.
     *
     * @param contactData Data transfer object containing the contact's information
     * @return ResponseEntity containing the created contact and HTTP status CREATED (201).
     */
    @PostMapping
    public ResponseEntity<Contact> createContact(@RequestBody ContactDTO contactData) {
        Contact createContact = contactService.createContact(contactData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createContact);
    }

    /**
     * Updates an existing contact identified by its unique ID.
     *
     * @param id Unique identifier for the contact to be updated
     * @param contactData Data transfer object containing the updated contact's information
     * @return ResponseEntity containing the updated contact or HTTP status NOT FOUND (404) if contact does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable long id, @RequestBody ContactDTO contactData) {
        Contact updateContact = contactService.updateContact(id, contactData);
        return updateContact != null ? ResponseEntity.ok(updateContact) : ResponseEntity.notFound().build();
    }

    /**
     * Deletes an existing contact by its unique ID.
     *
     * @param id Unique identifier for the contact to be deleted
     * @return ResponseEntity with HTTP status NO CONTENT (204) on successful deletion,
     * or HTTP status NOT FOUND (404) if the contact does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable long id) {
        boolean deleteContact = contactService.deleteContact(id);
        return deleteContact ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}



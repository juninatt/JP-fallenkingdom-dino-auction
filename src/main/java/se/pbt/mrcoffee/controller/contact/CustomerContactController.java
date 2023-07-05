package se.pbt.mrcoffee.controller.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.service.contact.CustomerContactService;

import java.util.List;

@RestController
@RequestMapping("/customer-contact")
public class CustomerContactController {

    private final CustomerContactService contactService;

    @Autowired
    public CustomerContactController(CustomerContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerContactResponseDTO>> getAllPrivateCustomerContacts() {
        List<CustomerContactResponseDTO> contacts = contactService.getAllCustomerContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerContactResponseDTO> getCustomerContactById(@PathVariable long contactId) {
        var contactDetails = contactService.getCustomerContactById(contactId);
            return ResponseEntity.ok(contactDetails);
    }

    @PostMapping
    public ResponseEntity<CustomerContactResponseDTO> createPrivateCustomerContact(@RequestBody CustomerContact contactDetails) {
        var createdContact = contactService.createCustomerContact(contactDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerContactResponseDTO> updatePrivateCustomerContact(@PathVariable long contactId, @RequestBody CustomerContact contactDetails) {
        var updatedContact = contactService.updateCustomerContact(contactId, contactDetails);
        return ResponseEntity.ok(updatedContact);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrivateCustomerContact(@PathVariable long contactId) {
        contactService.deleteCustomerContact(contactId);
        return ResponseEntity.noContent().build();
    }
}

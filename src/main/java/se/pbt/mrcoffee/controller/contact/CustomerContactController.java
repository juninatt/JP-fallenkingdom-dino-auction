package se.pbt.mrcoffee.controller.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<List<se.pbt.mrcoffee.model.contact.CustomerContact>> getAllPrivateCustomerContacts() {
        List<se.pbt.mrcoffee.model.contact.CustomerContact> contacts = contactService.getAllPrivateCustomerContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<se.pbt.mrcoffee.model.contact.CustomerContact> getPrivateCustomerContactById(@PathVariable long id) {
        se.pbt.mrcoffee.model.contact.CustomerContact contact = contactService.getPrivateCustomerContactById(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<se.pbt.mrcoffee.model.contact.CustomerContact> createPrivateCustomerContact(@RequestBody se.pbt.mrcoffee.model.contact.CustomerContact contact) {
        se.pbt.mrcoffee.model.contact.CustomerContact createdContact = contactService.createPrivateCustomerContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<se.pbt.mrcoffee.model.contact.CustomerContact> updatePrivateCustomerContact(@PathVariable long id, @RequestBody se.pbt.mrcoffee.model.contact.CustomerContact contact) {
        se.pbt.mrcoffee.model.contact.CustomerContact updatedContact = contactService.updatePrivateCustomerContact(id, contact);
        if (updatedContact != null) {
            return ResponseEntity.ok(updatedContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePrivateCustomerContact(@PathVariable long id) {
        boolean deleted = contactService.deletePrivateCustomerContact(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package se.pbt.mrcoffee.controller.privatecustomercontact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.model.contact.PrivateCustomerContact;
import se.pbt.mrcoffee.service.privatecustomercontact.PrivateCustomerContactService;

import java.util.List;

@RestController
@RequestMapping("/private-customer-contact")
public class PrivateCustomerContactController {

    private final PrivateCustomerContactService contactService;

    @Autowired
    public PrivateCustomerContactController(PrivateCustomerContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping
    public ResponseEntity<List<PrivateCustomerContact>> getAllPrivateCustomerContacts() {
        List<PrivateCustomerContact> contacts = contactService.getAllPrivateCustomerContacts();
        return ResponseEntity.ok(contacts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PrivateCustomerContact> getPrivateCustomerContactById(@PathVariable long id) {
        PrivateCustomerContact contact = contactService.getPrivateCustomerContactById(id);
        if (contact != null) {
            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<PrivateCustomerContact> createPrivateCustomerContact(@RequestBody PrivateCustomerContact contact) {
        PrivateCustomerContact createdContact = contactService.createPrivateCustomerContact(contact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdContact);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PrivateCustomerContact> updatePrivateCustomerContact(@PathVariable long id, @RequestBody PrivateCustomerContact contact) {
        PrivateCustomerContact updatedContact = contactService.updatePrivateCustomerContact(id, contact);
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

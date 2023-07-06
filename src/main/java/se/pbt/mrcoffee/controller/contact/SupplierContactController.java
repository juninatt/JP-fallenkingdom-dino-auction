package se.pbt.mrcoffee.controller.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.model.contact.SupplierContact;
import se.pbt.mrcoffee.service.contact.SupplierContactService;

import java.util.List;

@RestController
@RequestMapping("/supplier-contacts")
public class SupplierContactController {

    private final SupplierContactService supplierContactService;

    @Autowired
    public SupplierContactController(SupplierContactService supplierContactService) {
        this.supplierContactService = supplierContactService;
    }

    @GetMapping
    public ResponseEntity<List<SupplierContact>> getAllSupplierContacts() {
        List<SupplierContact> supplierContacts = supplierContactService.getAllSupplierContacts();
        return ResponseEntity.ok(supplierContacts);
    }

    @GetMapping("/{contactId}")
    public ResponseEntity<SupplierContact> getSupplierContactById(@PathVariable long contactId) {
        SupplierContact supplierContact = supplierContactService.getSupplierContactById(contactId);
        if (supplierContact != null) {
            return ResponseEntity.ok(supplierContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<SupplierContact> createSupplierContact(@RequestBody SupplierContact supplierContact) {
        SupplierContact createdSupplierContact = supplierContactService.createSupplierContact(supplierContact);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplierContact);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<SupplierContact> updateSupplierContact(@PathVariable long contactId, @RequestBody SupplierContact supplierContact) {
        SupplierContact updatedSupplierContact = supplierContactService.updateSupplierContact(contactId, supplierContact);
        if (updatedSupplierContact != null) {
            return ResponseEntity.ok(updatedSupplierContact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteSupplierContact(@PathVariable long contactId) {
        boolean deleted = supplierContactService.deleteSupplierContact(contactId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


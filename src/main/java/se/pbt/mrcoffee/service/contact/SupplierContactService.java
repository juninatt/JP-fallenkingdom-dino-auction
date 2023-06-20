package se.pbt.mrcoffee.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.contact.SupplierContact;
import se.pbt.mrcoffee.repository.contact.SupplierContactRepository;

import java.util.List;

@Service
public class SupplierContactService {

    private final SupplierContactRepository supplierContactRepository;

    @Autowired
    public SupplierContactService(SupplierContactRepository supplierContactRepository) {
        this.supplierContactRepository = supplierContactRepository;
    }

    public List<SupplierContact> getAllSupplierContacts() {
        return supplierContactRepository.findAll();
    }

    public SupplierContact getSupplierContactById(long id) {
        return supplierContactRepository.findById(id).orElse(null);
    }

    public SupplierContact createSupplierContact(SupplierContact supplierContact) {
        return supplierContactRepository.save(supplierContact);
    }

    public SupplierContact updateSupplierContact(long id, SupplierContact supplierContact) {
        SupplierContact existingSupplierContact = supplierContactRepository.findById(id).orElse(null);
        if (existingSupplierContact != null) {
            existingSupplierContact.setCompanyName(supplierContact.getCompanyName());
            existingSupplierContact.setIndustry(supplierContact.getIndustry());
            // Improve updating options
            return supplierContactRepository.save(existingSupplierContact);
        }
        return null;
    }

    public boolean deleteSupplierContact(long id) {
        SupplierContact existingSupplierContact = supplierContactRepository.findById(id).orElse(null);
        if (existingSupplierContact != null) {
            supplierContactRepository.delete(existingSupplierContact);
            return true;
        }
        return false;
    }
}


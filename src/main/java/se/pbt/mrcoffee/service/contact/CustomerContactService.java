package se.pbt.mrcoffee.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.repository.contact.CustomerContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerContactService {

    private final CustomerContactRepository contactRepository;
    private final JmsMessageProducer jmsMessageProducer;

    @Autowired
    public CustomerContactService(CustomerContactRepository contactRepository, JmsMessageProducer jmsMessageProducer) {
        this.contactRepository = contactRepository;
        this.jmsMessageProducer = jmsMessageProducer;
    }

    public List<CustomerContact> getAllPrivateCustomerContacts() {
        return contactRepository.findAll();
    }

    public CustomerContact getPrivateCustomerContactById(long id) {
        Optional<CustomerContact> optionalContact = contactRepository.findById(id);
        return optionalContact.orElse(null);
    }

    public CustomerContact createPrivateCustomerContact(CustomerContact contact) {
        CustomerContact createdContact = contactRepository.save(contact);
        jmsMessageProducer.sendMessage("myQueue", "Private customer contact created: " + createdContact.getEmail());
        return createdContact;
    }

    public CustomerContact updatePrivateCustomerContact(long id, CustomerContact contact) {
        Optional<CustomerContact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            CustomerContact existingContact = optionalContact.get();
            existingContact.setEmail(contact.getEmail());
            existingContact.setPhoneNumber(contact.getPhoneNumber());
            // Update other properties as needed
            jmsMessageProducer.sendMessage("myQueue", "Private customer contact updated: " + existingContact.getEmail());
            return contactRepository.save(existingContact);
        } else {
            return null;
        }
    }

    public boolean deletePrivateCustomerContact(long id) {
        Optional<CustomerContact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
            jmsMessageProducer.sendMessage("myQueue", "Private customer contact deleted: " + id);
            return true;
        } else {
            return false;
        }
    }
}

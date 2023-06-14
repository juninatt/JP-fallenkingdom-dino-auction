package se.pbt.mrcoffee.service.privatecustomercontact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.contact.PrivateCustomerContact;
import se.pbt.mrcoffee.repository.privatecustomercontact.PrivateCustomerContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PrivateCustomerContactService {

    private final PrivateCustomerContactRepository contactRepository;
    private final JmsMessageProducer jmsMessageProducer;

    @Autowired
    public PrivateCustomerContactService(PrivateCustomerContactRepository contactRepository, JmsMessageProducer jmsMessageProducer) {
        this.contactRepository = contactRepository;
        this.jmsMessageProducer = jmsMessageProducer;
    }

    public List<PrivateCustomerContact> getAllPrivateCustomerContacts() {
        return contactRepository.findAll();
    }

    public PrivateCustomerContact getPrivateCustomerContactById(long id) {
        Optional<PrivateCustomerContact> optionalContact = contactRepository.findById(id);
        return optionalContact.orElse(null);
    }

    public PrivateCustomerContact createPrivateCustomerContact(PrivateCustomerContact contact) {
        PrivateCustomerContact createdContact = contactRepository.save(contact);
        jmsMessageProducer.sendMessage("myQueue", "Private customer contact created: " + createdContact.getEmail());
        return createdContact;
    }

    public PrivateCustomerContact updatePrivateCustomerContact(long id, PrivateCustomerContact contact) {
        Optional<PrivateCustomerContact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            PrivateCustomerContact existingContact = optionalContact.get();
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
        Optional<PrivateCustomerContact> optionalContact = contactRepository.findById(id);
        if (optionalContact.isPresent()) {
            contactRepository.delete(optionalContact.get());
            jmsMessageProducer.sendMessage("myQueue", "Private customer contact deleted: " + id);
            return true;
        } else {
            return false;
        }
    }
}

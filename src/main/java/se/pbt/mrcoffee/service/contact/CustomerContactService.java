package se.pbt.mrcoffee.service.contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.exception.CustomerContactNotFoundException;
import se.pbt.mrcoffee.mapper.CustomerContactMapper;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.repository.contact.CustomerContactRepository;

import java.util.List;

@Service
public class CustomerContactService {

    private final CustomerContactRepository contactRepository;
    private final JmsMessageProducer jmsMessageProducer;

    @Autowired
    public CustomerContactService(CustomerContactRepository contactRepository, JmsMessageProducer jmsMessageProducer) {
        this.contactRepository = contactRepository;
        this.jmsMessageProducer = jmsMessageProducer;
    }

    public List<CustomerContactResponseDTO> getAllCustomerContacts() {
        return contactRepository.findAll().stream()
                .map(CustomerContactMapper.INSTANCE::toCustomerContactResponseDTO)
                .toList();
    }

    public CustomerContactResponseDTO getCustomerContactById(long contactId) {
        var retrievedContactDetails = contactRepository.findById(contactId)
                .orElseThrow(CustomerContactNotFoundException::new);
        return CustomerContactMapper.INSTANCE.toCustomerContactResponseDTO(retrievedContactDetails);
    }

    public CustomerContactResponseDTO createCustomerContact(CustomerContact contactDetails) {
        var createdContact = contactRepository.save(contactDetails);
        return CustomerContactMapper.INSTANCE.toCustomerContactResponseDTO(createdContact);
    }

    public CustomerContactResponseDTO updateCustomerContact(long contactId, CustomerContact contactDetails) {
        var contactToUpdate = contactRepository.findById(contactId)
                .orElseThrow(CustomerContactNotFoundException::new);

        contactToUpdate.setFirstName(contactDetails.getFirstName());
        contactToUpdate.setLastName(contactDetails.getLastName());
        contactToUpdate.setEmail(contactDetails.getEmail());
        contactToUpdate.setPhoneNumber(contactDetails.getPhoneNumber());
        contactToUpdate.setAdditionalInfo(contactDetails.getAdditionalInfo());

        var updateContact = contactRepository.save(contactToUpdate);
        return CustomerContactMapper.INSTANCE.toCustomerContactResponseDTO(updateContact);
    }

    public void deleteCustomerContact(long id) {
        var cantactToDelete = contactRepository.findById(id)
                .orElseThrow(CustomerContactNotFoundException::new);
        contactRepository.delete(cantactToDelete);
    }
}

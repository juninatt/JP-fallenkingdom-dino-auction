package se.pbt.dinoauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.dinoauction.model.dto.ContactDTO;
import se.pbt.dinoauction.mapper.ContactMapper;
import se.pbt.dinoauction.model.entity.contact.Contact;
import se.pbt.dinoauction.repository.contact.ContactRepository;

import java.util.List;

/**
 * Service class for managing {@link Contact} related operations within the application.
 */
@Service
public class ContactService {

    private final ContactRepository contactRepository;

    /**
     * Constructs a new ContactService instance with the provided {@link ContactRepository}.
     *
     * @param contactRepository the repository responsible for Contact data operations.
     */
    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * Retrieves all {@link Contact} instances.
     *
     * @return a list of all Contact instances.
     */
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    /**
     * Retrieves a {@link Contact} by their unique ID.
     *
     * @param id the unique ID of the Contact.
     * @return the Contact instance, or null if not found.
     */
    public Contact getContactById(long id) {
        return contactRepository.findById(id).orElse(null);
    }

    /**
     * Creates a new {@link Contact} based on the provided {@link ContactDTO}.
     *
     * @param contactData the ContactDTO containing the contact details.
     * @return the created Contact instance-
     */
    public Contact createContact(ContactDTO contactData) {
        return contactRepository.save(ContactMapper.INSTANCE.toContact(contactData));
    }

    /**
     * Updates an existing {@link Contact}.
     *
     * @param id          the unique ID of the Contact to update
     * @param contactData the updated ContactDTO
     * @return the updated Contact instance, or null if not found
     */
    public Contact updateContact(long id, ContactDTO contactData) {
        Contact updateContact = contactRepository.findById(id).orElse(null);
        if (updateContact != null) {
            // Add updating
            return contactRepository.save(updateContact);
        }
        return null;
    }

    /**
     * Deletes a {@link Contact} by their unique ID.
     *
     * @param id the unique ID of the Contact to delete
     * @return true if the operation is successful, false otherwise
     */
    public boolean deleteContact(long id) {
        var deleteContact = contactRepository.findById(id)
                .orElse(null);
        if (deleteContact != null) {
            contactRepository.delete(deleteContact);
            return true;
        }
        return false;
    }
}


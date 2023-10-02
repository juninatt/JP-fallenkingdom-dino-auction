package se.pbt.dinoauction.repository.contact;

import org.springframework.data.repository.CrudRepository;
import se.pbt.dinoauction.model.contact.Contact;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();
}

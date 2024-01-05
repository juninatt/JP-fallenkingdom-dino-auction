package se.pbt.dinoauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.dinoauction.model.entity.contact.Contact;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Long> {

    @Override
    List<Contact> findAll();
}

package se.pbt.mrcoffee.repository.contact;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.contact.CustomerContact;

import java.util.List;

public interface CustomerContactRepository extends CrudRepository<CustomerContact, Long> {

    @Override
    List<CustomerContact> findAll();
}

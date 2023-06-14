package se.pbt.mrcoffee.repository.privatecustomercontact;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.contact.PrivateCustomerContact;

import java.util.List;

public interface PrivateCustomerContactRepository extends CrudRepository<PrivateCustomerContact, Long> {

    @Override
    List<PrivateCustomerContact> findAll();
}

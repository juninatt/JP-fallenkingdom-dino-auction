package se.pbt.mrcoffee.repository.contact;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.contact.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    @Override
    List<Address> findAll();
}

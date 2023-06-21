package se.pbt.mrcoffee.repository.address;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.adress.Address;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    @Override
    List<Address> findAll();
}
package se.pbt.mrcoffee.repository.contact;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.contact.SupplierContact;

import java.util.List;

public interface SupplierContactRepository extends CrudRepository<SupplierContact, Long> {

    @Override
    List<SupplierContact> findAll();
}

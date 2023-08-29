package se.pbt.mrcoffee.repository.order;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.order.Receipt;

import java.util.List;

@Repository
public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

    @Override
    List<Receipt> findAll();
}

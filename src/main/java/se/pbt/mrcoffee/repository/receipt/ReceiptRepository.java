package se.pbt.mrcoffee.repository.receipt;

import org.springframework.data.repository.CrudRepository;
import se.pbt.mrcoffee.model.receipt.Receipt;

import java.util.List;

public interface ReceiptRepository extends CrudRepository<Receipt, Long> {

    @Override
    List<Receipt> findAll();
}

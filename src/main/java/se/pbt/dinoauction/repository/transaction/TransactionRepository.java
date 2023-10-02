package se.pbt.dinoauction.repository.transaction;

import org.springframework.data.jpa.repository.JpaRepository;
import se.pbt.dinoauction.model.entity.transaction.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}

package se.pbt.dinoauction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.pbt.dinoauction.model.entity.transaction.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {


}

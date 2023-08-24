package se.pbt.mrcoffee.repository.payment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.payment.Payment;

/**
 * Repository interface for interacting with {@link Payment} entities in the database.
 * It extends JpaRepository to provide CRUD operations for the Payment entity.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
}

package se.pbt.mrcoffee.repository.purchase;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.purchase.Purchase;

/**
 * Repository interface for managing {@link Purchase} entities.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}

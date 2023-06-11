package se.pbt.mrcoffee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.Coffee;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
}

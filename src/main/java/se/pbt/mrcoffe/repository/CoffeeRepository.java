package se.pbt.mrcoffe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffe.model.Coffee;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, String> {
}

package se.pbt.mrcoffee.repository.coffee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.product.Coffee;

import java.util.List;

@Repository
public interface CoffeeRepository extends CrudRepository<Coffee, Long> {
    @Override
    List<Coffee> findAll();
}
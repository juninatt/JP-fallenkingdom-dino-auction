package se.pbt.mrcoffee.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<MrCoffeeUser, Long> {

    MrCoffeeUser findByName(String name);

    @Override
    List<MrCoffeeUser> findAll();
}

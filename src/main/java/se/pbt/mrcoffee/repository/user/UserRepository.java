package se.pbt.mrcoffee.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<MrCoffeeUser, Long> {

    MrCoffeeUser findByUsername(String username);

    @Override
    List<MrCoffeeUser> findAll();
}

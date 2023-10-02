package se.pbt.dinoauction.repository.auctionitem;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.dinoauction.model.auctionitem.Dinosaur;

import java.util.List;

@Repository
public interface DinosaurRepository extends CrudRepository<Dinosaur, Long> {
    @Override
    List<Dinosaur> findAll();
}

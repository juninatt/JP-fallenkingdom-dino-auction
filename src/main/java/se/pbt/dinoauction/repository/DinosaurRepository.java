package se.pbt.dinoauction.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;

import java.util.List;

@Repository
public interface DinosaurRepository extends CrudRepository<Dinosaur, Long> {
    @Override
    List<Dinosaur> findAll();
}

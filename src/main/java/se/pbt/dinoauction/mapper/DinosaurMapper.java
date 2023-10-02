package se.pbt.dinoauction.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.dinoauction.model.dto.DinosaurDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;

/**
 * Mapper interface for {@link Dinosaur}-related objects.
 */
@Mapper
public interface DinosaurMapper {

    DinosaurMapper INSTANCE = Mappers.getMapper(DinosaurMapper.class);

    /**
     * Maps a {@link DinosaurDTO} to a {@link Dinosaur} entity.
     *
     * @param dinosaurDTO The DinosaurDTO to convert.
     * @return The corresponding Dinosaur entity.
     */
    Dinosaur toDinosaur(DinosaurDTO dinosaurDTO);

    /**
     * Maps a {@link Dinosaur} entity to a {@link DinosaurDTO}.
     *
     * @param dinosaurEntity The Dinosaur entity to convert.
     * @return The corresponding DinosaurDTO.
     */
    DinosaurDTO toDinosaurDTO(Dinosaur dinosaurEntity);
}

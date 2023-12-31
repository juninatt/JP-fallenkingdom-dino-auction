package se.pbt.dinoauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.dinoauction.exception.DinosaurConversionException;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.jms.MessageProducer;
import se.pbt.dinoauction.mapper.DinosaurMapper;
import se.pbt.dinoauction.model.dto.DinoCardDataDTO;
import se.pbt.dinoauction.model.dto.DinoCardDataListDTO;
import se.pbt.dinoauction.model.dto.DinosaurDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.repository.DinosaurRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Dinosaur-related operations.
 */
@Service
public class AuctionService {

    private final DinosaurRepository dinosaurRepository;
    private final MessageProducer messageProducer;

    /**
     * Constructor for DinosaurService.
     *
     * @param dinosaurRepository  The {@link DinosaurRepository} for Dinosaur entities.
     * @param messageProducer The {@link MessageProducer} for JMS messaging.
     */
    @Autowired
    public AuctionService(DinosaurRepository dinosaurRepository, MessageProducer messageProducer) {
        this.dinosaurRepository = dinosaurRepository;
        this.messageProducer = messageProducer;
    }

    /**
     * Fetch all {@link Dinosaur} entities in database.
     *
     * @return List of all Dinosaurs.
     * @throws DinosaurNotFoundException if no Dinosaurs are found.
     */
    public List<Dinosaur> getAllDinosaurs() {
        var allDinosaurs = dinosaurRepository.findAll();
        if (allDinosaurs.isEmpty())
            throw new DinosaurNotFoundException();
        else return allDinosaurs;
    }

    /**
     * Fetch a specific {@link Dinosaur} entity by ID.
     *
     * @param id The ID of the Dinosaur.
     * @return The Dinosaur or null if not found.
     */
    public Dinosaur getDinosaurById(long id) {
        Optional<Dinosaur> optionalDinosaur = dinosaurRepository.findById(id);
        return optionalDinosaur.orElse(null);
    }

    /**
     * Create a new {@link Dinosaur} entity.
     *
     * @param dinosaurData The DTO containing data for the new Dinosaur.
     * @return The newly created Dinosaur.
     */
    public Dinosaur createDinosaur(DinosaurDTO dinosaurData) {
        return dinosaurRepository.save(DinosaurMapper.INSTANCE.toDinosaur(dinosaurData));
    }


    /**
     * Update an existing {@link Dinosaur} entity by ID.
     *
     * @param id            The ID of the Dinosaur to update.
     * @param dinosaurData The DTO containing the new Dinosaur data.
     * @return The updated Dinosaur or null if not found.
     */
    public Dinosaur updateDinosaur(long id, DinosaurDTO dinosaurData) {
        var optionalDinosaur = dinosaurRepository.findById(id);
        if (optionalDinosaur.isPresent()) {
            var existingDinosaur = optionalDinosaur.get();
            existingDinosaur.setName(dinosaurData.name());
            // Update other properties as needed
            messageProducer.sendMessage("myQueue", "Dinosaur updated: " + existingDinosaur.getName());
            return dinosaurRepository.save(existingDinosaur);
        } else {
            return null;
        }
    }

    /**
     * Delete a specific {@link Dinosaur} entity by ID.
     *
     * @param id The ID of the Dinosaur to delete.
     * @return True if the Dinosaur was deleted, false otherwise.
     */
    public boolean deleteDinosaur(long id) {
        Optional<Dinosaur> optionalDinosaur = dinosaurRepository.findById(id);
        if (optionalDinosaur.isPresent()) {
            dinosaurRepository.delete(optionalDinosaur.get());
            return true;
        } else {
            return false;
        }
    }

    public DinoCardDataListDTO getDinoCardDataList() {
        List<DinoCardDataDTO> dinoCards;
        List<Dinosaur> existingDinosaurs = dinosaurRepository.findAll();
        if (existingDinosaurs.isEmpty())
            throw new DinosaurNotFoundException("No dinosaurs found in database");
        else {
            try {
                dinoCards = existingDinosaurs.stream()
                        .map(DinosaurMapper.INSTANCE::toDinoCardDataDTO)
                        .toList();
            } catch (RuntimeException exception) {
                throw new DinosaurConversionException(exception.getMessage(), exception.getCause());
            }
            System.out.println(dinoCards);
            return new DinoCardDataListDTO(dinoCards);
        }
    }
}

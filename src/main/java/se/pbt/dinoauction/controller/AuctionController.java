package se.pbt.dinoauction.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.dinoauction.model.dto.DinoCardDataDTO;
import se.pbt.dinoauction.model.dto.DinoCardDataListDTO;
import se.pbt.dinoauction.model.dto.DinosaurDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.service.AuctionService;

import java.util.List;

/**
 * REST Controller for managing {@link Dinosaur} entities.
 */
@RestController
@RequestMapping("/dinosaurs")
public class AuctionController {

    private final AuctionService auctionService;

    /**
     * Constructor for {@link AuctionController} class..
     */
    public AuctionController(AuctionService auctionService) {
        this.auctionService = auctionService;
    }


    /**
     * Retrieves a list of {@link DinoCardDataDTO} objects wrapped in a {@link DinoCardDataListDTO}.
     *
     * @return ResponseEntity<DinoCardDataListDTO> containing a list of {@link Dinosaur} cards.
     */

    @GetMapping("/dino-cards")
    public ResponseEntity<DinoCardDataListDTO> getDinoCardDataList() {
        DinoCardDataListDTO cardData = auctionService.getDinoCardDataList();
        return ResponseEntity.ok(cardData);
    }

    /**
     * Get all {@link Dinosaur} entities.
     *
     * @return A ResponseEntity with the list of all Dinosaur entities.
     */
    @GetMapping
    public ResponseEntity<List<Dinosaur>> getAllDinosaurs() {
        List<Dinosaur> dinosaurs = auctionService.getAllDinosaurs();
        return ResponseEntity.ok(dinosaurs);
    }

    /**
     * Get a {@link Dinosaur} entity by its ID.
     *
     * @param id The ID of the Dinosaur.
     * @return A ResponseEntity containing the Dinosaur entity or a 404 status code if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Dinosaur> getDinosaurById(@PathVariable long id) {
        var fetchedDinosaur = auctionService.getDinosaurById(id);
        if (fetchedDinosaur != null) {
            return ResponseEntity.ok(fetchedDinosaur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new {@link Dinosaur} entity.
     *
     * @param dinosaurData The details for the new Dinosaur.
     * @return A ResponseEntity containing the newly created Dinosaur entity.
     */
    @PostMapping
    public ResponseEntity<Dinosaur> createDinosaur(@RequestBody DinosaurDTO dinosaurData) {
        var createdDinosaur = auctionService.createDinosaur(dinosaurData);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDinosaur);
    }

    /**
     * Update an existing {@link Dinosaur} entity.
     *
     * @param id    The ID of the Dinosaur to update.
     * @param dinosaurData The new data for the Dinosaur.
     * @return A ResponseEntity containing the updated Dinosaur entity or a 404 status code if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Dinosaur> updateDinosaur(@PathVariable long id, @RequestBody DinosaurDTO dinosaurData) {
        var updatedDinosaur = auctionService.updateDinosaur(id, dinosaurData);
        if (updatedDinosaur != null) {
            return ResponseEntity.ok(updatedDinosaur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a {@link Dinosaur} entity by its ID.
     *
     * @param id The ID of the Dinosaur to delete.
     * @return A ResponseEntity with a 204 status code if deletion was successful, or a 404 status code if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDinosaur(@PathVariable long id) {
        boolean deleted = auctionService.deleteDinosaur(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

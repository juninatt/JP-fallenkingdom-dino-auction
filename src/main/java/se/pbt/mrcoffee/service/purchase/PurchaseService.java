package se.pbt.mrcoffee.service.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.request.PurchaseDTO;
import se.pbt.mrcoffee.dto.response.PurchaseResponseDTO;
import se.pbt.mrcoffee.exception.PurchaseNotFoundException;
import se.pbt.mrcoffee.mapper.PurchaseMapper;
import se.pbt.mrcoffee.model.purchase.Purchase;
import se.pbt.mrcoffee.repository.purchase.PurchaseRepository;

import java.util.List;

/**
 * Service class that manages CRUD operations for {@link Purchase} entities.
 */
@Service
public class PurchaseService {

    private final PurchaseRepository purchaseRepository;

    @Autowired
    public PurchaseService(PurchaseRepository purchaseRepository) {
        this.purchaseRepository = purchaseRepository;
    }

    /**
     * Fetches all {@link Purchase} objects from the database and returns a list with their DTO representations.
     *
     * @return A list of {@link PurchaseResponseDTO}s representing all purchases in the database.
     */
    public List<PurchaseResponseDTO> getAllPurchases() {
        return purchaseRepository.findAll().stream()
                .map(PurchaseMapper.INSTANCE::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a {@link Purchase} identified by the given ID and returns its DTO representation.
     *
     * @param id ID of the Purchase to be retrieved.
     * @return {@link PurchaseResponseDTO} that represents the Purchase with the given ID.
     * @throws PurchaseNotFoundException if no Purchase with the given ID is found.
     */
    public PurchaseResponseDTO getPurchaseById(long id) {
        return purchaseRepository.findById(id)
                .map(PurchaseMapper.INSTANCE::toResponseDTO)
                .orElseThrow(() -> new PurchaseNotFoundException("No purchase with ID: " + id + " could be found."));
    }


    /**
     * Updates a {@link Purchase} in the database by ID with the provided purchase details.
     *
     * @param purchaseId The ID of the purchase to be updated.
     * @param purchaseDetails A {@link PurchaseDTO} object with the updated information.
     * @return The {@link PurchaseResponseDTO} that represents the updated Purchase.
     * @throws PurchaseNotFoundException If no Purchase with the given ID exists in the database.
     */
    public PurchaseResponseDTO updatePurchase(long purchaseId, PurchaseDTO purchaseDetails) {
        var purchaseToUpdate = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException("No purchase with ID: " + purchaseId + " could be found."));

        // Implement logic for linking the member objects, or restructure

        var updatedPurchase = purchaseRepository.save(purchaseToUpdate);
        return PurchaseMapper.INSTANCE.toResponseDTO(updatedPurchase);
    }

    /**
     * Deletes the {@link Purchase} with the specified ID.
     *
     * @param purchaseId The ID of the purchase to be deleted.
     * @throws PurchaseNotFoundException If no purchase with the specified ID is found.
     */
    public void deletePurchase(long purchaseId) {
        var purchaseToDelete = purchaseRepository.findById(purchaseId)
                .orElseThrow(() -> new PurchaseNotFoundException("No purchase with ID: " + purchaseId + " could be found."));
        purchaseRepository.delete(purchaseToDelete);
    }
}

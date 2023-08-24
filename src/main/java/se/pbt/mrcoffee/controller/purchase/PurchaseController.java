package se.pbt.mrcoffee.controller.purchase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.dto.request.PurchaseDTO;
import se.pbt.mrcoffee.dto.response.PurchaseResponseDTO;
import se.pbt.mrcoffee.model.purchase.Purchase;
import se.pbt.mrcoffee.service.purchase.PurchaseService;

import java.util.List;

/**
 * Controller class for managing purchase-related HTTP requests.
 * <p>
 * This class provides endpoints for various CRUD operations related to {@link Purchase} objects, including
 * retrieving all purchases, getting a purchase by ID, updating a purchase, and deleting a purchase.
 * It makes use of the {@link PurchaseService} to handle the business logic related to these operations.
 * <p>
 * Clients interact with this controller through the "/purchases" URL.
 */
@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    private final PurchaseService purchaseService;

    /**
     * Constructs a PurchaseController with the given {@link PurchaseService}.
     *
     * @param purchaseService The service to handle the purchase-related logic
     */
    @Autowired
    public PurchaseController(PurchaseService purchaseService) {
        this.purchaseService = purchaseService;
    }

    /**
     * Retrieves All {@link Purchase} objects.
     *
     * @return A response entity containing a list of all purchases
     */
    @GetMapping
    public ResponseEntity<List<PurchaseResponseDTO>> getAllPurchases() {
        return ResponseEntity.ok(purchaseService.getAllPurchases());
    }

    /**
     * Retrieves a specific {@link Purchase} by its ID.
     *
     * @param id The ID of the purchase
     * @return A response entity containing the purchase details
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseResponseDTO> getPurchaseById(@PathVariable Long id) {
        return ResponseEntity.ok(purchaseService.getPurchaseById(id));
    }

    /**
     * Updates the details of a specific {@link Purchase}.
     *
     * @param id             the ID of the purchase to update
     * @param purchaseDetails the new details of the purchase
     * @return a response entity containing the updated purchase details
     */
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseResponseDTO> updatePurchase(@PathVariable Long id, @RequestBody PurchaseDTO purchaseDetails) {
        return ResponseEntity.ok(purchaseService.updatePurchase(id, purchaseDetails));
    }

    /**
     * Deletes a specific {@link Purchase} by its ID.
     *
     * @param id the ID of the purchase to delete
     * @return a response entity indicating successful deletion
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchase(@PathVariable Long id) {
        purchaseService.deletePurchase(id);
        return ResponseEntity.noContent().build();
    }
}

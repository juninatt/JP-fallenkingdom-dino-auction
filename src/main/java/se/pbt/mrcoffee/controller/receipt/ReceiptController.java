package se.pbt.mrcoffee.controller.receipt;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.annotation.GlobalApiResponses;
import se.pbt.mrcoffee.model.receipt.Receipt;
import se.pbt.mrcoffee.service.receipt.ReceiptService;

import java.util.List;


/**
 * Controller class for handling RESTful API endpoints related to receipts.
 */
@RestController
@RequestMapping("/receipt")
@Tag(name = "Receipts", description = "Endpoints for managing receipts")
public class ReceiptController {

    private final ReceiptService receiptService;

    @Autowired
    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    /**
     * Retrieves all {@link Receipt} objects.
     *
     * @return ResponseEntity containing the list of all receipts.
     */
    @GetMapping
    @Operation(summary = "Retrieve all receipts", description = "Retrieve a list of all receipts from the database")
    @GlobalApiResponses(schemaImplementation = Receipt.class)
    public ResponseEntity<List<Receipt>> getAllReceipts() {
        var receipts = receiptService.getAllReceipts();
        return ResponseEntity.ok(receipts);
    }

    /**
     * Retrieves a {@link Receipt} by its ID.
     *
     * @param id The ID of the receipt to retrieve.
     * @return ResponseEntity containing the retrieved receipt if found, or a not found response.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve a receipt by ID", description = "Retrieve a specific receipt from the database based on the receipts ID")
    @GlobalApiResponses(schemaImplementation = Receipt.class)
    public ResponseEntity<Receipt> getReceiptById( @PathVariable @Parameter(description = "Receipt ID", example = "1") long id) {
        var receipt = receiptService.getReceiptById(id);
        if (receipt != null) {
            return ResponseEntity.ok(receipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates a new {@link Receipt}.
     *
     * @param receipt The receipt to create.
     * @return ResponseEntity containing the created receipt.
     */
    @PostMapping
    @Operation(summary = "Create a new receipt", description = "Create a new receipt and persist it to the database")
    @GlobalApiResponses(schemaImplementation = Receipt.class)
    public ResponseEntity<Receipt> createReceipt(@RequestBody @Parameter(description = "Receipt object") Receipt receipt) {
        var createdReceipt = receiptService.createReceipt(receipt);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdReceipt);
    }

    /**
     * Updates an existing {@link Receipt}.
     *
     * @param id      The ID of the receipt to update.
     * @param receipt The updated receipt object.
     * @return ResponseEntity containing the updated receipt if found, or a not found response.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update an existing receipt",  description = "Update an existing receipt in the database")
    @GlobalApiResponses(schemaImplementation = Receipt.class)
    public ResponseEntity<Receipt> updateReceipt(@PathVariable @Parameter(description = "Receipt ID", example = "1") long id,
                                                 @RequestBody @Parameter(description = "Updated receipt object") Receipt receipt) {
        var updatedReceipt = receiptService.updateReceipt(id, receipt);
        if (updatedReceipt != null) {
            return ResponseEntity.ok(updatedReceipt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes a {@link Receipt} by its ID.
     *
     * @param id The ID of the receipt to delete.
     * @return ResponseEntity indicating the success or failure of the deletion.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a receipt by ID")
    @GlobalApiResponses
    public ResponseEntity<Void> deleteReceipt(@PathVariable @Parameter(description = "Receipt ID", example = "1") long id) {
        boolean deleted = receiptService.deleteReceipt(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

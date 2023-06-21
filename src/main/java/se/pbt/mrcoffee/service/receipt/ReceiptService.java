package se.pbt.mrcoffee.service.receipt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.receipt.Receipt;
import se.pbt.mrcoffee.repository.receipt.ReceiptRepository;

import java.util.List;

/**
 * Service class that handles the business logic for the {@link Receipt} entity.
 */
@Service
public class ReceiptService {

    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    /**
     * Retrieves all {@link Receipt} objects from the repository.
     *
     * @return List of all receipts.
     */
    public List<Receipt> getAllReceipts() {
        return receiptRepository.findAll();
    }

    /**
     * Retrieves a {@link Receipt} by its ID.
     *
     * @param id The ID of the receipt.
     * @return The receipt with the specified ID, or null if not found.
     */
    public Receipt getReceiptById(long id) {
        return receiptRepository.findById(id)
                .orElse(null);
    }

    /**
     * Creates a new {@link Receipt}.
     *
     * @param receipt The receipt to be created.
     * @return The created receipt.
     */
    public Receipt createReceipt(Receipt receipt) {
        return receiptRepository.save(receipt);
    }

    /**
     * Updates an existing {@link Receipt} with new data.
     *
     * @param id            The ID of the receipt to be updated.
     * @param updatedReceipt The updated receipt object.
     * @return The updated receipt, or null if the receipt does not exist.
     */
    public Receipt updateReceipt(long id, Receipt updatedReceipt) {
        var existingReceipt = receiptRepository.findById(id);
        if (existingReceipt.isPresent()) {
            return receiptRepository.save(updatedReceipt);
        }
        return null;
    }

    /**
     * Deletes a {@link Receipt} by its ID.
     *
     * @param id The ID of the receipt to be deleted.
     * @return true if the receipt is deleted successfully, false otherwise.
     */
    public boolean deleteReceipt(long id) {
        var receipt = receiptRepository.findById(id);
        if (receipt.isPresent()) {
            receiptRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

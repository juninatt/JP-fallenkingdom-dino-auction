package se.pbt.dinoauction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.dinoauction.model.dto.TransactionPreviewDTO;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.model.entity.transaction.Transaction;
import se.pbt.dinoauction.repository.auctionitem.DinosaurRepository;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Service class that manages {@link Transaction} related operations.
 */
@Service
public class TransactionService {

    private final DinosaurRepository dinosaurRepository;

    @Autowired
    public TransactionService(DinosaurRepository dinosaurRepository) {
        this.dinosaurRepository = dinosaurRepository;
    }


    public TransactionPreviewDTO getOrderPreview(HashMap<Long, Integer> orderedProducts) {
        HashMap<String, Integer> previewCoffee = new HashMap<>();
        BigDecimal totalPrice;

        totalPrice = orderedProducts.entrySet().stream()
                .map(entry -> {
                    Long id = entry.getKey();
                    Integer quantity = entry.getValue();

                    Dinosaur dinosaur = dinosaurRepository.findById(id)
                            .orElseThrow(() -> new DinosaurNotFoundException("Dinosaur with ID: " + id + " not found"));

                    previewCoffee.put(dinosaur.getName(), quantity);

                    return dinosaur.getPriceInDollar().multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new TransactionPreviewDTO("Order Preview", previewCoffee, totalPrice);
    }
}

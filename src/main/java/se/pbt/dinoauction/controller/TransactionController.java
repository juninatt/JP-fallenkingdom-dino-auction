package se.pbt.dinoauction.controller;

import org.springframework.web.bind.annotation.*;
import se.pbt.dinoauction.dto.TransactionPreviewDTO;
import se.pbt.dinoauction.service.TransactionService;

import java.util.HashMap;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    /**
     * Constructs a PurchaseController with the given {@link TransactionService}.
     *
     * @param transactionService The service to handle the purchase-related logic
     */
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/summary")
    public TransactionPreviewDTO getOrderPreview(@RequestBody HashMap<Long, Integer> wantedProducts) {
        return transactionService.getOrderPreview(wantedProducts);
    }

    @PostMapping("/confirmed")
    public void validateOrder(TransactionPreviewDTO confirmedOrder) {

    }
}

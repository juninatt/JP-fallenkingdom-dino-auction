package se.pbt.dinoauction.integrationtest.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import se.pbt.dinoauction.model.dto.TransactionPreviewDTO;
import se.pbt.dinoauction.model.entity.auctionitem.Dinosaur;
import se.pbt.dinoauction.repository.DinosaurRepository;
import se.pbt.dinoauction.service.TransactionService;
import se.pbt.dinoauction.testobject.TestObjectFactory;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("TransactionService Integration Tests")
public class TransactionServiceTest {

    @Autowired
    private DinosaurRepository dinosaurRepository;

    @Autowired
    private TransactionService transactionService;

    @Nested
    class GetTransactionPreviewTest {

        @Test
        @DisplayName("Calculates price correctly, multiple items")
        void successfully_previewsOrder_multipleItems() {
            Dinosaur dinosaur1 = TestObjectFactory.dinosaur();
            Dinosaur dinosaur2 = TestObjectFactory.dinosaur();

            dinosaur1 = dinosaurRepository.save(dinosaur1);
            dinosaur2 = dinosaurRepository.save(dinosaur2);


            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(dinosaur1.getId(), 2);
            orderedProducts.put(dinosaur2.getId(), 3);

            TransactionPreviewDTO result = transactionService.getOrderPreview(orderedProducts);

            BigDecimal expectedTotalPrice = dinosaur1.getPriceInDollar().multiply(BigDecimal.valueOf(2.00))
                    .add(dinosaur2.getPriceInDollar().multiply(BigDecimal.valueOf(3)));

            assertEquals(expectedTotalPrice.stripTrailingZeros(), result.totalSum().stripTrailingZeros());        }

        @Test
        @DisplayName("Calculates price correctly, single item")
        void successfully_previewsOrder_singleItem() {
            Dinosaur dinosaur1 = TestObjectFactory.dinosaur();
            dinosaur1 = dinosaurRepository.save(dinosaur1);

            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(dinosaur1.getId(), 1);

            TransactionPreviewDTO result = transactionService.getOrderPreview(orderedProducts);

            assertEquals(dinosaur1.getPriceInDollar(), result.totalSum().stripTrailingZeros());
        }
    }
}

package se.pbt.mrcoffee.service.receipt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import se.pbt.mrcoffee.model.receipt.Receipt;
import se.pbt.mrcoffee.repository.receipt.ReceiptRepository;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DisplayName("ReceiptService:")
class ReceiptServiceTest {

    @Autowired
    private ReceiptService receiptService;

    @Autowired
    private ReceiptRepository receiptRepository;


    @BeforeEach
    void reset() {
        receiptRepository.deleteAll();
    }

    @Nested
    @DisplayName("getAllReceipts()")
    class GetAllReceiptsTest {

        @Test
        @DisplayName("Retrieves all receipts in database")
        void getAllReceipts_testGetAllReceipts() {
            // Save test receipts to the repository
            var receipt1 = TestObjectFactory.createReceipt();
            var receipt2 = TestObjectFactory.createReceipt();

            receiptRepository.save(receipt1);
            receiptRepository.save(receipt2);

            // Call the method being tested and assign the return value
            var receipts = receiptService.getAllReceipts();

            // Assert that the expected number of receipts is returned
            assertEquals(2, receipts.size());
        }

        @Test
        @DisplayName("Retrieves an empty list when no receipts exist in the database")
        void getAllReceipts_testGetAllReceiptsEmpty() {
            // Call the method being tested and assign the return value
            var receipts = receiptService.getAllReceipts();

            // Assert that an empty list is returned
            assertEquals(0, receipts.size());
        }

        @Test
        @DisplayName("Retrieves receipts with correct data from database")
        void getAllReceipts_testGetAllReceiptsData() {
            // Save test receipts to the repository
            var receipt1 = TestObjectFactory.createReceipt();
            var receipt2 = TestObjectFactory.createReceipt();

            receipt1.setTotalAmount(BigDecimal.valueOf(10.51));
            receipt2.setTotalAmount(BigDecimal.valueOf(15.75));

            receiptRepository.save(receipt1);
            receiptRepository.save(receipt2);

            // Call the method being tested and assign the return value
            var receipts = receiptService.getAllReceipts();

            // Assert that the retrieved receipts have the correct data
            assertEquals(2, receipts.size());
            assertEquals(BigDecimal.valueOf(10.51), receipts.get(0).getTotalAmount());
            assertEquals(BigDecimal.valueOf(15.75), receipts.get(1).getTotalAmount());
        }
    }


    @Nested
    @DisplayName("createReceipt()")
    class CreateReceiptTest {

        @Test
        @DisplayName("Persists receipt to database")
        void createReceipts_testCreateReceiptDatabase() {
            // Create a new receipt
            var receipt = TestObjectFactory.createReceipt();

            // Create the receipt using the service
            var createdReceipt = receiptService.createReceipt(receipt);

            // Retrieve the receipt from the database
            var savedReceipt = receiptRepository.findById(createdReceipt.getReceiptId())
                    .orElse(null);

            // Assert that the saved receipt exists in the database
            assertNotNull(savedReceipt);
            assertEquals(createdReceipt.getReceiptId(), savedReceipt.getReceiptId());
            assertEquals(receipt.getTotalAmount(), savedReceipt.getTotalAmount());
        }

        @Test
        @DisplayName("Returns the created receipt")
        void createReceipts_returnsCreatedReceipt() {
            // Create a new receipt
            var receipt = TestObjectFactory.createReceipt();

            // Create the receipt using the service
            var createdReceipt = receiptService.createReceipt(receipt);

            // Assert that the returned receipt is the same as the created receipt
            assertEquals(receipt, createdReceipt);
        }
    }


    @Nested
    @DisplayName("updateReceipt()")
    class UpdateReceiptTest {

        @Test
        @DisplayName("Updates an existing receipt when found in database")
        void updateReceipts_updatesReceipt_whenFoundInDatabase() {
            // Create a new receipt
            Receipt receipt = new Receipt();
            receiptRepository.save(receipt);

            // Update the receipt with new data
            receipt.setTotalAmount(BigDecimal.valueOf(30.75));
            receipt.setDiscountCode("DISCOUNT123");

            // Update the receipt using the service
            var updatedReceipt = receiptService.updateReceipt(receipt.getReceiptId(), receipt);

            // Assert that the receipt is updated with the new data
            assertNotNull(updatedReceipt);
            assertEquals(BigDecimal.valueOf(30.75), updatedReceipt.getTotalAmount());
            assertEquals("DISCOUNT123", updatedReceipt.getDiscountCode());
        }

        @Test
        @DisplayName("Returns null when updating a non-existing receipt")
        void updateReceipts_returnsNull_whenStepIsNotFound() {
            // Create a new receipt without saving it to the repository
            Receipt receipt = new Receipt();
            receipt.setTotalAmount(BigDecimal.valueOf(25.00));
            receipt.setDiscountCode("DISCOUNT456");

            // Update the non-existing receipt using the service
            Receipt updatedReceipt = receiptService.updateReceipt(123L, receipt);

            // Assert that the updated receipt is null
            assertNull(updatedReceipt);
        }
    }


    @Nested
    @DisplayName("deleteReceipt()")
    class DeleteReceiptTest {

        @Test
        @DisplayName("Deletes an existing receipt")
        void deleteReceipt_deletesReceipt_whenIdExistsInDatabase() {
            // Create a new receipt
            var receipt = TestObjectFactory.createReceipt();
            receiptRepository.save(receipt);

            // Delete the receipt using the service
            boolean deleted = receiptService.deleteReceipt(receipt.getReceiptId());

            // Assert that the receipt is deleted successfully
            assertTrue(deleted);
        }

        @Test
        @DisplayName("Returns false when deleting a non-existing receipt")
        void deleteReceipt_returnsFalse_whenNoIdExistsInDatabase() {
            // Delete a non-existing receipt using an ID that doesn't exist
            boolean deleted = receiptService.deleteReceipt(123L);

            // Assert that the receipt is not deleted (non-existing)
            assertFalse(deleted);
        }

        @Test
        @DisplayName("Returns false when deleting a receipt with negative ID value")
        void deleteReceipt_returnsFalse_whenIdHasNegativeValue() {
            // Delete a null receipt using a negative ID value
            boolean deleted = receiptService.deleteReceipt(-1L);

            // Assert that the receipt is not deleted (null)
            assertFalse(deleted);
        }
    }
}

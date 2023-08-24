package se.pbt.mrcoffee.unittest.mapper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import se.pbt.mrcoffee.dto.response.PurchaseResponseDTO;
import se.pbt.mrcoffee.mapper.PurchaseMapper;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("PurchaseMapper tests:")
class PurchaseMapperTest {

    private final PurchaseMapper purchaseMapper = PurchaseMapper.INSTANCE;


    @Test
    @DisplayName("Maps Purchase to PurchaseResponseDTO")
    void toResponseDTO_ShouldMapPurchaseToResponseDTO() {
        // Create a test Purchase object
        var purchase = TestObjectFactory.createPurchase();

        // Call the method to be tested
        PurchaseResponseDTO result = purchaseMapper.toResponseDTO(purchase);

        // Assert the result has expected values
        assertNotNull(result);
        assertEquals(purchase.getId(), result.id());
        assertEquals(purchase.getReceipt().getId(), result.receiptId());
        assertEquals(purchase.getPayment().getId(), result.paymentId());
        assertEquals(purchase.getCustomer().getId(), result.customerId());
    }

    @Test
    @DisplayName("Maps PurchaseDTO to PurchaseResponseDTO")
    void toResponseDTO_ShouldMapPurchaseDTOToResponseDTO() {
        // Create a test PurchaseDTO object
        var purchaseDTO = TestObjectFactory.createPurchaseDTO();

        // Call the method to be tested
        PurchaseResponseDTO result = purchaseMapper.toResponseDTO(purchaseDTO);

        // Assert the result has expected values
        assertNotNull(result);
        // Assert other properties are mapped correctly
        assertEquals(purchaseDTO.receiptId(), result.receiptId());
        assertEquals(purchaseDTO.paymentId(), result.paymentId());
        assertEquals(purchaseDTO.customerId(), result.customerId());
    }
}

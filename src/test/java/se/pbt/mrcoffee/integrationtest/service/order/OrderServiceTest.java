package se.pbt.mrcoffee.integrationtest.service.order;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.dto.response.OrderPreviewDTO;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.service.order.OrderService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.math.BigDecimal;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private OrderService orderService;

    @Nested
    @DisplayName("getOrderPreview method tests:")
    class GetOrderPreviewTest {

        @Test
        @DisplayName("Test Order Preview With Multiple Items")
        public void testGetOrderPreview() {
            Coffee coffee1 = TestObjectFactory.createCoffee();
            Coffee coffee2 = TestObjectFactory.createCoffee();

            coffee1 = coffeeRepository.save(coffee1);
            coffee2 = coffeeRepository.save(coffee2);


            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(coffee1.getId(), 2);
            orderedProducts.put(coffee2.getId(), 3);

            OrderPreviewDTO result = orderService.getOrderPreview(orderedProducts);

            BigDecimal expectedTotalPrice = coffee1.getPrice().multiply(BigDecimal.valueOf(2))
                    .add(coffee2.getPrice().multiply(BigDecimal.valueOf(3)));

            assertEquals(expectedTotalPrice, result.totalSum());
        }

        @Test
        @DisplayName("Test Order Preview With Single Item")
        public void testGetOrderPreviewSingleItem() {
            Coffee coffee1 = TestObjectFactory.createCoffee();
            coffee1 = coffeeRepository.save(coffee1);

            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(coffee1.getId(), 1);

            OrderPreviewDTO result = orderService.getOrderPreview(orderedProducts);

            assertEquals(coffee1.getPrice(), result.totalSum());
        }

        @Test
        @DisplayName("Test Order Preview With Zero Quantity")
        public void testGetOrderPreviewZeroQuantity() {
            Coffee coffee1 = TestObjectFactory.createCoffee();
            coffee1 = coffeeRepository.save(coffee1);

            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(coffee1.getId(), 0);

            OrderPreviewDTO result = orderService.getOrderPreview(orderedProducts);

            assertEquals("0.00", result.totalSum().toPlainString());
        }

        @Test
        @DisplayName("Test Order Preview With Nonexistent Coffee")
        public void testGetOrderPreviewNonexistentCoffee() {
            HashMap<Long, Integer> orderedProducts = new HashMap<>();
            orderedProducts.put(999L, 1);

            assertThrows(RuntimeException.class, () -> orderService.getOrderPreview(orderedProducts));
        }
    }
}

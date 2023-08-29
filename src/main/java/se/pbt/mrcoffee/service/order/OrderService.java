package se.pbt.mrcoffee.service.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.response.OrderPreviewDTO;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.model.order.Order;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.order.PaymentRepository;
import se.pbt.mrcoffee.repository.order.ReceiptRepository;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * Service class that manages CRUD operations for {@link Order} entities.
 */
@Service
public class OrderService {

    private final ReceiptRepository receiptRepository;
    private final PaymentRepository paymentRepository;
    private final CoffeeRepository coffeeRepository;

    @Autowired
    public OrderService(ReceiptRepository receiptRepository,
                        PaymentRepository paymentRepository,
                        CoffeeRepository coffeeRepository) {
        this.receiptRepository = receiptRepository;
        this.paymentRepository = paymentRepository;
        this.coffeeRepository = coffeeRepository;
    }


    public OrderPreviewDTO getOrderPreview(HashMap<Long, Integer> orderedProducts) {
        HashMap<Coffee, Integer> previewCoffee = new HashMap<>();
        BigDecimal totalPrice;

        totalPrice = orderedProducts.entrySet().stream()
                .map(entry -> {
                    Long id = entry.getKey();
                    Integer quantity = entry.getValue();

                    Coffee coffee = coffeeRepository.findById(id)
                            .orElseThrow(() -> new CoffeeNotFoundException("Coffee with ID: " + id + " not found"));

                    previewCoffee.put(coffee, quantity);

                    return coffee.getPrice().multiply(BigDecimal.valueOf(quantity));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new OrderPreviewDTO("Order Preview", previewCoffee, totalPrice);
    }
}

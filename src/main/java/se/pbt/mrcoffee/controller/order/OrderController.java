package se.pbt.mrcoffee.controller.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.dto.response.OrderPreviewDTO;
import se.pbt.mrcoffee.service.order.OrderService;

import java.util.HashMap;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Constructs a PurchaseController with the given {@link OrderService}.
     *
     * @param orderService The service to handle the purchase-related logic
     */
    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/summary")
    public OrderPreviewDTO getOrderPreview(@RequestBody HashMap<Long, Integer> wantedProducts) {
        return orderService.getOrderPreview(wantedProducts);
    }
}

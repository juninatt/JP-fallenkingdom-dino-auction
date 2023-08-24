package se.pbt.mrcoffee.controller.payment;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.annotation.GlobalApiResponses;
import se.pbt.mrcoffee.dto.request.PaymentDTO;
import se.pbt.mrcoffee.dto.response.PaymentResponseDTO;
import se.pbt.mrcoffee.model.payment.Payment;
import se.pbt.mrcoffee.service.payment.PaymentService;

import java.util.List;

/**
 * The PaymentController is a REST controller that handles HTTP requests for the {@link Payment} resource.
 * It provides endpoints to create, retrieve, update, and delete payments.
 */
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Retrieves a list of all stored {@link Payment} objects in the database.
     *
     * @return A ResponseEntity containing a list of {@link PaymentResponseDTO}.
     *         The HTTP status code is 200 (OK) if the operation is successful.
     */
    @GetMapping
    @Operation(summary = "Retrieve all payments", description = "Retrieve a list of all payments from the database")
    @GlobalApiResponses(schemaImplementation = Payment.class)
    public ResponseEntity<List<PaymentResponseDTO>> getAllPayments() {
        List<PaymentResponseDTO> payments = paymentService.getAllPayments();
        return ResponseEntity.ok(payments);
    }

    /**
     * Retrieves {@link Payment} by its ID and returns a DTO representation of the payment.
     *
     * @param id The ID of the payment to retrieve.
     * @return A ResponseEntity containing a {@link PaymentResponseDTO} that represents the requested payment,
     *         or a 404 status if the payment does not exist.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve payment by ID", description = "Retrieve a specific payment from the database based on the payment ID")
    @GlobalApiResponses(schemaImplementation = PaymentResponseDTO.class)
    public ResponseEntity<PaymentResponseDTO> getPaymentById(@PathVariable long id) {
        var retrievedPayment = paymentService.getPaymentById(id);
        return ResponseEntity.ok(retrievedPayment);
    }

    /**
     * Creates new {@link Payment} object and stores it in the database.
     *
     * @param paymentDetails The new payment details.
     * @return A ResponseEntity containing a {@link PaymentResponseDTO} with the stored information.
     */
    @PostMapping
    @Operation(summary = "Create a new payment", description = "Create a new payment and persist it to the database")
    @GlobalApiResponses(schemaImplementation = PaymentResponseDTO.class)
    public ResponseEntity<PaymentResponseDTO> createPayment(@RequestBody PaymentDTO paymentDetails) {
        var savedPayment = paymentService.createPayment(paymentDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPayment);
    }

    /**
     * Updates {@link Payment} in the database by its ID.
     *
     * @param id The ID of the payment to update.
     * @param paymentDetails The new details for the payment.
     * @return A ResponseEntity containing {@link PaymentResponseDTO},
     *         or a 404 status if the payment does not exist.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update payment by ID", description = "Update an existing payment in the database by its ID")
    @GlobalApiResponses(schemaImplementation = PaymentResponseDTO.class)
    public ResponseEntity<PaymentResponseDTO> updatePayment(@PathVariable long id, @RequestBody PaymentDTO paymentDetails) {
        var paymentToUpdate = paymentService.updatePayment(id, paymentDetails);
        return ResponseEntity.ok(paymentToUpdate);
    }

    /**
     * Deletes {@link Payment} from the database by its ID.
     *
     * @param id the ID of the payment to delete.
     * @return A ResponseEntity with no content if the deletion was successful,
     *         or a 404 status if the payment does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a payment by ID")
    @GlobalApiResponses
    public ResponseEntity<Void> deletePayment(@PathVariable long id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }
}

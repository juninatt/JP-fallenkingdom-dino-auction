package se.pbt.mrcoffee.service.payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.request.PaymentDTO;
import se.pbt.mrcoffee.dto.response.PaymentResponseDTO;
import se.pbt.mrcoffee.exception.PaymentNotFoundException;
import se.pbt.mrcoffee.mapper.PaymentMapper;
import se.pbt.mrcoffee.model.payment.Payment;
import se.pbt.mrcoffee.repository.payment.PaymentRepository;

import java.util.List;

/**
 * Service class that handles operations related to {@link Payment} entities.
 */
@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    /**
     * Constructor that initializes PaymentRepository.
     *
     * @param paymentRepository The repository used to interact with payments in the database.
     */
    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    /**
     * Fetches all {@link Payment} objects from the database and returns a list with their DTO representations.
     *
     * @return A list of {@link PaymentResponseDTO}s representing all payments in the database.
     */
    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(PaymentMapper.INSTANCE::toResponseDTO)
                .toList();
    }

    /**
     * Retrieves a {@link Payment} identified by the given ID and returns its DTO representation.
     *
     * @param id ID of the Payment to be retrieved.
     * @return {@link PaymentResponseDTO} that represents the Payment with the given ID.
     * @throws PaymentNotFoundException if no Payment with the given ID is found.
     */
    public PaymentResponseDTO getPaymentById(long id) {
        return paymentRepository.findById(id)
                .map(PaymentMapper.INSTANCE::toResponseDTO)
                .orElseThrow(() -> new PaymentNotFoundException("No payment with ID: " + id + " could be found."));
    }

    /**
     * Creates a new {@link Payment} and stores it in the database.
     *
     * @param paymentDetails A {@link PaymentDTO} that holds the values of the new payment.
     * @return A {@link PaymentResponseDTO} that represents the newly created payment.
     */
    public PaymentResponseDTO createPayment(PaymentDTO paymentDetails) {
        var savedPayment = paymentRepository.save(PaymentMapper.INSTANCE.toPayment(paymentDetails));
        return PaymentMapper.INSTANCE.toResponseDTO(savedPayment);
    }

    /**
     * Updates a {@link Payment} in the database by ID with the provided payment details.
     *
     * @param paymentId      The ID of the payment to be updated.
     * @param paymentDetails A Payment object with the updated information.
     * @return The {@link PaymentResponseDTO} that represents the updated Payment.
     * @throws PaymentNotFoundException If no Payment with the given ID exists in the database.
     */
    public PaymentResponseDTO updatePayment(long paymentId, PaymentDTO paymentDetails) {
        var paymentToUpdate = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("No payment with ID: " + paymentId + " could be found."));

        paymentToUpdate.setStatus(paymentDetails.status());
        paymentToUpdate.setPaymentMethod(paymentDetails.paymentMethod());
        var updatedPayment = paymentRepository.save(paymentToUpdate);
        return PaymentMapper.INSTANCE.toResponseDTO(updatedPayment);
    }

    /**
     * Deletes the {@link Payment} with the specified ID.
     *
     * @param paymentId The ID of the payment to be deleted.
     * @throws PaymentNotFoundException If no payment with the specified ID is found.
     */
    public void deletePayment(long paymentId) {
        var paymentToDelete = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException("No payment with ID: " + paymentId + " could be found."));
        paymentRepository.delete(paymentToDelete);
    }
}

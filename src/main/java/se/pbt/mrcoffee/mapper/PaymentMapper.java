package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.PaymentDTO;
import se.pbt.mrcoffee.dto.response.PaymentResponseDTO;
import se.pbt.mrcoffee.model.payment.Payment;

/**
 * Mapper interface for converting between {@link Payment} entity and its corresponding DTO representations.
 */
@Mapper
public interface PaymentMapper {

    PaymentMapper INSTANCE = Mappers.getMapper(PaymentMapper.class);

    /**
     * Converts a {@link Payment} entity to its response DTO representation.
     *
     * @param payment Payment entity to be converted.
     * @return {@link PaymentResponseDTO} representing the payment.
     */
    PaymentResponseDTO toResponseDTO(Payment payment);

    /**
     * Converts a request DTO to its corresponding {@link Payment} entity.
     *
     * @param paymentDTO Payment DTO to be converted.
     * @return {@link Payment} entity representing the DTO.
     */
    Payment toPayment(PaymentDTO paymentDTO);
}

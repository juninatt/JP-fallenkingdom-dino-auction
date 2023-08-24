package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.PurchaseDTO;
import se.pbt.mrcoffee.dto.response.PurchaseResponseDTO;
import se.pbt.mrcoffee.model.purchase.Purchase;

/**
 * Provides mapping methods for converting between {@link Purchase}, {@link PurchaseDTO}, and {@link PurchaseResponseDTO} objects.
 * <p>
 * This interface leverages MapStruct to generate implementation code for the mappings, simplifying
 * the transformation logic between the various representations of a purchase within the system.
 */
@Mapper
public interface PurchaseMapper {

    /**
     * Singleton instance of the mapper, facilitating easy access to the mapping methods.
     */
    PurchaseMapper INSTANCE = Mappers.getMapper(PurchaseMapper.class);

    /**
     * Converts a {@link PurchaseDTO} object into a {@link Purchase} entity.
     *
     * @param purchaseDTO The {@link PurchaseDTO} representing the purchase details.
     * @return The {@link Purchase} entity built from the given DTO.
     */
    Purchase toPurchase(PurchaseDTO purchaseDTO);

    /**
     * Converts a {@link Purchase} entity into a PurchaseResponseDTO object.
     *
     * @param purchase The {@link Purchase} entity representing a purchase transaction.
     * @return The response DTO containing details of the purchase.
     */
    PurchaseResponseDTO toResponseDTO(Purchase purchase);

    /**
     * Converts a {@link PurchaseDTO} object into a {@link PurchaseResponseDTO} object.
     * <p>
     * This method allows for direct conversion between the request and response DTOs, bypassing
     * the entity representation when necessary.
     *
     * @param purchaseDTO The {@link PurchaseDTO} representing the purchase details.
     * @return The {@link PurchaseResponseDTO} containing details of the purchase.
     */
    PurchaseResponseDTO toResponseDTO(PurchaseDTO purchaseDTO);
}

package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.model.contact.Address;

/**
 * Mapper interface for Address-related objects.
 * Provides methods for mapping between {@link Address} entity and different Address Data Transfer Objects (DTOs).
 */
@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    /**
     * Maps an {@link Address} entity to an {@link AddressDTO}.
     * @param address the Address entity
     * @return the corresponding {@link AddressDTO}
     */
    AddressDTO toAddressDTO(Address address);

    /**
     * Maps an {@link AddressDTO} to an {@link Address} entity.
     * @param addressDTO the {@link AddressDTO}
     * @return the corresponding {@link Address} entity
     */
    Address toAddress(AddressDTO addressDTO);

    /**
     * Maps an {@link Address} entity to an {@link AddressResponseDTO}.
     * @param address the {@link Address} entity
     * @return the corresponding {@link AddressResponseDTO}
     */
    AddressResponseDTO toResponseDTO(Address address);
}

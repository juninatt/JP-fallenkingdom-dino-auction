package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.model.adress.Address;

@Mapper
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDTO toAddressDTO(Address address);

    Address toAddress(AddressDTO addressDTO);

    AddressResponseDTO toResponseDTO(Address adress);
}

package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.CustomerContactDTO;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.model.contact.CustomerContact;

@Mapper
public interface CustomerContactMapper {

    CustomerContactMapper INSTANCE = Mappers.getMapper(CustomerContactMapper.class);


    CustomerContactDTO toCustomerContactDTO(CustomerContact customerContact);

    CustomerContactResponseDTO toCustomerContactResponseDTO(CustomerContact customerContact);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    CustomerContact toCustomerContact(CustomerContactDTO customerContactDTO);
}


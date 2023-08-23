package se.pbt.mrcoffee.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se.pbt.mrcoffee.dto.request.CustomerContactDTO;
import se.pbt.mrcoffee.dto.request.SupplierContactDTO;
import se.pbt.mrcoffee.dto.response.CustomerContactResponseDTO;
import se.pbt.mrcoffee.dto.response.SupplierContactResponseDTO;
import se.pbt.mrcoffee.model.contact.CustomerContact;
import se.pbt.mrcoffee.model.contact.SupplierContact;

/**
 * Mapper interface for Contact-related objects.
 * Provides methods for mapping between different contact types such as {@link CustomerContact} and {@link SupplierContact}.
 */
@Mapper
public interface ContactMapper {

    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);


    /**
     * Maps a {@link CustomerContact} entity to a {@link CustomerContactDTO}.
     * @param customerContact the {@link CustomerContact} entity
     * @return the corresponding {@link CustomerContactDTO}
     */
    CustomerContactDTO toCustomerContactDTO(CustomerContact customerContact);

    /**
     * Maps a {@link CustomerContact} entity to a {@link CustomerContactResponseDTO}.
     * @param customerContact the {@link CustomerContact} entity
     * @return the corresponding {@link CustomerContactResponseDTO}
     */
    CustomerContactResponseDTO toCustomerContactResponseDTO(CustomerContact customerContact);

    /**
     * Maps a {@link CustomerContactDTO} to a {@link CustomerContact} entity, ignoring the user and addresses fields.
     * @param customerContactDTO the {@link CustomerContactDTO}
     * @return the corresponding {@link CustomerContact} entity
     */
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    CustomerContact toCustomerContact(CustomerContactDTO customerContactDTO);


    /**
     * Maps a {@link SupplierContact} entity to a {@link SupplierContactDTO}.
     * @param supplierContact the {@link SupplierContact} entity
     * @return the corresponding {@link SupplierContactDTO}
     */
    SupplierContactDTO toSupplierContactDTO(SupplierContact supplierContact);

    /**
     * Maps a {@link SupplierContact} entity to a {@link SupplierContactResponseDTO}.
     * @param supplierContact the {@link SupplierContact} entity
     * @return the corresponding {@link SupplierContactResponseDTO}
     */
    SupplierContactResponseDTO toSupplierContactResponseDTO(SupplierContact supplierContact);

    /**
     * Maps a {@link SupplierContactDTO} to a {@link SupplierContact} entity, ignoring the user and addresses fields.
     * @param supplierContactDTO the {@link SupplierContactDTO}
     * @return the corresponding {@link SupplierContact} entity
     */
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "addresses", ignore = true)
    SupplierContact toSupplierContact(SupplierContactDTO supplierContactDTO);
}


package se.pbt.mrcoffee.unittest.mapper;

import org.junit.jupiter.api.Test;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.mapper.AddressMapper;
import se.pbt.mrcoffee.model.adress.Address;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressMapperTest {

    @Test
    public void testAddressToAddressDTO() {
         var address = new Address();
        address.setStreet("Main St");
        address.setStreetNumber(123);
        address.setApartmentNumber(4);
        address.setCity("Sample City");
        address.setPostalCode("12345");
        address.setCountry("Sample Country");

        var addressDTO = AddressMapper.INSTANCE.toAddressDTO(address);

        assertEquals(address.getStreet(), addressDTO.street());
        assertEquals(address.getStreetNumber(), addressDTO.streetNumber());
        assertEquals(address.getApartmentNumber(), addressDTO.apartmentNumber());
        assertEquals(address.getCity(), addressDTO.city());
        assertEquals(address.getPostalCode(), addressDTO.postalCode());
        assertEquals(address.getCountry(), addressDTO.country());
    }

    @Test
    public void testAddressDTOToAddress() {
        var addressDTO = new AddressDTO("Main St", 123, 4, "Sample City", "12345", "Sample Country");

        var address = AddressMapper.INSTANCE.toAddress(addressDTO);

        assertEquals(addressDTO.street(), address.getStreet());
        assertEquals(addressDTO.streetNumber(), address.getStreetNumber());
        assertEquals(addressDTO.apartmentNumber(), address.getApartmentNumber());
        assertEquals(addressDTO.city(), address.getCity());
        assertEquals(addressDTO.postalCode(), address.getPostalCode());
        assertEquals(addressDTO.country(), address.getCountry());
    }
}

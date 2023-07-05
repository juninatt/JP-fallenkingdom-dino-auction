package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.exception.AddressNotFoundException;
import se.pbt.mrcoffee.mapper.AddressMapper;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.repository.address.AddressRepository;
import se.pbt.mrcoffee.service.address.AddressService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@DisplayName("AddressService:")
class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;


    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
    }


    @Nested
    @DisplayName("createAddress()")
    class CreateAddressTest {

        @Test
        @DisplayName("Creates a new address and stores it in database")
        void createAddress_createsNewAddressInDatabase() {
            // Create a DTO holding the values for the address
            var addressDetails = TestObjectFactory.createAddressDTO();

            // Call the method to be tested
            addressService.createAddress(addressDetails);

            // Assert a new address was persisted to database
            assertEquals(1, addressRepository.findAll().size());
        }

        @Test
        @DisplayName("Returns response object with same values as input object")
        void createAddress_returnsResponseDTO_withCorrectFieldValues() {
            // Create a DTO holding the values for the address
            var addressDetails = TestObjectFactory.createAddressDTO();

            // Call the method to be tested and assign the returned value
            var result = addressService.createAddress(addressDetails);

            // Assert the fields of the returned object is same as test object
            assertNotNull(result);
            assertNotNull(result.addressId());
            assertEquals(addressDetails.street(), result.street());
            assertEquals(addressDetails.streetNumber(), result.streetNumber());
            assertEquals(addressDetails.city(), result.city());
            assertEquals(addressDetails.postalCode(), result.postalCode());
            assertEquals(addressDetails.country(), result.country());
        }
    }


    @Nested
    @DisplayName("getAllAddresses()")
    class GetAllAddressesTest {

        @Test
        @DisplayName("Returns representation of all addresses")
        void getAllAddresses_ShouldReturnAllAddresses() {
            // Create test objects and add to database
            var addresses = new ArrayList<Address>();
            addresses.add(TestObjectFactory.createAddress());
            addresses.add(TestObjectFactory.createAddress());
            addressRepository.saveAll(addresses);

            // Call tested method and assign return value
            var result = addressService.getAllAddresses();

            // Create a list of expected AddressResponseDTO objects
            List<AddressResponseDTO> expected = addresses.stream()
                    .map(AddressMapper.INSTANCE::toResponseDTO)
                    .toList();


            // Assert that returned list contains the expected values
            assertEquals(addresses.size(), result.size());
            assertTrue(result.containsAll(expected));
        }
    }


    @Nested
    @DisplayName("getAddressById()")
    class GetAddressByIdTest {

        @Test
        @DisplayName("Returns an address representation with the same ID as address it represents")
        void getAddressById_ShouldReturnAddressWithGivenId() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            addressRepository.save(address);

            var storedAddressId = address.getAddressId();

            // Call the method to be tested with the id of the stored object
            var response = addressService.getAddressById(storedAddressId);

            // Assert the returned object has same ID as the test object
            assertNotNull(response);
            assertEquals(address.getAddressId(), response.addressId());
        }
    }


    @Nested
    @DisplayName("updateAddress()")
    class UpdateAddressTest {

        @Test
        @DisplayName("Updates an existing address if matching ID is found in database")
        void updateAddress_ShouldUpdateExistingAddress() {
            // Create an object to be updated and save it to the database
            var address = TestObjectFactory.createAddress();
            address = addressRepository.save(address);

            var id = address.getAddressId();

            // Create a new address details to update the existing address with
            var newAddressDetails = new AddressDTO("Updated Street", 456, 1, "Updated City", "67890", "Updated Country");

            // Call the tested method with test objects ID and address details and assign returned value
            var result = addressService.updateAddress(id, newAddressDetails);

            // Assert the returned value is not null and contains the updated data
            assertNotNull(result);
            assertEquals(id, result.addressId());
            assertEquals(newAddressDetails.street(), result.street());
            assertEquals(newAddressDetails.streetNumber(), result.streetNumber());
            assertEquals(newAddressDetails.city(), result.city());
            assertEquals(newAddressDetails.postalCode(), result.postalCode());
            assertEquals(newAddressDetails.country(), result.country());
        }

        @Test
        @DisplayName("Throws AddressNotFoundException when no address exists with the given ID")
        void updateAddress_throwsAddressNotFoundException_whenNoAddressWithGivenIdExists() {
            // Create a test object
            var address = TestObjectFactory.createAddressDTO();

            // Assert exception is thrown when no matching ID is found
            assertThrows(AddressNotFoundException.class, () -> {
                // Act
                addressService.updateAddress(666L, address);
            });
        }
    }

    @Nested
    @DisplayName("deleteAddress()")
    class DeleteAddressTest {


        @Test
        @DisplayName("Successfully deletes object with corresponding ID from database")
        void deleteAddress_deletesExistingAddress_whenIdIsFoundInDatabase() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            address = addressRepository.save(address);

            var id = address.getAddressId();

            // Call the method to test
            addressService.deleteAddress(id);

            // Assert the object is gone from database
            assertFalse(addressRepository.existsById(id));
        }

        @Test
        @DisplayName("Throws AddressNotFoundException when no address with given ID exists")
        void deleteAddress_ThrowsAddressNotFoundException_WhenIdNotFoundInDatabase() {
            // Call the tested method with invalid ID
            assertThrows(AddressNotFoundException.class, () -> addressService.deleteAddress(666L));
        }

        @Test
        @DisplayName("Checks if delete method is invoked once")
        void deleteAddress_RepositoryDeleteMethodInvokedOnce_WhenValidId() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            address = addressRepository.save(address);

            // Call the tested method
            addressService.deleteAddress(address.getAddressId());

            // Verify that delete method is called once
            assertFalse(addressRepository.existsById(address.getAddressId()));        }
    }
}

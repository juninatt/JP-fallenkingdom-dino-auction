package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.repository.address.AddressRepository;
import se.pbt.mrcoffee.service.address.AddressService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("AddressService:")
class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private JmsMessageProducer jmsMessageProducer;


    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
    }


    @Nested
    @DisplayName("createAddress()")
    class CreateAddressTest {

        @Test
        @DisplayName("Creates a new address")
        void createAddress_ShouldCreateNewAddress() {
            // Create a test object
            var address = TestObjectFactory.createAddress();

            // Call the method to be tested and assign the returned value
            var result = addressService.createAddress(address);

            // Assert the fields of the returned object is same as test object
            assertNotNull(result);
            assertNotNull(result.getAddressId());
            assertEquals(address.getStreet(), result.getStreet());
            assertEquals(address.getStreetNumber(), result.getStreetNumber());
            assertEquals(address.getCity(), result.getCity());
            assertEquals(address.getPostalCode(), result.getPostalCode());
            assertEquals(address.getCountry(), result.getCountry());
        }
    }


    @Nested
    @DisplayName("getAllAddresses() ->")
    class GetAllAddressesTest {

        @Test
        @DisplayName("Returns all addresses")
        void getAllAddresses_ShouldReturnAllAddresses() {
            // Create test objects and add to database
            var addresses = new ArrayList<Address>();
            addresses.add(TestObjectFactory.createAddress());
            addresses.add(TestObjectFactory.createAddress());
            addressRepository.saveAll(addresses);

            // Call tested method and assign return value
            var result = addressService.getAllAddresses();

            // Assert that returned list contains the expected values
            assertEquals(addresses.size(), result.size());
            assertTrue(result.containsAll(addresses));
        }

    }


    @Nested
    @DisplayName("getAddressById()")
    class GetAddressByIdTest {

        @Test
        @DisplayName("Returns the address with the given ID")
        void getAddressById_ShouldReturnAddressWithGivenId() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            addressRepository.save(address);

            var id = address.getAddressId();

            // Call the method to be tested with the id of the stored object
            var result = addressService.getAddressById(id);

            // Assert the returned object is the test object
            assertNotNull(result);
            assertEquals(address, result);
        }
    }


    @Nested
    @DisplayName("updateAddress()")
    class UpdateAddressTest {

        @Test
        @DisplayName("Updates an existing address")
        void updateAddress_ShouldUpdateExistingAddress() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            address = addressRepository.save(address);

            var id = address.getAddressId();

            // Create a new address containing the updated data
            var updatedAddress = new Address("Updated Street", 456, 1, "Updated City", "67890", "Updated Country");

            // Call the tested method with test objects ID and address with updated data and assign returned value
            var result = addressService.updateAddress(id, updatedAddress);

            // Assert the returned value is not null and contains the updated data
            assertNotNull(result);
            assertEquals(id, result.getAddressId());
            assertEquals(updatedAddress.getStreet(), result.getStreet());
            assertEquals(updatedAddress.getStreetNumber(), result.getStreetNumber());
            assertEquals(updatedAddress.getCity(), result.getCity());
            assertEquals(updatedAddress.getPostalCode(), result.getPostalCode());
            assertEquals(updatedAddress.getCountry(), result.getCountry());
        }

        @Test
        @DisplayName("Returns null when no address exists with the given ID")
        void updateAddress_ShouldReturnNullWhenNoAddressWithGivenIdExists() {
            // Call the tested method with invalid ID and assign return value
            var result = addressService.updateAddress(666L, TestObjectFactory.createAddress());

            // Assert returned value is null
            assertNull(result);
        }
    }

    @Nested
    @DisplayName("deleteAddress()")
    class DeleteAddressTest {


        @Test
        @DisplayName("Deletes an existing address")
        void deleteAddress_ShouldDeleteExistingAddress() {
            // Create a test object and save it to the database
            var address = TestObjectFactory.createAddress();
            address = addressRepository.save(address);

            var id = address.getAddressId();

            // Call the tested method and assign the returned value
            var result = addressService.deleteAddress(id);

            // Assert the returned value is true and that the objects is gone from database
            assertTrue(result);
            assertFalse(addressRepository.existsById(id));
        }

        @Test
        @DisplayName("Returns false when no address exists with the given ID")
        void deleteAddress_ShouldReturnFalseWhenNoAddressWithGivenIdExists() {
            // Call tested method and assign return value
            var result = addressService.deleteAddress(666L);

            // Assert the returned value is null
            assertFalse(result);
        }
    }
}

package se.pbt.mrcoffee.controller.address;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.annotation.GlobalApiResponses;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.mapper.AddressMapper;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.service.address.AddressService;

import java.util.List;

/**
 * The AddressController is a REST controller that handles HTTP requests for the Address resource.
 * It provides endpoints to create, retrieve, update, and delete addresses.
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    /**
     * Retrieves all {@link Address} objects stored in the database.
     *
     * @return A ResponseEntity containing a list of all addresses.
     */
    @GetMapping
    @Operation(summary = "Retrieve all addresses", description = "Retrieve a list of all addresses from the database")
    @GlobalApiResponses(schemaImplementation = Address.class)
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    /**
     * Retrieves {@link Address} by its ID and returns a DTO representation of the address.
     *
     * @param id The ID of the address to retrieve.
     * @return A ResponseEntity containing an {@link AddressResponseDTO} that represents the requested address,
     *         or a 404 status if the address does not exist.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Retrieve address by ID", description = "Retrieve a specific address from the database based on the address ID")
    @GlobalApiResponses(schemaImplementation = AddressResponseDTO.class)
    public ResponseEntity<AddressResponseDTO> getAddressById(@PathVariable long id) {
        var requestedAddress = addressService.getAddressById(id);
        if (requestedAddress != null) {
            return ResponseEntity.ok(AddressMapper.INSTANCE.addressToAddressResponseDTO(requestedAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Creates new {@link Address} object and stores it in the database.
     *
     * @param addressDetails The new address details.
     * @return A ResponseEntity containing a {@link AddressResponseDTO} with the stored information.
     */
    @PostMapping
    @Operation(summary = "Create a new address", description = "Create a new address and persist it to the database")
    @GlobalApiResponses(schemaImplementation = AddressResponseDTO.class)
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressDTO addressDetails) {
        var newAddress = AddressMapper.INSTANCE.addressDTOToAddress(addressDetails);
        addressService.createAddress(newAddress);
        var createdAddressResponse = AddressMapper.INSTANCE.addressToAddressResponseDTO(newAddress);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddressResponse);
    }

    /**
     * Updates {@link Address} in database by its ID.
     *
     * @param id The ID of the address to update.
     * @param addressDetails The new details for the address.
     * @return A ResponseEntity containing {@link AddressResponseDTO}, or a 404 status if the address does not exist.
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update address by ID",  description = "Update an existing address in the database by its ID")
    @GlobalApiResponses(schemaImplementation = AddressResponseDTO.class)
    public ResponseEntity<AddressResponseDTO> updateAddress(@PathVariable long id, @RequestBody AddressDTO addressDetails) {
        var updatedAddress = addressService.updateAddress(id, AddressMapper.INSTANCE.addressDTOToAddress(addressDetails));
        if (updatedAddress != null) {
            return ResponseEntity.ok(AddressMapper.INSTANCE.addressToAddressResponseDTO(updatedAddress));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Deletes {@link Address} from the database by its ID.
     *
     * @param id the ID of the address to delete.
     * @return A ResponseEntity with no content if the deletion was successful, or a 404 status if the address does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a address by ID")
    @GlobalApiResponses
    public ResponseEntity<Void> deleteAddress(@PathVariable long id) {
        boolean deleted = addressService.deleteAddress(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

package se.pbt.mrcoffee.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.request.AddressDTO;
import se.pbt.mrcoffee.dto.response.AddressResponseDTO;
import se.pbt.mrcoffee.exception.AddressNotFoundException;
import se.pbt.mrcoffee.mapper.AddressMapper;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.repository.address.AddressRepository;

import java.util.List;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    /**
     * Fetches all {@link Address} objects from the database and returns a list with their DTO representations.
     *
     * @return A list of {@link AddressResponseDTO}s representing all addresses in the database.
     */
    public List<AddressResponseDTO> getAllAddresses() {
        return addressRepository.findAll().stream()
                .map(AddressMapper.INSTANCE::addressToAddressResponseDTO)
                .toList();
    }

    /**
     * Retrieves an {@link Address} identified by the given ID and returns its DTO representation.
     *
     * @param id ID of the Address to be retrieved.
     * @return {@link AddressResponseDTO} that represents the Address with the given ID.
     * @throws AddressNotFoundException if no Address with the given ID is found.
     */
    public AddressResponseDTO getAddressById(long id) {
        return addressRepository.findById(id)
                .map(AddressMapper.INSTANCE::addressToAddressResponseDTO)
                .orElseThrow(() -> new AddressNotFoundException("No address with ID: " + id + " could be found."));
    }

    /**
     * Creates a new {@link Address} and stored it in the database.
     *
     * @param addressDetails A {@link AddressDTO} that holds the values of the new address.
     * @return A {@link AddressResponseDTO} that represents the newly created step.
     */
    public AddressResponseDTO createAddress(AddressDTO addressDetails) {
        var savedAddress = addressRepository.save(AddressMapper.INSTANCE.addressDTOToAddress(addressDetails));
        return AddressMapper.INSTANCE.addressToAddressResponseDTO(savedAddress);
    }

    /**
     * Updates an {@link Address} in the database by ID with the provided address details.
     *
     * @param addressId The ID of the address to be updated.
     * @param addressDetails An Address object with the updated information.
     * @return The {@link AddressResponseDTO} that represents the updated Address.
     * @throws AddressNotFoundException If no Address with the given ID exists in the database.
     */
    public AddressResponseDTO updateAddress(long addressId, AddressDTO addressDetails) {
        var addressToUpdate = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("No address with ID: " + addressId + " could be found."));

            addressToUpdate.setStreet(addressDetails.street());
            addressToUpdate.setStreetNumber(addressDetails.streetNumber());
            addressToUpdate.setCity(addressDetails.city());
            addressToUpdate.setPostalCode(addressDetails.postalCode());
            addressToUpdate.setCountry(addressDetails.country());
            var updatedAddress = addressRepository.save(addressToUpdate);
            return AddressMapper.INSTANCE.addressToAddressResponseDTO(updatedAddress);
    }

    /**
     * Deletes the {@link Address} with the specified ID.
     *
     * @param addressId The ID of the address to be deleted.
     * @throws AddressNotFoundException If no address with the specified ID is found.
     */
    public void deleteAddress(long addressId) {
        var addressToDelete = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException("No address with ID: " + addressId + " could be found."));
            addressRepository.delete(addressToDelete);
    }
}

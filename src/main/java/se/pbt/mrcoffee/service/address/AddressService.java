package se.pbt.mrcoffee.service.address;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.model.adress.Address;
import se.pbt.mrcoffee.repository.address.AddressRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Address getAddressById(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        return optionalAddress.orElse(null);
    }

    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    public Address updateAddress(long id, Address address) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreet(address.getStreet());
            existingAddress.setStreetNumber(address.getStreetNumber());
            existingAddress.setCity(address.getCity());
            existingAddress.setPostalCode(address.getPostalCode());
            existingAddress.setCountry(address.getCountry());
            return addressRepository.save(existingAddress);
        } else {
            return null;
        }
    }

    public boolean deleteAddress(long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            addressRepository.delete(optionalAddress.get());
            return true;
        } else {
            return false;
        }
    }
}

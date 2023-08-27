package se.pbt.mrcoffee.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.dto.request.CoffeeDTO;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.mapper.ProductMapper;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class for handling Coffee-related operations.
 */
@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final JmsMessageProducer jmsMessageProducer;

    /**
     * Constructor for CoffeeService.
     *
     * @param coffeeRepository  The {@link CoffeeRepository} for Coffee entities.
     * @param jmsMessageProducer The {@link JmsMessageProducer} for JMS messaging.
     */
    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository, JmsMessageProducer jmsMessageProducer) {
        this.coffeeRepository = coffeeRepository;
        this.jmsMessageProducer = jmsMessageProducer;
    }

    /**
     * Fetch all {@link Coffee} entities in database.
     *
     * @return List of coffee entities.
     * @throws CoffeeNotFoundException if no Coffee entities are found.
     */
    public List<Coffee> getAllCoffees() {
        var allCoffees = coffeeRepository.findAll();
        if (allCoffees.isEmpty())
            throw new CoffeeNotFoundException();
        else return allCoffees;
    }

    /**
     * Fetch a specific {@link Coffee} entity by ID.
     *
     * @param id The ID of the Coffee entity.
     * @return The Coffee entity or null if not found.
     */
    public Coffee getCoffeeById(long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        return optionalCoffee.orElse(null);
    }

    /**
     * Create a new {@link Coffee} entity.
     *
     * @param coffeeDetails The DTO containing details for the new Coffee entity.
     * @return The newly created Coffee entity.
     */
    public Coffee createCoffee(CoffeeDTO coffeeDetails) {
        return coffeeRepository.save(ProductMapper.INSTANCE.toCoffee(coffeeDetails));
    }


    /**
     * Update an existing {@link Coffee} entity by ID.
     *
     * @param id            The ID of the Coffee entity to update.
     * @param coffeeDetails The DTO containing the updated details.
     * @return The updated Coffee entity or null if not found.
     */
    public Coffee updateCoffee(long id, CoffeeDTO coffeeDetails) {
        var optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            var existingCoffee = optionalCoffee.get();
            existingCoffee.setName(coffeeDetails.name());
            // Update other properties as needed
            jmsMessageProducer.sendMessage("myQueue", "Coffee updated: " + existingCoffee.getName());
            return coffeeRepository.save(existingCoffee);
        } else {
            return null;
        }
    }

    /**
     * Delete a specific {@link Coffee} entity by ID.
     *
     * @param id The ID of the Coffee entity to delete.
     * @return True if the entity was deleted, false otherwise.
     */
    public boolean deleteCoffee(long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            coffeeRepository.delete(optionalCoffee.get());
            return true;
        } else {
            return false;
        }
    }
}

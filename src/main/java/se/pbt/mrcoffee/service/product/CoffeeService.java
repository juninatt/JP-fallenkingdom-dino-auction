package se.pbt.mrcoffee.service.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;
    private final JmsMessageProducer jmsMessageProducer;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository, JmsMessageProducer jmsMessageProducer) {
        this.coffeeRepository = coffeeRepository;
        this.jmsMessageProducer = jmsMessageProducer;
    }

    public List<Coffee> getAllCoffees() {
        var allCoffees = coffeeRepository.findAll();
        if (allCoffees.isEmpty())
            throw new CoffeeNotFoundException();
        else return allCoffees;
    }

    public Coffee getCoffeeById(long id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        return optionalCoffee.orElse(null);
    }

    public Coffee createCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(long id, Coffee coffee) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            Coffee existingCoffee = optionalCoffee.get();
            existingCoffee.setName(coffee.getName());
            // Update other properties as needed
            jmsMessageProducer.sendMessage("myQueue", "Coffee updated: " + existingCoffee.getName());
            return coffeeRepository.save(existingCoffee);
        } else {
            return null;
        }
    }

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

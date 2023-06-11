package se.pbt.mrcoffe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.pbt.mrcoffe.model.Coffee;
import se.pbt.mrcoffe.repository.CoffeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeService {

    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public List<Coffee> getAllCoffees() {
        return (List<Coffee>) coffeeRepository.findAll();
    }

    public Coffee getCoffeeById(String id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        return optionalCoffee.orElse(null);
    }

    public Coffee createCoffee(Coffee coffee) {
        return coffeeRepository.save(coffee);
    }

    public Coffee updateCoffee(String id, Coffee coffee) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            Coffee existingCoffee = optionalCoffee.get();
            existingCoffee.setName(coffee.getName());
            // Update other properties as needed
            return coffeeRepository.save(existingCoffee);
        } else {
            return null;
        }
    }

    public boolean deleteCoffee(String id) {
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(id);
        if (optionalCoffee.isPresent()) {
            coffeeRepository.delete(optionalCoffee.get());
            return true;
        } else {
            return false;
        }
    }
}

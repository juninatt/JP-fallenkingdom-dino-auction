package se.pbt.mrcoffee.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.dto.request.CoffeeDTO;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.service.product.CoffeeService;

import java.util.List;

/**
 * REST Controller for managing {@link Coffee} entities.
 */
@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    /**
     * Constructor for CoffeeController.
     *
     * @param coffeeService The {@link CoffeeService} for Coffee entities.
     */
    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * Get all {@link Coffee} entities.
     *
     * @return A ResponseEntity with the list of all Coffee entities.
     */
    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffees() {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        return ResponseEntity.ok(coffees);
    }

    /**
     * Get a {@link Coffee} entity by its ID.
     *
     * @param id The ID of the Coffee entity.
     * @return A ResponseEntity containing the Coffee entity or a 404 status code if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable long id) {
        var coffee = coffeeService.getCoffeeById(id);
        if (coffee != null) {
            return ResponseEntity.ok(coffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Create a new {@link Coffee} entity.
     *
     * @param coffeeDetails The details for the new Coffee entity.
     * @return A ResponseEntity containing the newly created Coffee entity.
     */
    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody CoffeeDTO coffeeDetails) {
        var createdCoffee = coffeeService.createCoffee(coffeeDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffee);
    }

    /**
     * Update an existing {@link Coffee} entity.
     *
     * @param id    The ID of the Coffee entity to update.
     * @param coffee The updated details for the Coffee entity.
     * @return A ResponseEntity containing the updated Coffee entity or a 404 status code if not found.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable long id, @RequestBody CoffeeDTO coffee) {
        var updatedCoffee = coffeeService.updateCoffee(id, coffee);
        if (updatedCoffee != null) {
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete a {@link Coffee} entity by its ID.
     *
     * @param id The ID of the Coffee entity to delete.
     * @return A ResponseEntity with a 204 status code if deletion was successful, or a 404 status code if not found.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable long id) {
        boolean deleted = coffeeService.deleteCoffee(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

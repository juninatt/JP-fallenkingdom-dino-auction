package se.pbt.mrcoffee.controller.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.service.product.CoffeeService;

import java.util.List;

@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffees() {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        return ResponseEntity.ok(coffees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable long id) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        if (coffee != null) {
            return ResponseEntity.ok(coffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee) {
        Coffee createdCoffee = coffeeService.createCoffee(coffee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCoffee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable long id, @RequestBody Coffee coffee) {
        Coffee updatedCoffee = coffeeService.updateCoffee(id, coffee);
        if (updatedCoffee != null) {
            return ResponseEntity.ok(updatedCoffee);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

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

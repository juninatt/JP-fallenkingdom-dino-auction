package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.Coffee;
import se.pbt.mrcoffee.repository.CoffeeRepository;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeeServiceTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private CoffeeService coffeeService;


    @BeforeEach
    void setup() {
        coffeeRepository.deleteAll();
        coffeeRepository.saveAll(List.of(
                TestObjectFactory.createCoffee("Americano"),
                TestObjectFactory.createCoffee("Cappuccino"),
                TestObjectFactory.createCoffee("Espresso"),
                TestObjectFactory.createCoffee("Latte"),
                TestObjectFactory.createCoffee("Double Double")
        ));
    }


    @Nested
    @DisplayName("getAllCoffees():")
    public class GetAllCoffeesTests {

        @Nested
        @DisplayName("Returns:")
        class GetAllCoffeesReturnTest {

            @Test
            @DisplayName("Returns list of objects of class Coffe.java")
            void getAllCoffees_validInput_returnsObjectsOfClassCoffe() {
                // Call method to test
                var result = coffeeService.getAllCoffees();

                // Assert all objects in returned list are of class Coffee.java
                assertNotNull(result);
                result.forEach(
                        coffee -> assertEquals(Coffee.class, coffee.getClass()));
            }

            @Test
            @DisplayName("Returns list of correct size")
            void getAllCoffees_validInput_returnsListOfCorrectSize() {
                // Call method to test
                var result = coffeeService.getAllCoffees();

                // Assert returned list is of expected size
                assertNotNull(result);
                assertEquals(result.size(), result.size());
            }

        }

        @Nested
        @DisplayName("Throws")
        class GetAllCoffeesThrowsTest {

            @Test
            @DisplayName("Throws CoffeeNotFoundException when no objects are found in database")
            void getAllCoffees_returnEmptyList() {
                // Empty database
                coffeeRepository.deleteAll();
                assertTrue(coffeeRepository.findAll().isEmpty());

                // Call method to test and assert for NullPointerException
                assertThrows(CoffeeNotFoundException.class, () -> coffeeService.getAllCoffees());
            }
        }
    }
}

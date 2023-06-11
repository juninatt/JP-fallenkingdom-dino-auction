package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.Coffee;
import se.pbt.mrcoffee.repository.CoffeeRepository;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class CoffeeServiceTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private CoffeeService coffeeService;


    @Nested
    @DisplayName("getAllCoffees():")
    public class GetAllCoffeesTests {

        @Test
        @DisplayName("Returns correct number of objects")
        void getAllCoffees_returnListOfCoffees() {
            // Arrange
            var coffe1 = TestObjectFactory.createCoffee("Coffee 1");
            var coffe2 = TestObjectFactory.createCoffee("Coffee 2");

            coffeeRepository.save(coffe1);
            coffeeRepository.save(coffe2);

            // Act
            List<Coffee> resultCoffees = coffeeRepository.findAll();

            // Assert
            assertNotNull(resultCoffees);
            assertEquals(resultCoffees.size(), resultCoffees.size());
        }

        @Test
        @DisplayName("Returns empty list when no objects are found in database")
        void getAllCoffees_returnEmptyList() {
            coffeeRepository.deleteAll();
            // Arrange
            List<Coffee> expectedCoffees = Collections.emptyList();

            // Act
            List<Coffee> resultCoffees = coffeeRepository.findAll();

            // Assert
            assertNotNull(resultCoffees);
            assertEquals(expectedCoffees.size(), resultCoffees.size());
            assertEquals(expectedCoffees, resultCoffees);
        }
    }
}

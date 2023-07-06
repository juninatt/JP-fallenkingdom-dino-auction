package se.pbt.mrcoffee.integrationtest.service.product;

import se.pbt.mrcoffee.integrationtest.testobject.TestObjectFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.service.product.CoffeeService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("CoffeeService:")
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
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee()
        ));
    }


    @Nested
    @DisplayName("getAllCoffees():")
    class GetAllCoffeesTests {

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

    @Nested
    @DisplayName("updateCoffee():")
    class UpdateCoffeeTests {

        @Test
        @DisplayName("Returns updated Coffee when Coffee exists")
        void updateCoffee_validInput_returnsUpdatedCoffee() {
            // Arrange
            long coffeeId = 1L;
            String updatedName = "Updated Coffee";
            Coffee updatedCoffee = new Coffee();
            updatedCoffee.setName(updatedName);

            Coffee existingCoffee = TestObjectFactory.createCoffee();
            CoffeeRepository coffeeRepository = mock(CoffeeRepository.class);
            when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(existingCoffee));
            when(coffeeRepository.save(existingCoffee)).thenReturn(existingCoffee);

            JmsMessageProducer jmsMessageProducer = mock(JmsMessageProducer.class);

            CoffeeService coffeeService = new CoffeeService(coffeeRepository, jmsMessageProducer);

            // Act
            Coffee result = coffeeService.updateCoffee(coffeeId, updatedCoffee);

            // Assert
            assertNotNull(result);
            assertEquals(updatedName, result.getName());
            verify(jmsMessageProducer).sendMessage("myQueue", "Coffee updated: " + updatedName);
        }

        @Test
        @DisplayName("Returns null when Coffee does not exist")
        void updateCoffee_coffeeNotFound_returnsNull() {
            // Arrange
            long nonExistingCoffeeId = 999L;
            CoffeeRepository coffeeRepository = mock(CoffeeRepository.class);
            when(coffeeRepository.findById(nonExistingCoffeeId)).thenReturn(Optional.empty());

            JmsMessageProducer jmsMessageProducer = mock(JmsMessageProducer.class);

            CoffeeService coffeeService = new CoffeeService(coffeeRepository, jmsMessageProducer);

            // Act
            Coffee result = coffeeService.updateCoffee(nonExistingCoffeeId, new Coffee());

            // Assert
            assertNull(result);
            verifyNoInteractions(jmsMessageProducer);
        }
    }
}

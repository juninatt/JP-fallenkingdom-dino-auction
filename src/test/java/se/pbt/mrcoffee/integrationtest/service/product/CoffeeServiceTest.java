package se.pbt.mrcoffee.integrationtest.service.product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;
import se.pbt.mrcoffee.jms.JmsMessageProducer;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.repository.product.CoffeeRepository;
import se.pbt.mrcoffee.service.product.CoffeeService;
import se.pbt.mrcoffee.testobject.TestObjectFactory;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("CoffeeService integration tests")
class CoffeeServiceTest {

    @Autowired
    private CoffeeRepository coffeeRepository;

    @Autowired
    private JmsMessageProducer jmsMessageProducer;

    @Autowired
    private CoffeeService coffeeService;

    @BeforeEach
    void setup() {
        // Reset the database and populate with test data
        coffeeRepository.deleteAll();
        coffeeRepository.saveAll(List.of(
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee(),
                TestObjectFactory.createCoffee()
        ));
    }


    @Test
    @DisplayName("Returns a list of coffee objects")
    void shouldReturnListContainingCoffeeInstances() {
        // Invoke the service method
        var result = coffeeService.getAllCoffees();

        // Verify each returned object is of Coffee type
        assertNotNull(result);
        result.forEach(coffee -> assertEquals(Coffee.class, coffee.getClass()));
    }

    @Test
    @DisplayName("Returns a list of expected size")
    void shouldReturnListWithExpectedSize() {
        // Invoke the service method
        var result = coffeeService.getAllCoffees();

        // Verify the size of the returned list
        assertNotNull(result);
        assertEquals(5, result.size());
    }

    @Test
    @DisplayName("Throws CoffeeNotFoundException when no coffee object is found")
    void shouldThrowCoffeeNotFoundException() {
        // Clear the database
        coffeeRepository.deleteAll();
        assertTrue(coffeeRepository.findAll().isEmpty());

        // Expect exception when invoking the service method
        assertThrows(CoffeeNotFoundException.class, () -> coffeeService.getAllCoffees());
            }

    @Test
    @DisplayName("Returns updated coffee when the coffee exists")
    void shouldReturnUpdatedCoffeeWhenCoffeeExists() {
        // Set up the context with mock objects
        long coffeeId = 1L;
        var coffeeDTO = TestObjectFactory.createCoffeeDTO();
        var existingCoffee = TestObjectFactory.createCoffee();

        // Use mocks for external dependencies in order to test JMS Messanger
        CoffeeRepository coffeeRepositoryMock = mock(CoffeeRepository.class);
        when(coffeeRepositoryMock.findById(coffeeId)).thenReturn(Optional.of(existingCoffee));
        when(coffeeRepositoryMock.save(existingCoffee)).thenReturn(existingCoffee);

        JmsMessageProducer jmsMessageProducerMock = mock(JmsMessageProducer.class);

        CoffeeService coffeeServiceMock = new CoffeeService(coffeeRepositoryMock, jmsMessageProducerMock);

        // Invoke the service method for update
        Coffee result = coffeeServiceMock.updateCoffee(coffeeId, coffeeDTO);

        // Verify coffee is updated and correct jms message was sent
        assertNotNull(result);
        assertEquals(coffeeDTO.name(), result.getName());
        verify(jmsMessageProducerMock).sendMessage("myQueue", "Coffee updated: " + result.getName());
        }

    @Test
    @DisplayName("Returns null when no coffee object is found")
    void shouldReturnNullWhenCoffeeDoesNotExist() {
        // Mock the repository to return empty. Mocking necessary to test JMS Messanger
        long nonExistingCoffeeId = 999L;
        CoffeeRepository coffeeRepositoryMock = mock(CoffeeRepository.class);
        when(coffeeRepositoryMock.findById(nonExistingCoffeeId)).thenReturn(Optional.empty());

        JmsMessageProducer jmsMessageProducerMock = mock(JmsMessageProducer.class);

        CoffeeService coffeeServiceMock = new CoffeeService(coffeeRepositoryMock, jmsMessageProducerMock);

        // Invoke the service method
        Coffee result = coffeeServiceMock.updateCoffee(nonExistingCoffeeId, TestObjectFactory.createCoffeeDTO());

        // Verify result and no interactions with the message producer
        assertNull(result);
        verifyNoInteractions(jmsMessageProducerMock);
    }
}

package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.pbt.mrcoffee.messaging.JmsMessageProducer;
import se.pbt.mrcoffee.model.Coffee;
import se.pbt.mrcoffee.repository.CoffeeRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class CoffeeServiceTest {

    @Mock
    private CoffeeRepository coffeeRepository;

    @Mock
    private JmsMessageProducer jmsMessageProducer;

    @InjectMocks
    private CoffeeService coffeeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getCoffeeById_existingId_returnsCoffee() {
        // Arrange
        Coffee expectedCoffee = new Coffee(
                "Coffee 1",
                "Description",
                BigDecimal.valueOf(1),
                "Origin",
                "Roast Level",
                "Flavour Notes",
                "Caffeine Content");
        when(coffeeRepository.findById(1L)).thenReturn(Optional.of(expectedCoffee));

        // Act
        Coffee resultCoffee = coffeeRepository.findById(1L).get();

        // Assert
        assertNotNull(resultCoffee);
        assertEquals(expectedCoffee, resultCoffee);
        verify(coffeeRepository, times(1)).findById(1L);
    }

    @Test
    void updateCoffee_existingId_updatesCoffeeAndSendsJmsMessage() {
        // Arrange
        long coffeeId = 1L;
        Coffee existingCoffee = new Coffee(
                "Coffee 1",
                "Description",
                BigDecimal.valueOf(1),
                "Origin",
                "Roast Level",
                "Flavour Notes",
                "Caffeine Content");
        Coffee updatedCoffee = new Coffee(
                "Updated Coffee",
                "Updated Description",
                BigDecimal.valueOf(2),
                "Updated Origin",
                "Updated Roast Level",
                "Updated Flavour Notes",
                "Updated Caffeine Content");

        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(existingCoffee));
        when(coffeeRepository.save(existingCoffee)).thenReturn(updatedCoffee);

        // Act
        Coffee resultCoffee = coffeeService.updateCoffee(coffeeId, updatedCoffee);

        // Assert
        assertNotNull(resultCoffee);
        assertEquals(updatedCoffee, resultCoffee);
        verify(coffeeRepository, times(1)).findById(coffeeId);
        verify(coffeeRepository, times(1)).save(existingCoffee);
        verify(jmsMessageProducer, times(1)).sendMessage("myQueue", "Coffee updated: " + existingCoffee.getName());
    }
}

package se.pbt.mrcoffee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.pbt.mrcoffee.model.Coffee;
import se.pbt.mrcoffee.repository.CoffeeRepository;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CoffeeServiceTest {

    @Mock
    private CoffeeRepository coffeeRepository;

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
        Coffee resultCoffee = coffeeService.getCoffeeById(1);

        // Assert
        assertNotNull(resultCoffee);
        assertEquals(expectedCoffee, resultCoffee);
        verify(coffeeRepository, times(1)).findById(1L);
    }
}

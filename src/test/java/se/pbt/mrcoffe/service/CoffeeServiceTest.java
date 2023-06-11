package se.pbt.mrcoffe.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import se.pbt.mrcoffe.model.Coffee;
import se.pbt.mrcoffe.repository.CoffeeRepository;

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
        String coffeeId = "1";
        Coffee expectedCoffee = new Coffee(coffeeId, "Coffee 1");
        when(coffeeRepository.findById(coffeeId)).thenReturn(Optional.of(expectedCoffee));

        // Act
        Coffee resultCoffee = coffeeService.getCoffeeById(coffeeId);

        // Assert
        assertNotNull(resultCoffee);
        assertEquals(expectedCoffee, resultCoffee);
        verify(coffeeRepository, times(1)).findById(coffeeId);
    }

    // Implement test methods for other service methods
    // getAllCoffees(), createCoffee(), updateCoffee(), deleteCoffee()
}

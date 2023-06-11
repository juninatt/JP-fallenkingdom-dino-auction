package se.pbt.mrcoffe.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.pbt.mrcoffe.model.Coffee;
import se.pbt.mrcoffe.service.CoffeeService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CoffeeControllerTest {

    @Mock
    private CoffeeService coffeeService;

    @InjectMocks
    private CoffeeController coffeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllCoffees() {
        // Arrange
        List<Coffee> expectedCoffees = Arrays.asList(
                new Coffee("1", "Coffee 1"),
                new Coffee("2", "Coffee 2")
        );
        when(coffeeService.getAllCoffees()).thenReturn(expectedCoffees);

        // Act
        ResponseEntity<List<Coffee>> response = coffeeController.getAllCoffees();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCoffees, response.getBody());
        verify(coffeeService, times(1)).getAllCoffees();
    }

    // Implement test methods for other controller methods
    // getCoffeeById(), createCoffee(), updateCoffee(), deleteCoffee()
}

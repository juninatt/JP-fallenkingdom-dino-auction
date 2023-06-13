package se.pbt.mrcoffee.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import se.pbt.mrcoffee.model.product.Coffee;
import se.pbt.mrcoffee.service.CoffeeService;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("CoffeeController:")
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
                new Coffee("1", "Coffee 1", BigDecimal.valueOf(9.99), "Colombia", "Medium", "Rich and fruity", "Medium"),
                new Coffee("2", "Coffee 2", BigDecimal.valueOf(12.99), "Brazil", "Dark", "Intense and chocolatey", "High"),
                new Coffee("3", "Coffee 3", BigDecimal.valueOf(11.50), "Costa Rica", "Medium", "Smooth and nutty", "Medium"),
                new Coffee("4", "Coffee 4", BigDecimal.valueOf(10.75), "Brazil", "Medium-Dark", "Bold and caramel", "High"),
                new Coffee("5", "Coffee 5", BigDecimal.valueOf(14.99), "Ethiopia", "Light", "Floral and citrusy", "Low")
        );
        when(coffeeService.getAllCoffees()).thenReturn(expectedCoffees);

        // Act
        ResponseEntity<List<Coffee>> response = coffeeController.getAllCoffees();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCoffees, response.getBody());
        verify(coffeeService, times(1)).getAllCoffees();
    }

}

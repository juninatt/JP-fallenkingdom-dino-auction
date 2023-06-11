package se.pbt.mrcoffee.exception.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("GlobalExceptionHandler:")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Handles CoffeeNotFoundException")
    void handleCoffeeNotFoundException_returnsValidErrorResponse() {
        // Create the exception
        var exception = new CoffeeNotFoundException("Coffee not found");

        // Call the handler method to be tested
        var response = globalExceptionHandler.handleCoffeeNotFoundException(exception);

        // Assert the returned ErrorResponse contains the expected values
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Coffee not found", response.getBody().getErrorMessage());
        assertEquals(exception, response.getBody().getDetails());
    }
}


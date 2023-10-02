package se.pbt.dinoauction.unittest.exception.handler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.exception.handler.GlobalExceptionHandler;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("GlobalExceptionHandler:")
class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();

    @Test
    @DisplayName("Handles DinosaurNotFoundException")
    void handleDinosaurNotFoundException_returnsValidErrorResponse() {
        // Create the exception
        var exception = new DinosaurNotFoundException("Dinosaur not found");

        // Call the handler method to be tested
        var response = globalExceptionHandler.handleDinosaurNotFoundException(exception);

        // Assert the returned ErrorResponse contains the expected values
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Dinosaur not found", response.getBody().getErrorMessage());
        assertEquals(exception, response.getBody().getDetails());
    }
}


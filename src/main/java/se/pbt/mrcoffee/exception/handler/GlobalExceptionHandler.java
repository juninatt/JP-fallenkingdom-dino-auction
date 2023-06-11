package se.pbt.mrcoffee.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.pbt.mrcoffee.exception.CoffeeNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CoffeeNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleCoffeeNotFoundException(CoffeeNotFoundException exception) {
        ErrorResponse<?> errorResponse = new ErrorResponse<>(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                Instant.now().toEpochMilli(),
                exception
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

package se.pbt.mrcoffee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.pbt.mrcoffee.model.Coffee;

/**
 * Exception thrown when a {@link Coffee} object is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CoffeeNotFoundException extends RuntimeException {

    public CoffeeNotFoundException() {
        super("Coffee could not be found");
    }

    public CoffeeNotFoundException(String message) {
        super(message);
    }

    public CoffeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

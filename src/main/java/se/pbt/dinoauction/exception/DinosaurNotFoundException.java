package se.pbt.dinoauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.pbt.dinoauction.model.auctionitem.Dinosaur;

/**
 * Exception thrown when a specified {@link Dinosaur} is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class DinosaurNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a predefined message.
     */
    public DinosaurNotFoundException() {
        super("Dinosaur could not be found");
    }

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message the detail message
     */
    public DinosaurNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public DinosaurNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

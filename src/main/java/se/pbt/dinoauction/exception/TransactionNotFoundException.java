package se.pbt.dinoauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.pbt.dinoauction.model.transaction.Transaction;

/**
 * Exception thrown when a specified {@link Transaction} is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message the detail message
     */
    public TransactionNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public TransactionNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

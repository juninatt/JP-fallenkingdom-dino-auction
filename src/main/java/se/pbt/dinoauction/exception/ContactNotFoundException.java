package se.pbt.dinoauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.pbt.dinoauction.model.contact.Contact;

/**
 * Exception thrown when specified {@link Contact} is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ContactNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message the detail message
     */
    public ContactNotFoundException(String message) {  super(message);  }

    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public ContactNotFoundException(String message, Throwable cause) {  super(message, cause);   }
}

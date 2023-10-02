package se.pbt.dinoauction.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import se.pbt.dinoauction.model.user.security.Role;

/**
 * Exception thrown when a specified {@link Role} is not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class RoleNotFoundException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message the detail message
     */
    public RoleNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


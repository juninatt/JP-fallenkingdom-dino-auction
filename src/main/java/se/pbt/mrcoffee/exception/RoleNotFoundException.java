package se.pbt.mrcoffee.exception;


/**
 * This class represents a custom exception to be thrown when a role is not found.
 */
public class RoleNotFoundException extends RuntimeException {

    /**
     * Default constructor with a predefined message.
     */
    public RoleNotFoundException() {
        super("Role not found.");
    }

    /**
     * Constructor with a custom message.
     * @param message The custom message to be displayed.
     */
    public RoleNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor with a custom message and a cause.
     * @param message The custom message to be displayed.
     * @param cause The cause of this exception.
     */
    public RoleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}


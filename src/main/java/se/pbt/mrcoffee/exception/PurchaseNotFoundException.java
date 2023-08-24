package se.pbt.mrcoffee.exception;

/**
 * Represents a custom exception thrown when a Purchase entity is not found in the database.
 * This exception is typically used to signal that a requested purchase-related operation
 * could not be performed because the specified purchase does not exist.
 */
public class PurchaseNotFoundException extends RuntimeException {

    /**
     * Constructs a new PurchaseNotFoundException with the specified detail message.
     *
     * @param message the detail message explaining why the exception was thrown.
     */
    public PurchaseNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new PurchaseNotFoundException with the specified detail message and cause.
     *
     * @param message the detail message explaining why the exception was thrown.
     * @param cause   the underlying cause of the exception (e.g., a nested exception).
     */
    public PurchaseNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

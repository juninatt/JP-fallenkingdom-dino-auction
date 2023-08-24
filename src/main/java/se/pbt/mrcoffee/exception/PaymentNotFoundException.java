package se.pbt.mrcoffee.exception;

/**
 * Custom exception thrown when a Payment entity is not found in the database.
 * It extends {@link RuntimeException}, so it's an unchecked exception.
 */
public class PaymentNotFoundException extends RuntimeException {

    /**
     * Default constructor that creates a new PaymentNotFoundException with a detailed error message.
     *
     * @param message The detailed error message describing why the exception was thrown.
     */
    public PaymentNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a PaymentNotFoundException with a detailed error message and a reference to the original cause of the exception.
     *
     * @param message The detailed error message describing why the exception was thrown.
     * @param cause   The original cause of the exception (may be null).
     */
    public PaymentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

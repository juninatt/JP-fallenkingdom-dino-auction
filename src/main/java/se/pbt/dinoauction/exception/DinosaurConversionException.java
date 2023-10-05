package se.pbt.dinoauction.exception;

public class DinosaurConversionException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message the detail message
     */
    public DinosaurConversionException(String message) {
        super(message);
    }


    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message the detail message
     * @param cause the cause of the exception
     */
    public DinosaurConversionException(String message, Throwable cause) {
        super(message, cause);
    }
}

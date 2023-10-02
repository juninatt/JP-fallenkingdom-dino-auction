package se.pbt.dinoauction.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import se.pbt.dinoauction.exception.ContactNotFoundException;
import se.pbt.dinoauction.exception.DinosaurNotFoundException;
import se.pbt.dinoauction.exception.RoleNotFoundException;
import se.pbt.dinoauction.exception.TransactionNotFoundException;

import java.time.Instant;

/**
 * Acts as a centralized exception handling mechanism for the entire application.
 * This class captures specific exceptions and maps them to appropriate HTTP response
 * statuses and custom error messages, providing a consistent error response format.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles {@link DinosaurNotFoundException}.
     *
     * @param exception the thrown exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(DinosaurNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleDinosaurNotFoundException(DinosaurNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    /**
     * Handles {@link TransactionNotFoundException}.
     *
     * @param exception the thrown exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleTransactionNotFoundException(TransactionNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    /**
     * Handles {@link RoleNotFoundException}.
     *
     * @param exception the thrown exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleRoleNotFoundException(RoleNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    /**
     * Handles {@link ContactNotFoundException}.
     *
     * @param exception the thrown exception
     * @return ResponseEntity containing the error details
     */
    @ExceptionHandler(ContactNotFoundException.class)
    public ResponseEntity<ErrorResponse<?>> handleContactNotFoundException(ContactNotFoundException exception) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, exception);
    }

    /**
     * Builds and returns an ErrorResponse wrapped in a ResponseEntity for the given exception.
     *
     * @param status    the HTTP status
     * @param exception the thrown exception
     * @return ResponseEntity containing the error details
     */
    private ResponseEntity<ErrorResponse<?>> buildErrorResponse(HttpStatus status, RuntimeException exception) {
        ErrorResponse<?> errorResponse = new ErrorResponse<>(
                status.value(),
                exception.getMessage(),
                Instant.now().toEpochMilli(),
                exception
        );
        return ResponseEntity.status(status).body(errorResponse);
    }
}

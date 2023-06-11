package se.pbt.mrcoffee.exception.handler;

import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ChangeSetPersister.NotFoundException.class)
    public ResponseEntity<ErrorResponse<String>> handleNotFoundException(ChangeSetPersister.NotFoundException exception) {
        ErrorResponse<String> errorResponse = new ErrorResponse<>(
                HttpStatus.NOT_FOUND.value(),
                exception.getMessage(),
                Instant.now().toEpochMilli(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}

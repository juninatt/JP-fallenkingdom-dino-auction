package se.pbt.mrcoffee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CustomerContactNotFoundException extends RuntimeException {

    public CustomerContactNotFoundException() { super("Contact information of customer could not be found"); }

    public CustomerContactNotFoundException(String message) {  super(message);  }

    public CustomerContactNotFoundException(String message, Throwable cause) {  super(message, cause);   }
}

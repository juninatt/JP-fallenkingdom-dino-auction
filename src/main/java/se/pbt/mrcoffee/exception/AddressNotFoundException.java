package se.pbt.mrcoffee.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {

    public AddressNotFoundException() { super("Address could not be found"); }

    public AddressNotFoundException(String message) {  super(message);  }

    public AddressNotFoundException(String message, Throwable cause) {  super(message, cause);   }


}

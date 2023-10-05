package se.pbt.dinoauction.exception;

import se.pbt.dinoauction.model.entity.user.AppUser;
import se.pbt.dinoauction.model.entity.user.security.Role;

/**
 * Exception related to authority issues for {@link AppUser}s.
 * @see Role
 */
public class UserAuthorityException extends RuntimeException {

    /**
     * Constructs a new exception with a detailed message.
     *
     * @param message A message providing details on why the exception was thrown.
     */
    public UserAuthorityException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a detailed message and cause.
     *
     * @param message The detail message.
     * @param cause The cause of the exception.
     */
    public UserAuthorityException(String message, Throwable cause) {
        super(message, cause);
    }
}

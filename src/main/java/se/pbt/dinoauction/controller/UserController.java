package se.pbt.dinoauction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.dinoauction.model.user.AppUser;
import se.pbt.dinoauction.service.user.UserService;

import java.util.List;

/**
 * UserController is a RESTful controller responsible for managing user accounts.
 * It provides CRUD operations for AppUser entities.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Constructor for dependency injection.
     *
     * @param userService The service responsible for managing user accounts
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Retrieves all existing users.
     *
     * @return ResponseEntity containing a list of all AppUser entities and HTTP status OK (200).
     */
    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        List<AppUser> appUsers = userService.getAllUsers();
        return new ResponseEntity<>(appUsers, HttpStatus.OK);
    }

    /**
     * Fetches a single user by their unique ID.
     *
     * @param id Unique identifier for the user
     * @return ResponseEntity containing the AppUser entity or HTTP status NOT FOUND (404) if the user does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppUser> getUserById(@PathVariable Long id) {
        AppUser appUser = userService.getUserById(id);
        return appUser != null ? new ResponseEntity<>(appUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Updates an existing user account identified by its unique ID.
     *
     * @param id Unique identifier for the user to be updated
     * @param appUser AppUser entity containing the updated user information
     * @return ResponseEntity containing the updated AppUser or HTTP status NOT FOUND (404) if the user does not exist.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AppUser> updateUser(@PathVariable Long id, @RequestBody AppUser appUser) {
        AppUser updatedAppUser = userService.updateUser(id, appUser);
        return updatedAppUser != null ? new ResponseEntity<>(updatedAppUser, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Deletes an existing user account by its unique ID.
     *
     * @param id Unique identifier for the user to be deleted
     * @return ResponseEntity with HTTP status NO CONTENT (204) on successful deletion,
     * or HTTP status NOT FOUND (404) if the user does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        return deleted ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}



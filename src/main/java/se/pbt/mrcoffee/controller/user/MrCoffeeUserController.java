package se.pbt.mrcoffee.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.pbt.mrcoffee.model.user.MrCoffeeUser;
import se.pbt.mrcoffee.service.user.MrCoffeeUserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class MrCoffeeUserController {

    private final MrCoffeeUserService userService;

    @Autowired
    public MrCoffeeUserController(MrCoffeeUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<MrCoffeeUser>> getAllUsers() {
        List<MrCoffeeUser> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MrCoffeeUser> getUserById(@PathVariable Long id) {
        MrCoffeeUser user = userService.getUserById(id);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<MrCoffeeUser> updateUser(@PathVariable Long id, @RequestBody MrCoffeeUser user) {
        MrCoffeeUser updatedUser = userService.updateUser(id, user);
        if (updatedUser != null) {
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}


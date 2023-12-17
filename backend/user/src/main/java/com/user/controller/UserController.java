package com.user.controller;

import com.user.model.UserEntity;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint for checking if the controller is responsive.
     *
     * @return ResponseEntity with "Ping" and HttpStatus.OK.
     */
    @GetMapping("/ping")
    public ResponseEntity<String> getPing() {
        return new ResponseEntity<>("Ping", HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a list of all users.
     *
     * @return ResponseEntity with the list of users and HttpStatus.OK.
     */
    @GetMapping("")
    public ResponseEntity<List<UserEntity>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    /**
     * Endpoint for updating an existing user.
     *
     * @param user The user object to be updated.
     * @return ResponseEntity with the updated user object and HttpStatus.OK.
     */
    @PutMapping("")
    public ResponseEntity<UserEntity> updateUser(@RequestBody UserEntity user) {
        return new ResponseEntity(userService.updateUser(user), HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new user.
     *
     * @param user The user object to be created.
     * @return ResponseEntity with the created user object and HttpStatus.OK.
     */
    @PostMapping("")
    public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity user) {
        return new ResponseEntity(userService.createUser(user), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a user by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return ResponseEntity with HttpStatus.OK if the user was deleted, HttpStatus.CONFLICT otherwise.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        if (userService.deleteUserById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint for deleting a user by name.
     *
     * @param name The username of the user to be deleted.
     * @return ResponseEntity with HttpStatus.OK if the user was deleted, HttpStatus.CONFLICT otherwise.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity deleteUserByName(@PathVariable String name) {
        if (userService.deleteUserByName(name)) {
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

    /**
     * Endpoint for retrieving a user by ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return ResponseEntity with the user if found and HttpStatus.OK, otherwise HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Long id) {
        return new ResponseEntity(userService.getUserById(id), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a user by name.
     *
     * @param name The username of the user to be retrieved.
     * @return ResponseEntity with the user if found and HttpStatus.OK, otherwise HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{name}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable String name) {
        return new ResponseEntity(userService.getUserByName(name), HttpStatus.OK);
    }
}

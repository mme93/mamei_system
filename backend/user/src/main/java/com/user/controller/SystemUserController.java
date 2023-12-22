package com.user.controller;

import com.user.model.SystemUserEntity;
import com.user.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST Controller for managing user-related operations.
 */
@RestController
@RequestMapping("/system_user")
public class SystemUserController {

    private final SystemUserService systemUserService;

    @Autowired
    public SystemUserController(SystemUserService systemUserService) {
        this.systemUserService = systemUserService;
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
    public ResponseEntity<List<SystemUserEntity>> getUsers() {
        return new ResponseEntity<>(systemUserService.getUsers(), HttpStatus.OK);
    }

    /**
     * Endpoint for updating an existing user.
     *
     * @param user The user object to be updated.
     * @return ResponseEntity with the updated user object and HttpStatus.OK.
     */
    @PutMapping("")
    public ResponseEntity<SystemUserEntity> updateUser(@RequestBody SystemUserEntity user) {
        return new ResponseEntity(systemUserService.updateUser(user), HttpStatus.OK);
    }

    /**
     * Endpoint for creating a new user.
     *
     * @param user The user object to be created.
     * @return ResponseEntity with the created user object and HttpStatus.OK.
     */
    @PostMapping("")
    public ResponseEntity<SystemUserEntity> createUser(@RequestBody SystemUserEntity user) {
        return new ResponseEntity(systemUserService.createUser(user), HttpStatus.OK);
    }

    /**
     * Endpoint for deleting a user by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return ResponseEntity with HttpStatus.OK if the user was deleted, HttpStatus.CONFLICT otherwise.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUserById(@PathVariable Long id) {
        if (systemUserService.deleteUserById(id)) {
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
        if (systemUserService.deleteUserByName(name)) {
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
    public ResponseEntity<SystemUserEntity> getUserById(@PathVariable Long id) {
        return new ResponseEntity(systemUserService.getUserById(id), HttpStatus.OK);
    }

    /**
     * Endpoint for retrieving a user by name.
     *
     * @param name The username of the user to be retrieved.
     * @return ResponseEntity with the user if found and HttpStatus.OK, otherwise HttpStatus.NOT_FOUND.
     */
    @GetMapping("/{name}")
    public ResponseEntity<SystemUserEntity> getUserByName(@PathVariable String name) {
        return new ResponseEntity(systemUserService.getUserByName(name), HttpStatus.OK);
    }
}

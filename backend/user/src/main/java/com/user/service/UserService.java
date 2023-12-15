package com.user.service;

import com.user.model.User;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user-related operations.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return The created user object.
     */
    public User createUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    public boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Deletes a user by username.
     *
     * @param userName The username of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    public boolean deleteUserByName(String userName) {
        try {
            userRepository.deleteByUserName(userName);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Retrieves a user by ID.
     *
     * @param id The ID of the user to be retrieved.
     * @return An Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to be retrieved.
     * @return An Optional containing the user if found, otherwise empty.
     */
    public Optional<User> getUserByName(String userName) {
        return userRepository.findByUserName(userName);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return The list of all users.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates an existing user.
     *
     * @param user The updated user object.
     * @return The updated user object.
     */
    public User updateUser(User user) {
        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
        existingUser.ifPresent(updateUser -> {
            updateUser.setUserName(user.getUserName());
            updateUser.setPassword(user.getPassword());
            userRepository.save(updateUser);
        });
        return user;
    }
}

package com.user.service;

import com.user.model.entity.SystemUserEntity;
import com.user.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing user-related operations.
 */
@Service
public class SystemUserService {

    private final SystemUserRepository systemUserRepository;

    @Autowired
    public SystemUserService(SystemUserRepository systemUserRepository) {
        this.systemUserRepository = systemUserRepository;
    }

    /**
     * Creates a new user.
     *
     * @param user The user object to be created.
     * @return The created user object.
     */
    public SystemUserEntity createUser(SystemUserEntity user) {
        return systemUserRepository.save(user);
    }

    /**
     * Deletes a user by ID.
     *
     * @param id The ID of the user to be deleted.
     * @return True if the user was deleted, false otherwise.
     */
    public boolean deleteUserById(Long id) {
        try {
            systemUserRepository.deleteById(id);
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
            systemUserRepository.deleteByUserName(userName);
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
    public Optional<SystemUserEntity> getUserById(Long id) {
        return systemUserRepository.findById(id);
    }

    /**
     * Retrieves a user by username.
     *
     * @param userName The username of the user to be retrieved.
     * @return An Optional containing the user if found, otherwise empty.
     */
    public Optional<SystemUserEntity> getUserByName(String userName) {
        return systemUserRepository.findByUserName(userName);
    }

    /**
     * Retrieves a list of all users.
     *
     * @return The list of all users.
     */
    public List<SystemUserEntity> getUsers() {
        return systemUserRepository.findAll();
    }

    /**
     * Updates an existing user.
     *
     * @param user The updated user object.
     * @return The updated user object.
     */
    public SystemUserEntity updateUser(SystemUserEntity user) {
        Optional<SystemUserEntity> existingUser = systemUserRepository.findByUserName(user.getUserName());
        existingUser.ifPresent(updateUser -> {
            updateUser.setUserName(user.getUserName());
            updateUser.setPassword(user.getPassword());
            systemUserRepository.save(updateUser);
        });
        return user;
    }
}

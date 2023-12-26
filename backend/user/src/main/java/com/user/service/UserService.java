package com.user.service;

import com.user.model.entity.UserEntity;
import com.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<UserEntity> getUserById(Integer id) {
     return userRepository.findById(id);
    }

    public Optional<UserEntity> getUserByName( String username) {
        return userRepository.findByUsername(username);
    }
}

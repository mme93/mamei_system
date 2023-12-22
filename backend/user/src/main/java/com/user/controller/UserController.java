package com.user.controller;

import com.user.model.SystemUserEntity;
import com.user.model.UserEntity;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserEntity> getUserById(@PathVariable Integer id) {
        Optional<UserEntity> userEntityOpt=userService.getUserById(id);
        if(userEntityOpt.isPresent()){
            return new ResponseEntity(userEntityOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserEntity> getUserByName(@PathVariable String username) {
        Optional<UserEntity> userEntityOpt=userService.getUserByName(username);
        if(userEntityOpt.isPresent()){
            return new ResponseEntity(userEntityOpt.get(), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}

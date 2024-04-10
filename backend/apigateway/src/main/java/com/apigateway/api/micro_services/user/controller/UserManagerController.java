package com.apigateway.api.micro_services.user.controller;

import com.apigateway.api.micro_services.user.service.UserManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * Controller class responsible for handling user management related HTTP requests.
 */
@RestController
@RequestMapping("/user")
public class UserManagerController {

    private final UserManagerService userManagerService;

    @Autowired
    public UserManagerController(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    /**
     * Retrieves an account by user ID.
     *
     * @return ResponseEntity<Object> The ResponseEntity containing the account entity if found, otherwise NOT_FOUND status
     */
    @GetMapping("/account")
    public ResponseEntity<Object> getAccountByUserId() {
        Optional<Object> accountEntity = userManagerService.getAccountByUserId();
        if (accountEntity.isPresent()) {
            return new ResponseEntity<>(accountEntity.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

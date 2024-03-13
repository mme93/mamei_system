package com.apigateway.security.controller;

import com.apigateway.security.service.SecurityUserDefaultDBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * RestController to handle requests related to default database operations for security users.
 */
@RestController
@RequestMapping("/defaultDB")
public class SecurityUserDefaultDBController {

    private final SecurityUserDefaultDBService securityUserDefaultDBService;

    /**
     * Constructs a new SecurityUserDefaultDBController with the specified SecurityUserDefaultDBService.
     * @param securityUserDefaultDBService the service for managing default database operations for security users.
     */
    @Autowired
    public SecurityUserDefaultDBController(SecurityUserDefaultDBService securityUserDefaultDBService) {
        this.securityUserDefaultDBService = securityUserDefaultDBService;
    }

    /**
     * Handles POST requests to reload security user data in the default database.
     * @return ResponseEntity indicating the result of the operation.
     */
    @PostMapping("/reload/security_user")
    public ResponseEntity<String> saveTableSecurityUserDataset(){
        if(!securityUserDefaultDBService.deleteTableSecurityUserDataset().isBlank()){
            String result=securityUserDefaultDBService.saveTableSecurityUserDataset();
            if(!result.isBlank()){
                return new ResponseEntity("Save Security User: "+result,HttpStatus.OK);
            }
        }
        return new ResponseEntity("No Security User save.",HttpStatus.OK);
    }

}

package com.user.controller;

import com.user.service.UserDbInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST Controller for managing init standard datasets.
 */
@RestController
@RequestMapping("/user/db/init")
public class UserDbInitController {

    private final UserDbInitService initService;

    @Autowired
    public UserDbInitController(UserDbInitService initService) {
        this.initService = initService;
    }

    /**
     *
     * @return {@link HttpStatus#OK} when standard datasets set, {@link HttpStatus#CONFLICT} if not.
     */
    @PostMapping("")
    public ResponseEntity initDatabase(){
        if(initService.initDatabase()){
            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.CONFLICT);
    }

}

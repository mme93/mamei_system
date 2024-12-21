package com.gamesmanager.game.pixelquest.account.controller;

import com.gamesmanager.game.pixelquest.account.service.PixelQuestAccountService;
import com.gamesmanager.game.pixelquest.account.dto.PixelQuestUserDto;
import com.gamesmanager.game.pixelquest.account.service.PixelQuestUserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pixelquest/account")
public class AccountController {

    private final PixelQuestAccountService pixelQuestAccountService;
    private final PixelQuestUserService pixelQuestUserService;

    public AccountController(PixelQuestAccountService pixelQuestAccountService, PixelQuestUserService pixelQuestUserService) {
        this.pixelQuestAccountService = pixelQuestAccountService;
        this.pixelQuestUserService = pixelQuestUserService;
    }

    @GetMapping("/test")
    public ResponseEntity getTest(){


        pixelQuestUserService.test(true);

        return null;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PixelQuestUserDto pixelQuestUserDto) {
        return new ResponseEntity<>(pixelQuestUserService.test(true), HttpStatus.OK);
        //return new ResponseEntity<>(pixelQuestUserService.login(pixelQuestUserDto), HttpStatus.OK);
       /*
        try {
            return new ResponseEntity<>(pixelQuestUserService.login(pixelQuestUserDto), HttpStatus.OK);
        } catch (RuntimeException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());

        }

        */
    }

}

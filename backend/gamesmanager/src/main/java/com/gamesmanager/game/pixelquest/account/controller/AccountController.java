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

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PixelQuestUserDto pixelQuestUserDto) {
        try {
            return new ResponseEntity<>(pixelQuestUserService.login(pixelQuestUserDto), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());

        }
    }


    @GetMapping("/{id}")
    public ResponseEntity getAccountById(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(pixelQuestAccountService.getAccountById(id), HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ex.getMessage());
        }
    }

}

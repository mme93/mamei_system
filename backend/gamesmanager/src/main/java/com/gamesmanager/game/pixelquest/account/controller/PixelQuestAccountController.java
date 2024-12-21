package com.gamesmanager.game.pixelquest.account.controller;

import com.gamesmanager.game.pixelquest.account.dto.PixelQuestUserDto;
import com.gamesmanager.game.pixelquest.account.service.PixelQuestUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/pixelquest/account")
public class PixelQuestAccountController {

    private final PixelQuestUserService pixelQuestUserService;

    public PixelQuestAccountController(PixelQuestUserService pixelQuestUserService) {
        this.pixelQuestUserService = pixelQuestUserService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody PixelQuestUserDto pixelQuestUserDto) {
        return new ResponseEntity<>(pixelQuestUserService.login(pixelQuestUserDto), HttpStatus.OK);
    }

}

package com.gamesmanager.game.test.level.controller;

import com.gamesmanager.game.test.level.model.PixelQuestLevel;
import com.gamesmanager.game.test.level.service.PixelQuestLevelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pixelquest/level")
@RestController
public class PixelQuestLevelController {

    private final PixelQuestLevelService levelService;

    public PixelQuestLevelController(PixelQuestLevelService levelService) {
        this.levelService = levelService;
    }

    @GetMapping("/load/{level}" )
    public ResponseEntity<PixelQuestLevel> loadLevel(@PathVariable String level){
       return new ResponseEntity(levelService.loadLevel(level),HttpStatus.OK);
    }

}

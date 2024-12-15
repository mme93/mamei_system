package com.gamesmanager.game.pixelquest.test;

import org.aspectj.weaver.World;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class PixelDummyController {

    @GetMapping()
    public ResponseEntity lol() {
        PixelQuestWorldEntity world = new PixelQuestWorldEntity();
        world.setName("Summer Pixel Land");
        List<PixelQuestMapEntity>mapEntities = new ArrayList<>();

        world.setMaps(mapEntities);


        return new ResponseEntity(HttpStatus.OK);
    }
}

package com.gamesmanager.game.pixelquest.test;

import com.gamesmanager.game.pixelquest.level.model.EPixelQuestMap;
import com.gamesmanager.game.pixelquest.world.model.PixelQuestWorld;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/test")
public class PixelDummyController {

    private final PixelQuestWorldRepository worldRepository;

    public PixelDummyController(PixelQuestWorldRepository worldRepository) {
        this.worldRepository = worldRepository;
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<PixelQuestWorldEntity> getPixelWorld(@PathVariable Long id){
        PixelQuestWorldEntity world=null;
        try {
            world=worldRepository.getReferenceById(id);
        }catch (IllegalStateException e){
            System.err.println(e.getMessage());
        }

        return new ResponseEntity<>(null,HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity lol() {
        PixelQuestWorldEntity world = new PixelQuestWorldEntity();
        world.setName("Summer Pixel Land");
        List<PixelQuestMapEntity> mapEntities = new ArrayList<>();
        PixelQuestMapEntity mapEntity = new PixelQuestMapEntity();
        mapEntity.setHeight(14);
        mapEntity.setWidth(32);
        mapEntity.setPixelQuestMap(EPixelQuestMap.GENESIS_GROUND);
        mapEntity.setWorld(world);
        List<PixelQuestGridElement> gridElements = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 32; j++) {
                PixelQuestGridElement element = new PixelQuestGridElement(
                        i, j, EPixelQuestBaseTexture.STONE, EPixelQuestElementItem.NOTHING);
                gridElements.add(element);
                element.setMap(mapEntity);
            }
        }
        mapEntity.setGridElements(gridElements);
        mapEntities.add(mapEntity);
        world.setMaps(mapEntities);

        worldRepository.save(world);
        return new ResponseEntity(HttpStatus.OK);
    }
}

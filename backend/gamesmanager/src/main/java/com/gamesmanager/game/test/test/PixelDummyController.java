package com.gamesmanager.game.test.test;

import com.gamesmanager.game.pixelquest.account.dto.PixelQuestAccountDto;
import com.gamesmanager.game.pixelquest.account.entity.PixelQuestAccountEntity;
import com.gamesmanager.game.pixelquest.account.repository.PixelQuestAccountRepository;
import com.gamesmanager.game.test.level.model.EPixelQuestMap;
import com.gamesmanager.game.test.map.model.PixelQuestMapGridElementDto;
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
    private final PixelQuestAccountRepository accountRepository;

    public PixelDummyController(PixelQuestWorldRepository worldRepository, PixelQuestAccountRepository accountRepository) {
        this.worldRepository = worldRepository;
        this.accountRepository = accountRepository;
    }

    @GetMapping("/accout/{id}")
    public ResponseEntity<PixelQuestAccountDto> getAccountById(@PathVariable Long id) {
        PixelQuestAccountEntity accountEntity = accountRepository.getReferenceById(id);
        return new ResponseEntity<>(new PixelQuestAccountDto(
                accountEntity.getUserName(), accountEntity.getCurrentWorldId(), accountEntity.getCurrentMapId(), accountEntity.getMapColIndex(), accountEntity.getMapRowIndex(),accountEntity.getPixelQuestMap()
        ), HttpStatus.OK);
    }

    @GetMapping("/world/{id}")
    public ResponseEntity<PixelQuestWorldDto> getPixelWorld(@PathVariable Long id) {
        PixelQuestWorldEntity result = null;
        PixelQuestWorldDto world = null;
        try {
            result = worldRepository.getReferenceById(id);
            List<PixelQuestMapDto> pixelQuestMapDtos = new ArrayList<>();
            for (PixelQuestMapEntity mapEntity : result.getMaps()) {
                List<List<PixelQuestMapGridElementDto>> rows = new ArrayList<>();
                for (int i = 0; i < mapEntity.getHeight(); i++) {
                    List<PixelQuestMapGridElementDto> elements = new ArrayList<>();
                    for (int j = 0; j < mapEntity.getWidth(); j++) {
                        int index = (i * mapEntity.getWidth()) + j;
                        PixelQuestGridElementEntity elementEntity = mapEntity.getGridElements().get(index);
                        elements.add(
                                new PixelQuestMapGridElementDto(elementEntity.getRowIndex(), elementEntity.getColumnIndex(),
                                        elementEntity.getBaseTexture(), elementEntity.getItem()));
                    }
                    rows.add(elements);
                }
                pixelQuestMapDtos.add(new PixelQuestMapDto(mapEntity.getHeight(), mapEntity.getWidth(), mapEntity.getPixelQuestMap(), new PixelQuestGridDto(rows)));
            }
            world = new PixelQuestWorldDto(result.getName(), pixelQuestMapDtos);
        } catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }

        return new ResponseEntity<>(world, HttpStatus.OK);
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
        List<PixelQuestGridElementEntity> gridElements = new ArrayList<>();
        for (int i = 0; i < 14; i++) {
            for (int j = 0; j < 32; j++) {
                PixelQuestGridElementEntity element = new PixelQuestGridElementEntity(
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

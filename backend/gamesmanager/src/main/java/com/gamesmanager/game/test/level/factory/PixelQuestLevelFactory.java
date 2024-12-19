package com.gamesmanager.game.test.level.factory;

import com.gamesmanager.game.test.level.model.EPixelQuestLevel;
import com.gamesmanager.game.test.level.model.PixelQuestLevel;
import com.gamesmanager.game.test.map.service.PixelQuestMapService;
import org.springframework.stereotype.Component;

@Component
public class PixelQuestLevelFactory {

    private final PixelQuestMapService mapService;

    public PixelQuestLevelFactory(PixelQuestMapService mapService) {
        this.mapService = mapService;
    }

    public PixelQuestLevel createLevel(EPixelQuestLevel levelType) {
        switch (levelType) {
            case LEVEL_HOME:
                return PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Welcome Home")
                        .builder();

            case LEVEL_1:
                return PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 1: Beginning")
                        .builder();

            case LEVEL_2:
                return PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 2: The Journey")
                        .builder();

            case LEVEL_3:
                return PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 3: The Finale")
                        .builder();
            default:
                throw new IllegalArgumentException("Unsupported level type: " + levelType);
        }
    }

    public PixelQuestLevel
    loadLevel(EPixelQuestLevel levelType) {
        PixelQuestLevel level;
        switch (levelType) {
            case LEVEL_HOME:
                level = PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Welcome Home")
                        .builder();
                break;

            case LEVEL_1:
                level = PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 1: Beginning")
                        .builder();
                break;

            case LEVEL_2:
                level = PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 2: The Journey")
                        .builder();
                break;

            case LEVEL_3:
                level = PixelQuestLevel.builder()
                        .withLevel(levelType)
                        .withTitle("Level 3: The Finale")
                        .builder();
                break;
            default:
                throw new IllegalArgumentException("Unsupported level type: " + levelType);

        }
        level.loadMap(mapService);
        return level;
    }

}

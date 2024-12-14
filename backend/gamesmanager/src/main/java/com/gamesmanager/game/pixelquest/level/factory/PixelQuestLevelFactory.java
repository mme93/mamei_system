package com.gamesmanager.game.pixelquest.level.factory;

import com.gamesmanager.game.pixelquest.level.model.EPixelQuestLevel;
import com.gamesmanager.game.pixelquest.level.model.PixelQuestLevel;
import org.springframework.stereotype.Component;

@Component
public class PixelQuestLevelFactory {

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

    public PixelQuestLevel loadLevel(EPixelQuestLevel levelType) {
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

}

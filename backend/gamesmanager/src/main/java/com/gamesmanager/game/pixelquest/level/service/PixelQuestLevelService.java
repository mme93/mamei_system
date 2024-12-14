package com.gamesmanager.game.pixelquest.level.service;

import com.gamesmanager.game.pixelquest.level.factory.PixelQuestLevelFactory;
import com.gamesmanager.game.pixelquest.level.model.PixelQuestLevel;
import com.gamesmanager.game.pixelquest.util.StringToEnumConverter;
import org.springframework.stereotype.Service;


@Service
public class PixelQuestLevelService {

    private final PixelQuestLevelFactory factory;

    public PixelQuestLevelService(PixelQuestLevelFactory factory) {
        this.factory = factory;
    }

    public PixelQuestLevel loadLevel(String level) {
        return factory.loadLevel(StringToEnumConverter.convertLevel(level));
    }
}

package com.gamesmanager.game.test.level.service;

import com.gamesmanager.game.test.level.factory.PixelQuestLevelFactory;
import com.gamesmanager.game.test.level.model.PixelQuestLevel;
import com.gamesmanager.game.test.util.StringToEnumConverter;
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

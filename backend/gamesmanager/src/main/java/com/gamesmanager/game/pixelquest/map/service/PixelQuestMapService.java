package com.gamesmanager.game.pixelquest.map.service;

import com.gamesmanager.game.pixelquest.level.model.EPixelQuestLevel;
import com.gamesmanager.game.pixelquest.map.model.PixelQuestMap;
import org.springframework.stereotype.Service;

@Service
public class PixelQuestMapService {


    public PixelQuestMap loadMap(EPixelQuestLevel level) {

        switch (level) {
            case LEVEL_HOME:

                break;

            case LEVEL_1:

                break;

            case LEVEL_2:

                break;

            case LEVEL_3:

                break;
            default:
                throw new IllegalArgumentException("Unsupported level type: " + level);

        }

        return null;
    }
}

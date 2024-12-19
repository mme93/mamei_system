package com.gamesmanager.game.test.util;

import com.gamesmanager.game.test.level.model.EPixelQuestLevel;

public class StringToEnumConverter {

    public static EPixelQuestLevel convertLevel(String level) {
        switch (level) {
            case "LEVEL_HOME":
                return EPixelQuestLevel.LEVEL_HOME;
            case "LEVEL_1":
                return EPixelQuestLevel.LEVEL_1;
            case "LEVEL_2":
                return EPixelQuestLevel.LEVEL_2;
            case "LEVEL_3":
                return EPixelQuestLevel.LEVEL_3;
            default:
                throw new IllegalArgumentException(String.format("No level found by name %s.", level));

        }
    }

}

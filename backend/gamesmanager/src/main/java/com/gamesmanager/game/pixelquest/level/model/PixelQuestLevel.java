package com.gamesmanager.game.pixelquest.level.model;


import com.gamesmanager.game.pixelquest.map.model.PixelQuestMap;
import com.gamesmanager.game.pixelquest.map.service.PixelQuestMapService;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class PixelQuestLevel {

    private EPixelQuestLevel level;
    private String title;
    private PixelQuestMap pixelQuestMap;

    private PixelQuestLevel(EPixelQuestLevel level, String title) {
        this.level = level;
        this.title = title;
    }

    public void loadMap(PixelQuestMapService mapService){
        pixelQuestMap = mapService.loadMap(level);
    }

    public static PixelQuestLevelBuilder builder() {
        return new PixelQuestLevelBuilder();
    }

    public static class PixelQuestLevelBuilder {

        private EPixelQuestLevel level;
        private String title;

        public PixelQuestLevelBuilder withLevel(EPixelQuestLevel level) {
            this.level = level;
            return this;
        }

        public PixelQuestLevelBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public PixelQuestLevel builder() {
            Objects.requireNonNull(level);
            Objects.requireNonNull(title);
            return new PixelQuestLevel(level, title);
        }

    }
}
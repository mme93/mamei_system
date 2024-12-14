package com.gamesmanager.game.pixelquest.map.model;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PixelQuestMap {

    private PixelQuestMapGrid grid;

    private PixelQuestMap(PixelQuestMapGrid grid) {
        this.grid = grid;
    }

    public static PixelQuestMapBuilder builder() {
        return new PixelQuestMapBuilder();
    }

    public static class PixelQuestMapBuilder {

        private PixelQuestMapGrid grid;

        public PixelQuestMapBuilder withGrid(PixelQuestMapGrid grid) {
            this.grid = grid;
            return this;
        }

        public PixelQuestMap builder() {

            return new PixelQuestMap(null);
        }

    }
}

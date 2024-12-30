package com.gamesmanager.game.test.test;

import com.gamesmanager.game.test.level.model.EPixelQuestMap;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PixelQuestMapDto {

    private int height;

    private int width;

    private EPixelQuestMap pixelQuestMap;

    private PixelQuestGridDto grid;

}

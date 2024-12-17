package com.gamesmanager.game.pixelquest.test;

import com.gamesmanager.game.pixelquest.level.model.EPixelQuestMap;
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

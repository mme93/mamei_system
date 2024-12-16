package com.gamesmanager.game.pixelquest.map.model;

import com.gamesmanager.game.pixelquest.test.EPixelQuestBaseTexture;
import com.gamesmanager.game.pixelquest.test.EPixelQuestElementItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class PixelQuestMapGridElementDto {


    private int rowIndex;

    private int columnIndex;

    private EPixelQuestBaseTexture baseTexture;

    private EPixelQuestElementItem item;

}

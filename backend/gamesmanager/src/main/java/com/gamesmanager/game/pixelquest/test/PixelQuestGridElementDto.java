package com.gamesmanager.game.pixelquest.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PixelQuestGridElementDto {

    private int rowIndex;

    private int columnIndex;

    private EPixelQuestBaseTexture baseTexture;

    private EPixelQuestElementItem item;

}

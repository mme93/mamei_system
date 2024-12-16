package com.gamesmanager.game.pixelquest.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PixelQuestAccountDto {

    private String userName;

    private Long currentWorldId;

    private Long currentMapId;

    private int mapColIndex;

    private int mapRowIndex;

}

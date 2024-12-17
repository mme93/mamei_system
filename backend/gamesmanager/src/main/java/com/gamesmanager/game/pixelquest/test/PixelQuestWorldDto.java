package com.gamesmanager.game.pixelquest.test;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PixelQuestWorldDto {

    private String name;

    private List<PixelQuestMapDto> maps;
}

package com.gamesmanager.game.pixelquest.test;

import com.gamesmanager.game.pixelquest.map.model.PixelQuestMapGridElementDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PixelQuestGridDto {

    private List<List<PixelQuestMapGridElementDto>>rows;
}

package com.gamesmanager.game.test.test;

import com.gamesmanager.game.test.map.model.PixelQuestMapGridElementDto;
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

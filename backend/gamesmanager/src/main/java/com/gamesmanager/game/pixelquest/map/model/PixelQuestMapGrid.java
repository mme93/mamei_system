package com.gamesmanager.game.pixelquest.map.model;

import java.util.ArrayList;
import java.util.List;

public class PixelQuestMapGrid {

    private List<List<PixelQuestMapGridElementDto>> worldRows;

    public PixelQuestMapGrid() {
        this.worldRows = new ArrayList<>();
    }

    public void addRows(List<PixelQuestMapGridElementDto> elements){
        worldRows.add(new ArrayList<>(elements));
    }

    public void addEmptyRow(){
        worldRows.add(new ArrayList<>());
    }


    public void addElementToRow(PixelQuestMapGridElementDto element, int index) {
        List<PixelQuestMapGridElementDto>elements=worldRows.get(index);
        elements.add(element);
    }

}
